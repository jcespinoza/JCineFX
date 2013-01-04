/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import code.MessageType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class MessageBox extends AnchorPane implements Initializable{
    private static Window father;
    @FXML
    private static Label message;

    private MessageBox(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/MessageBox.fxml"));
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
    private void handleOk(){
        this.getScene().getWindow().hide();
    }
    
    public static void show(Window owner, String title, String mes){
        MessageBox m = new MessageBox();
        father = owner;
        message.setText(mes);
        Scene scene = new Scene(m);
        Stage st = new Stage();
        st.setTitle(title);
        st.setScene(scene);
        st.sizeToScene();
        st.setResizable(false);
        st.initOwner(owner);
        st.initModality(Modality.WINDOW_MODAL);
        st.showAndWait();
    }

}