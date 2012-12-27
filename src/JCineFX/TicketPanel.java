/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.salas.SalaLayout;
import EDJC.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jay C Espinoza
 */
public class TicketPanel extends AnchorPane implements Initializable{

    private ClientWindow father;
    private SalaLayout selectedSala;
    private int[] tickets;
    
    @FXML
    private TextField nomField;
    public TextField adField;
    public TextField menField;
    public TextField terField;
    
    public TicketPanel(ClientWindow owner) {
        father = owner;
        tickets = father.tickets;
        selectedSala = father.currentSala;
        
        FXMLLoader fx = new FXMLLoader(getClass().getResource("TicketPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void handleComprar(ActionEvent e){
        sendInfoToFather();
    }

    private void sendInfoToFather() {
        transformValues();
        father.showSeatSelectionPanel();
    }
    
    private void transformValues(){
        if( Util.puedeConvertirse(adField.getText()) ){
            tickets[0] = Integer.parseInt(adField.getText());
        }else{
            tickets[0] = 0;
        }
        
        if( Util.puedeConvertirse(menField.getText()) ){
            tickets[1] = Integer.parseInt(menField.getText());
        }else{
            tickets[1] = 0;
        }
        
        if( Util.puedeConvertirse(terField.getText()) ){
            tickets[2] = Integer.parseInt(terField.getText());
        }else{
            tickets[2] = 0;
        }
        father.tickets = tickets;
    }
}