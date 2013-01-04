package controllers;

import EDJC.movies.Movie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class MovieTile extends AnchorPane implements Initializable{
    private Movie movie;
    
    //FXML
    public ImageView peliImg;
    public Text label3d;
    public Label title;

    public MovieTile(Movie m){
        movie = m;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/MovieTile.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
        setFocusTraversable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
    }
    
    public Movie getMovie(){
        return movie;
    }

    private void setData() {
        peliImg.setImage(new Image(movie.getImgFile()));
        label3d.setVisible(movie.is3D());
        title.setText(movie.getTitle());
    }
}