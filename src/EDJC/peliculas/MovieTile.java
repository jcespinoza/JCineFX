/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.peliculas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jay C Espinoza
 */
public class MovieTile extends GridPane implements Initializable{
    private Pelicula pelicula;
    private ImageView img;
    private Node owner;
    
    public MovieTile(Pelicula p, Node container){
        pelicula = p;
        owner = container;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("MovieTile,fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try {
            fx.load();
            setListener();
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

    public Pelicula getPelicula() {
        return pelicula;
    }   
}
