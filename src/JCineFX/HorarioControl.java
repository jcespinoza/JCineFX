/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.peliculas.Pelicula;
import EDJC.peliculas.PeliculaBuilder;
import EDJC.salas.Horario;
import EDJC.salas.SalaBuilder;
import EDJC.salas.SalaLayout;
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
    private Pelicula pelicula;
    private Horario horario;
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
    
       
    public HorarioControl(int codSala, Horario h, Node container) {
        this.codSala = codSala;
        father = container;
        horario = h;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("HorarioControl.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try {
            pelicula = PeliculaBuilder.leerPelicula(h.getCodPeli());
            fx.load();
        } catch (IOException ex) {
            System.out.println("Error " + ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        codigo.setText(horario.getCodPeli() + "");
        titulo.setText(pelicula.getNombre());
        inicio.setText("Inicio: "+horario.getInicioStr());
        fin.setText("Fin: "+horario.getFinStr());
        genero.setText("Genero: "+pelicula.getGenero().toString());
        clasi.setText("Clasificacion: "+pelicula.getClasificacion().toString());
        formato3D.setText("Formato 3D: "+pelicula.getFormato3D().toString());
        duracion.setText(horario.getDuracionStr());
        img.setImage(new Image(pelicula.getImgArchivo()));
        }catch(Exception ex){
            System.out.println("Error: " + ex + " > " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public Horario getHorario(){
        return this.horario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public SalaLayout getSala() throws IOException{
        try{
            return SalaBuilder.leerSala(codSala);
        }catch(ClassNotFoundException ex){
            return null;
        }
    }
    
}