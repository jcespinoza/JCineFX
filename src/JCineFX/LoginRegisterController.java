/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class LoginRegisterController implements Initializable {
    @FXML
    public TitledPane titledLogin;
    public TitledPane titledRegister;
    public Label editLabel;
    public ImageView registerPicture;
    
    @FXML
    private void KeyReleaseHandle(KeyEvent e){
        final KeyCombination combo = new KeyCodeCombination(KeyCode.ENTER);
        try{
            if( combo.equals(new KeyCodeCombination(e.getCode())))
                System.out.println("Enter");;
        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }

    }
    @FXML
    private void handleRegisterButton(ActionEvent e){
        titledLogin.setCollapsible(true);
        titledRegister.setExpanded(true);
        titledLogin.setCollapsible(false);
    }
    
    @FXML
    private void handleLoginButton(ActionEvent e){
        //codigo para validar usuario aqui
    }
    
    @FXML
    private void handleFotoClick(MouseEvent e){
        //Codigo para poner la imagen aqui
        FileChooser fc = new FileChooser();
        String url = null;
        try {
            url = fc.showOpenDialog(editLabel.getScene().getWindow()).toURI().toURL().toString();
            Image img = new Image(url);
            registerPicture.setImage(img);
            registerPicture.setSmooth(true);
        } catch (MalformedURLException ex) {
            System.out.println("Error was: " + ex);
        }
    }
    
    @FXML
    private void handleCancelarReg(ActionEvent e){
        titledLogin.setCollapsible(true);
        titledLogin.setExpanded(true);
        titledLogin.setCollapsible(false);
    }
    
    @FXML
    private void handleEntered(MouseEvent e){
        String image = JCineFXLauncher1.class.getResource("edit.png").toExternalForm();
        editLabel.setStyle("-fx-background-image: url('" + image + "');"+
                        "-fx-background-repeat:round;" +
                        "-fx-border-radius: 2px;" +
                        "-fx-border-color: white;");
    }
    
    @FXML
    private void handleExited(MouseEvent e){
        editLabel.setStyle(null);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}