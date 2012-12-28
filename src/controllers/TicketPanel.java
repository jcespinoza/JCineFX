/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.util.Util;
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
public class TicketPanel extends AnchorPane implements Initializable{
    private ClientWindow father;

    public TicketPanel(ClientWindow cli){
        father = cli;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/TicketPanel.fxml"));
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
    public void handleSelectSeats(){
        SeatSelection se = new SeatSelection(father);
        Util.changeContent(se, father.content);
    }
    
    @FXML
    public void handleCancel(){
        father.resetClientData();
        father.loadCartelera();
    }
}