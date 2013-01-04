package controllers;

import EDJC.security.User;
import EDJC.security.UserBuilder;
import EDJC.util.Util;
import JCineFX.JCineFX;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class ProfilePanel extends AnchorPane implements Initializable{
    private AdminWindow father;
    private String imgPath;
    private User user;
    private boolean oldOk = false;
    private boolean pLength = false;
    private boolean uLength = false;
    private boolean match = false;
    
    //FXML variables
    public TextField username;
    public TextField name;
    public PasswordField oldPass;
    public PasswordField newPass1;
    public PasswordField newPass2;
    public Button changePic;
    public ImageView picture;
    public Button updateButton;
    
    public ProfilePanel(AdminWindow ad){
        father = ad;
        user = father.conf.getUser();
        imgPath = user.getPicturePath();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/ProfilePanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillUserData();
    }
    
    @FXML
    private void handleUpdate(){
        oldOk = checkOld();
        match = theyMatch();
        pLength = passLengthOk();
        uLength = userLengthOk();
        
        showAlert();
        if( oldOk && match && pLength && uLength)
            updateUser();
    }

    @FXML
    private void handleChangePic(){
        String temp = Util.getPicture(father.conf.getLastUserPath(), father.getWindow());
        if(temp != null){
            father.conf.setLastUserPath(new File(temp).getParent());
            try{
                imgPath = (new URL("file:" + temp).toExternalForm());
                picture.setImage(new Image(imgPath));
            }catch(Exception ex){ex.printStackTrace();}
        }
    }
    
    private void fillUserData() {
        username.setText(user.getUsername());
        name.setText(user.getFullName());
        if(! user.getPicturePath().equals("")){
            picture.setImage(new Image(user.getPicturePath()));           
        }
        if( user.getUsername().equals("guest") ){
            oldPass.setText(new String(user.getPassword()));
            disableInput();
        }
    }
    
    private void disableInput(){
        changePic.setDisable(true);
        username.setEditable(false);
        name.setEditable(false);
        oldPass.setEditable(false);
        newPass1.setEditable(false);
        newPass2.setEditable(false);
        updateButton.setDisable(true);
    }
    
    private boolean theyMatch(){
        if(newPass1.getText().equals(newPass2.getText()))
            return true;
        return false;
    }
    private boolean passLengthOk(){
        if( newPass1.getText().length() < 6 )
            return false;
        return true;
    }
    
    private boolean userLengthOk(){
        if( username.getText().length() < 2)
            return false;
        return true;
    }
    
    private boolean checkOld(){
        if( oldPass.getText().equals( new String( user.getPassword() )))
            return true;
        return false;
    }
    
    private void showAlert(){
        String errors = "";
        if( !uLength ) errors += "La longitud del username es muy corta.\n";
        if( !oldOk   ) errors += "El antiguo password es incorrecto.\n";
        if( !pLength ) errors += "La longitud del password es muy corta.\n";
        if( !match   ) errors += "Los passwords no coinciden.\n";
        
        if( !(uLength && oldOk && pLength && match) )
        MessageBox.show(this.getScene().getWindow(), "Datos incorrectos", errors);
    }
    
    private void updateUser(){
        if( !dataDiffers() )
            return;
        
        user.setUsername(username.getText());
        user.setFullName(name.getText());
        user.setPassword(newPass1.getText().toCharArray());
        user.setPicturePath(imgPath);
        UserBuilder.writeUsers(father.users, JCineFX.USERSPATH);
        MessageBox.show(this.getScene().getWindow(), "Completo!", "Datos actualizados exitosamente");
        father.loadMainPanel();
    }

    private boolean dataDiffers() {
        User u = new User( username.getText(), newPass1.getText().toCharArray() );
        u.setFullName(name.getText());
        u.setPicturePath(imgPath);
        if(user.compareTo(u)){
            MessageBox.show(getScene().getWindow(), "Informacion", "Los datos eran los mismos. No se actualizó el registro");
            father.loadMainPanel();
            return false;
        }
        return true;
    }
}