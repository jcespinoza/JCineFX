/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.movies.Movie;
import EDJC.rooms.Schedule;
import EDJC.rooms.Room4Ticket;
import EDJC.rooms.RoomLayout;
import EDJC.rooms.seats.SeatState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author Jay C Espinoza
 */
public class SeatSelection extends AnchorPane implements Initializable{

    @FXML
    private VBox chairPane;
    private RoomLayout sala;
    private Movie peli;
    private Schedule horario;
    private Room4Ticket st;
    private int tickets;
    public Text titulo;
    public Label fecha;
    public Label duracion;
    public Label genero;
    public Label estreno;
    public Label clasi;
    public static EventHandler<MouseEvent> mHandler;
    private ClientWindow father;
    
    public SeatSelection(ClientWindow owner){
        father = owner;
        sala = owner.currentSala;
        peli = owner.currentMov;
        horario = owner.currentHorario;
        st = owner.selectedST;
        tickets = owner.tickets[0] + owner.tickets[1] + owner.tickets[2];
        try{
            FXMLLoader fx = new FXMLLoader(getClass().getResource("SeatSelection.fxml"));
            fx.setRoot(this);
            fx.setController(this);
            fx.load();
        }catch(Exception ex){}
        mHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                SillaControl s = ((SillaControl)t.getSource());
                if( s.getState() == SeatState.DISPONIBLE)
                    s.setState(SeatState.SELECCIONADO);

                if( s.getState() == SeatState.SELECCIONADO)
                    s.setState(SeatState.DISPONIBLE);
                sala.setSilla(s.getRow(), s.getNumber(), s.getState());
            }
        };
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chairPane.getChildren().add(new ChairGrid(sala, false));
        titulo.setText(peli.getNombre());
        fecha.setText(horario.getFecha().getTime() + "");
        duracion.setText("Duracion: " + horario.getDuracionStr());
        genero.setText("Genero: " + peli.getGenero());
        clasi.setText("Clasificacion: " + peli.getClasificacion());
    }


    
    @FXML
    private void handleAccept(ActionEvent e){
        //code for selling the ticket goes here
        if( sala.howMany(SeatState.SELECCIONADO) == tickets){
            st.setSalaLayout(sala);
            sala.changeStates(SeatState.SELECCIONADO, SeatState.RESERVADO);
            JOptionPane.showMessageDialog(null, "THanks!");
        }
    }
    
    @FXML
    private void handleCancel(ActionEvent e){
        //take the user to the Cartelera again
        sala.changeStates(SeatState.SELECCIONADO, SeatState.DISPONIBLE);
        horario = null;
        peli = null;
        tickets = 0;
        sala = null;
        father.showCartelera();
    }
}