package controllers;

import EDJC.movies.Movie;
import EDJC.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class MovieListPanel extends AnchorPane implements Initializable{
    private AdminWindow father;
    
    @FXML
    private FlowPane flowPane;
    
    public MovieListPanel(AdminWindow ad){
        father = ad;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/MovieListPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
        setFocusTraversable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMovies();
    }

    @FXML
    private void handleGoBack(){
        father.loadMainPanel();
    }
    
    @FXML
    private void handleAddNew(){
        AddMoviePanel aad = new AddMoviePanel(father, this);
        Util.changeContent(aad, father.content);
    }

    private void loadMovies() {
        //Fetch movies from father's arrayList
        for(Movie m: father.movies){
            MovieTile mt = new MovieTile(m);

            flowPane.getChildren().add(mt);
        }
    }
}