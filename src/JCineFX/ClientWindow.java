/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.peliculas.Pelicula;
import EDJC.salas.Horario;
import EDJC.salas.Sala4Ticket;
import EDJC.salas.SalaLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jay C Espinoza
 */
public class ClientWindow implements Initializable{
    public SalaLayout currentSala;
    public Pelicula currentMov;
    public Horario currentHorario;
    public String nomCliente;
    public int[] tickets = new int[3];
    public Sala4Ticket selectedST;
    
    @FXML
    private AnchorPane content;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCartelera();
    }
    
    public void showCartelera(){
        Cartelera car = new Cartelera(this);
        changeContent(car);
    }
    
    private void changeContent(Node n){
        emptyContent();
        AnchorPane.setBottomAnchor(n, 0.0);
        AnchorPane.setTopAnchor(n, 0.0);
        AnchorPane.setLeftAnchor(n, 0.0);
        AnchorPane.setRightAnchor(n, 0.0);
        content.getChildren().add(n);
    }

    private void emptyContent() {
        content.getChildren().removeAll(content.getChildren());
    }

    public void showTicketPanel() {
        TicketPanel tp = new TicketPanel(this);
        changeContent(tp);
    }
    
    @FXML
    private void handleExit(ActionEvent e){
        Platform.exit();
    }
    
    @FXML
    private void handleGoAdmin(ActionEvent e){
        ModSelectionController m = new ModSelectionController();
        m.handleAdminButton(e);
        content.getScene().getWindow().hide();
    }

    public void showSeatSelectionPanel() {
        SeatSelection sel = new SeatSelection(this);
        changeContent(sel);
    }
}