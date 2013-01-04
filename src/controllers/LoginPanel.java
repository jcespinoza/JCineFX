package controllers;

import EDJC.security.Config;
import EDJC.security.User;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class LoginPanel extends AnchorPane implements Initializable{
    private LoginRegister father;
    private ChangeListener fListener;
    private int currentUserIndex = -1;
    
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
        initFocusListener();
        registerListener();
    }
    
    private void initFocusListener() {
        fListener = new ChangeListener() {

            @Override
            public void changed(ObservableValue focusProperty, Object prev, Object newValue) {
                //cast to ReadOnlyProperty, then to Node, to get the control that owns the changed property
                Node control = (Node)( ((ReadOnlyProperty)(focusProperty)).getBean() );
                boolean focus = (Boolean)newValue;

                if(control.equals(userLog))
                    handleUsernameFocus(focus);
                else if(control.equals(passLog))
                    handlePassFocus(focus);
            }
        };
    }
    
    private void registerListener(){
        userLog.focusedProperty().addListener(fListener);
        passLog.focusedProperty().addListener(fListener);
    }
    
    @FXML
    private void handleRegister(){
        father.loadRegisterPanel();
    }
    
    @FXML
    private void handleLogin(){
        if(validateInput()){
            AdminWindow.show(father.users, father.conf);
            this.getScene().getWindow().hide();
        }
    }

    private boolean validateInput(){
        User user = new User(userLog.getText(), passLog.getText().toCharArray());
        int indexFound = checkUsername(user.getUsername());
        if( indexFound == -1 ){
            msgLabel.setText("Ese username no existe.");
            return false;
        }else if( ! checkPassword(indexFound, user.getPassword()) ){
            msgLabel.setText("Combinación de Username y contraseña incorrecta.");
            return false;
        }
        father.conf.setUser(father.users.get(indexFound));
        return true;
    }
    
    private int checkUsername(String username){
        return father.users.indexOf(new User(username));
    }
    
    private boolean checkPassword(int index, char[] pass){
        return Arrays.equals(pass, father.users.get(index).getPassword());
    }
    
    private void handleUsernameFocus(boolean focus) {
        if( focus )
            msgLabel.setText(null);
    }

    private void handlePassFocus(boolean focus) {
        if( focus )
            msgLabel.setText(null);
    }
}