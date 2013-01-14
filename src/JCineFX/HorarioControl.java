/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.movies.Movie;
import EDJC.movies.MovieBuilder;
import EDJC.rooms.Schedule;
import EDJC.rooms.RoomBuilder;
import EDJC.rooms.RoomLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jay C Espinoza
 */
public class HorarioControl extends AnchorPane implements Initializable{
    private Movie pelicula;
    private Schedule horario;
    private int codSala;
    @FXML
    public Label codigo;
    public Label titulo;
    public Label inicio;
    public Label fin;
    public Label genero;
    public Label clasi;
    public Label formato3D;
    public Label duracion;
    public ImageView img;
    private Node father;
    
       
    public HorarioControl(int codSala, Schedule h, Node container) {
        this.codSala = codSala;
        father = container;
        horario = h;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("HorarioControl.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try {
            pelicula = null;//MovieBuilder.readMovie(h.getCodPeli());
            fx.load();
        } catch (IOException ex) {
            System.out.println("Error " + ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        try{
        codigo.setText(horario.getCodPeli() + "");
        titulo.setText(pelicula.getTitle());
        inicio.setText("Inicio: "+horario.getInicioStr());
        fin.setText("Fin: "+horario.getFinStr());
        genero.setText("Genero: "+pelicula.getGenre().toString());
        clasi.setText("Clasificacion: "+pelicula.getRating().toString());
        formato3D.setText("Formato 3D: "+pelicula.getFormat3D().toString());
        duracion.setText(horario.getDuracionStr());
        img.setImage(new Image(pelicula.getImgFile()));
        }catch(Exception ex){
            System.out.println("Error: " + ex + " > " + ex.getMessage());
            ex.printStackTrace();
        }
        * */
    }
    
    public Schedule getHorario(){
        return this.horario;
    }

    public Movie getPelicula() {
        return pelicula;
    }

    public RoomLayout getSala() throws IOException{
        try{
            return RoomBuilder.leerSala(codSala);
        }catch(ClassNotFoundException ex){
            return null;
        }
    }
    
}