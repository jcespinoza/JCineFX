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
public class LoginPanel extends AnchorPane implements Initializable{
    private LoginRegister father;
    
    public LoginPanel(LoginRegister lr){
        father = lr;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/LoginPanel.fxml"));
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
    private void handleRegister(){
        father.loadRegisterPanel();
    }
    
    @FXML
    private void handleLogin(){
        AdminWindow.show();
        this.getScene().getWindow().hide();
    }

}