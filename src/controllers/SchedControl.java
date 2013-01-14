package controllers;

import EDJC.movies.Movie;
import EDJC.rooms.SchedEntry;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class SchedControl extends AnchorPane implements Initializable{
    private SchedEntry sched;
    
    //FXML
    public Label title;
    public Label length;
    public Label start;
    public Label end;
    public Label code;
    public Label clasi;
    public Label genre;
    public Label format;
    public ImageView img;
    
    public SchedControl(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("HorarioControl.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    public SchedControl(SchedEntry s){
        this();
        sched = s;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(sched != null)
            loadInfo();
    }

    private void loadInfo() {
        Movie movie = sched.getMovie();
        title.setText(movie.getTitle());
        length.setText("Duración: " + movie.getLenght());
        start.setText("Inicio: " + sched.getString(SchedEntry.START_MINUTE));
        end.setText("Fin: " + sched.getString(SchedEntry.END_MINUTE));
        code.setText("Codigo Pelicula: " + movie.getCode());
        clasi.setText("Clasificación: " + movie.getRating());
        genre.setText("Género: " + movie.getGenre());
        format.setText("Formato: " + movie.getFormat3D());
        img.setImage(new Image(movie.getImgFile()));
    }
}