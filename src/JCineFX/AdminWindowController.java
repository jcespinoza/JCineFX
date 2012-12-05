/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class AdminWindowController implements Initializable {
    @FXML
    public TextField usernameField;
    public TextField nombreField;
    public PasswordField oldPassField;
    public PasswordField newPass1Field;
    public PasswordField newPass2Field;
    public Button updateButton;
    public Button btnImagenUser;
    public ImageView imgUser;
    public String imagePath = "file:src/res/user-icon-big.png";
    
    
    @FXML
    private MenuButton opMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillUserData();
        disableInput();
    }
    
    @FXML
    private void handleExit(ActionEvent e){
        Platform.exit();
    }
    
    @FXML
    private void handleUpdateButton(ActionEvent e){
        System.out.println("You clicked the update button");
    }
    
    private void disableInput(){
        usernameField.setEditable(false);
        nombreField.setEditable(false);
        oldPassField.setEditable(false);
        newPass1Field.setEditable(false);
        newPass2Field.setEditable(false);
        updateButton.setDisable(true);
    }

    private void fillUserData() {
        usernameField.setText(JCineFX.getCurrentUser().getUsername());
        nombreField.setText(JCineFX.getCurrentUser().getNombreCompleto());
        oldPassField.setText(new String(JCineFX.getCurrentUser().getPassword()));
        String tempPath = "file:" + JCineFX.getCurrentUser().getFotoPath();
        System.out.println("TempFile:" + tempPath);
        try{
            imgUser.setImage(new Image(tempPath));
            System.out.println("Succesfuly set the image:" + tempPath);
        }catch(IllegalArgumentException ex){
            System.out.println("Failed to set the image:" + tempPath);
            imgUser.setImage(new Image(imagePath));
        }
    }
}
