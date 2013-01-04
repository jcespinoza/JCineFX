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
public class TicketPricesPanel extends AnchorPane implements Initializable{
    private AdminWindow father;
    
    public TicketPricesPanel(AdminWindow a){
        father = a;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/TicketPricesPanel.fxml"));
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
    private void handleAccept(){
        father.loadMainPanel();
    }

}