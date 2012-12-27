/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

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
        /*try{

            Stage st = new Stage();
            Scene sce = new Scene(p);
            st.setScene(sce);
            //st.initOwner(lblTitle.getScene().getWindow());
            //st.initModality(Modality.APPLICATION_MODAL);
            st.show();
            st.setResizable(false);
            ((Node)e.getSource()).getScene().getWindow().hide();
        }catch(Exception ex){

        }*/
    }
    
    @FXML
    public void handleClientButton(ActionEvent e){
        /*try{
            Parent p = FXMLLoader.load(getClass().getResource("ClientWindow.fxml"));
            Stage st = new Stage();
            Scene sce = new Scene(p);
            st.setScene(sce);
            st.show();
            //lblTitle.getScene().getWindow().hide();
            ((Node)e.getSource()).getScene().getWindow().hide();
        }catch(Exception ex){
        }*/
    }
}
