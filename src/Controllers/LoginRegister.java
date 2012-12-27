/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class LoginRegister extends AnchorPane implements Initializable{
    
    public LoginRegister(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/Login_Register.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}