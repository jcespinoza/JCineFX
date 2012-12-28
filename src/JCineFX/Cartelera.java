/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.movies.Pelicula;
import EDJC.rooms.Horario;
import EDJC.rooms.HorarioBuilder;
import EDJC.rooms.Sala4Ticket;
import EDJC.rooms.SalaBuilder;
import EDJC.rooms.SalaLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jay C Espinoza
 */
public class Cartelera extends AnchorPane implements Initializable, EventHandler<MouseEvent>{
    @FXML
    public HBox hboxPane;
    
    private ClientWindow father;
    private Pelicula selectedMov;
    private SalaLayout selectedSala;
    private Horario selectedHorario;
    
    public Cartelera(ClientWindow c){
        father = c;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("Cartelera.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){
            
        }
    }
    
    private void sendInfoToFather(){
        father.currentMov = selectedMov;
        father.currentSala = selectedSala;
        father.currentHorario = selectedHorario;
        try {
            father.selectedST = SalaBuilder.leerSalaT( Sala4Ticket.parseNombre(selectedSala.getCodigo(), selectedMov.getCodigo(), selectedHorario.getFecha()));
        } catch (Exception ex) {}
        father.showTicketPanel();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HorarioBuilder.fillPanel(hboxPane, this);
    }

    @Override
    public void handle(MouseEvent t) {
        HorarioControl hc = (HorarioControl)t.getSource();
        selectedHorario = hc.getHorario();
        selectedMov = hc.getPelicula();
        try {
            selectedSala = hc.getSala();
            sendInfoToFather();
        } catch (IOException ex) {
            System.out.println("Could not send Sala to ClienWindow");
        }
    }
}