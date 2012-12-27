/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class ModSelectionController implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    public void handleAdminButton(ActionEvent e){
        try{
            Parent p = FXMLLoader.load(getClass().getResource("Login_Register.fxml"));
            Stage st = new Stage();
            Scene sce = new Scene(p);
            st.setScene(sce);
            st.show();
            st.setResizable(false);
            ((Node)e.getSource()).getScene().getWindow().hide();
        }catch(Exception ex){

        }
    }
    
    @FXML
    public void handleClientButton(ActionEvent e){
        try{
            Parent p = FXMLLoader.load(getClass().getResource("ClientWindow.fxml"));
            Stage st = new Stage();
            Scene sce = new Scene(p);
            st.setScene(sce);
            st.show();
            //lblTitle.getScene().getWindow().hide();
            ((Node)e.getSource()).getScene().getWindow().hide();
        }catch(Exception ex){
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
