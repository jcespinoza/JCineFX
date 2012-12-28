/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class SeatSelection extends AnchorPane implements Initializable{
    private ClientWindow father;
    
    public SeatSelection(ClientWindow cli){
        father = cli;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/SeatSelection.fxml"));
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
    private void handleCancel(){
        father.loadTicketPanel();
    }
    
    @FXML
    private void handleAccept(){
        System.out.println(father.getNTickets() + "Tickets bought!");
    }
}