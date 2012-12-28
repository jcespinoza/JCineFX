/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.movies.Movie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jay C Espinoza
 */
public class MovieTile extends AnchorPane implements Initializable{
    private Movie pelicula;
    private Node owner;
    
    @FXML
    public GridPane grid;
    public ImageView peliImg;
    
    public MovieTile(Movie p, Node container){
        pelicula = p;
        owner = container;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("MovieTile.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try {
            fx.load();
            setListener();
            peliImg.setImage(new Image(p.getImgFile()));
        } catch (IOException ex) {
            System.out.println("Failed to load MovieTile.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //code to initialize goes here
    }

    private void setListener() {
        EventHandler<MouseEvent> handler = ((EventHandler<MouseEvent>)owner);
        this.setOnMouseClicked(handler);
        
    }

    public Movie getPelicula() {
        return pelicula;
    }   
}
