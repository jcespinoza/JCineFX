package controllers;

import EDJC.security.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class LoginPanel extends AnchorPane implements Initializable{
    private LoginRegister father;
    @FXML
    private PasswordField passLog;
    public TextField userLog;
    public Label msgLabel;
    
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
        if(validateInput()){
            AdminWindow.show(father.users);
            this.getScene().getWindow().hide();
        }
    }

    private boolean validateInput(){
        User user = new User(userLog.getText(), passLog.getText().toCharArray());
        if(! checkUsername(userLog)){
            msgLabel.setText("Username incorrecto");
            return false;
        }
        return true;
    }
    
    private boolean checkUsername(TextField usField){
        return father.users.contains(new User(usField.getText()));
    }
}