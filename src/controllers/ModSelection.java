/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class ModSelection extends AnchorPane implements Initializable{

    public ModSelection(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/ModSelection.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public static void show(){
        AnchorPane mod = new ModSelection();
        Scene scene = new Scene(mod);
        Stage st = new Stage();
        st.setScene(scene);
        st.sizeToScene();
        st.setResizable(false);
        st.show();
    }
    
    public void handleAdminButton(ActionEvent e){
        try{
            LoginRegister.show();
            this.getScene().getWindow().hide();
        }catch(Exception ex){}
    }
    
    @FXML
    public void handleClientButton(ActionEvent e){
        ClientWindow.show();
        this.getScene().getWindow().hide();
    }
}
