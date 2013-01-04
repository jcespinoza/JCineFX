package controllers;

import EDJC.security.User;
import EDJC.security.UserBuilder;
import EDJC.util.Util;
import JCineFX.JCineFX;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class RegisterPanel extends AnchorPane implements Initializable{
    //FXML variables
    public Label editLabel;
    public ImageView registerPicture;
    public Label userMsg;
    public Label pass1Msg;
    public Label pass2Msg;
    public TextField username;
    public TextField name;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;
    
    //Object variables
    private LoginRegister father;
    private ChangeListener fListener;
    private String imgPath = getClass().getResource("/res/user-icon-big.png").toExternalForm();
    private boolean exists = false;
    private boolean passLenght = true;
    private boolean passMatch = false;
    
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
        initFocusListener();
        registerListener();
    }
    
    private void initFocusListener(){
        fListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue focusProperty, Object prev, Object newValue) {
                //cast to ReadOnlyProperty, then to Node, to get the control that owns the changed property
                Node control = (Node)( ((ReadOnlyProperty)(focusProperty)).getBean() );
                boolean focus = (Boolean)newValue;

                if(control.equals(username)){
                    handleUsernameFocus(focus);
                }else if(control.equals(pass1)){
                    handlePass1Focus(focus);
                }else{
                    handlePass2Focus(focus);
                }
            }
        };
    }
    
    private void registerListener(){
        username.focusedProperty().addListener(fListener);
        name.focusedProperty().addListener(fListener);
        pass1.focusedProperty().addListener(fListener);
        pass2.focusedProperty().addListener(fListener);
    }
    
    @FXML
    private void handleCancel(){
        father.loadLoginPanel();
    }
    
    @FXML
    private void handleRegister(){
        //method for saving to disk here
        if( !exists && passLenght && passMatch ){
            User u = new User(username.getText(), pass1.getText().toCharArray());
            u.setPicturePath(imgPath);
            u.setFullName(name.getText());
            father.users.add(u);
            UserBuilder.writeUsers(father.users, JCineFX.USERSPATH);
            handleCancel();
        }
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
        String temp = Util.getPicture(father.conf.getLastUserPath(), father.getScene().getWindow());
        if(temp != null){
            father.conf.setLastUserPath(new File(temp).getParent());
            try{
                imgPath = (new URL("file:" + temp).toExternalForm());
                registerPicture.setImage(new Image(imgPath));
            }catch(Exception ex){ex.printStackTrace();}
        }
    }
    
    private void handleUsernameFocus(boolean focus) {
        if( focus ){
            userMsg.setText(null);
            exists = false;
        }else{
            User us = new User(username.getText());
            if( father.users.contains(us) ){
                userMsg.setText("Este username ya existe");
                exists = true;
            }
        }
    }

    private void handlePass1Focus(boolean focus) {
        if( focus ){
            pass1Msg.setText(null);
            passLenght = false;
        }else{
            if( pass1.getText().length() < 6){
                pass1Msg.setText("El password debe tener al menos 6 caracteres.");
                passLenght = false;
            }else
                passLenght = true;
        }
    }

    private void handlePass2Focus(boolean focus) {
        if( focus ){
            pass2Msg.setText(null);
            passMatch = false;
        }else{
            if( pass1.getText().equals( pass2.getText() ) )
                passMatch = true;
            else{
                passMatch = false;
                pass2Msg.setText("Passwords no coinciden");
            }
        }
    }
}