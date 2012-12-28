/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import JCineFX.JCineFX;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class RegisterPanel extends AnchorPane implements Initializable{
    private LoginRegister father;
    public Label editLabel;
    public ImageView registerPicture;
    
    public RegisterPanel(LoginRegister lr){
        father = lr;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/RegisterPanel.fxml"));
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
    private void handleCancel(){
        father.loadLoginPanel();
    }
    
    @FXML
    private void handleRegister(){
        father.loadLoginPanel();
    }
    
    @FXML
    private void handleEntered(){
        String image = JCineFX.class.getResource("edit.png").toExternalForm();
        editLabel.setStyle("-fx-background-image: url('" + image + "');"+
                        "-fx-background-repeat:round;" +
                        "-fx-border-radius: 2px;" +
                        "-fx-border-color: white;");
    }
    
    @FXML
    private void handleExited(){
        editLabel.setStyle(null);
    }
    
    @FXML
    private void handleFotoClick(){
        FileChooser fc = new FileChooser();
        String url = null;
        try {
            url = fc.showOpenDialog(editLabel.getScene().getWindow()).toURI().toURL().toString();
            Image img = new Image(url);
            registerPicture.setImage(img);
            registerPicture.setSmooth(true);
        } catch (MalformedURLException ex) {}
    }
}