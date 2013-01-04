/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.security.Config;
import EDJC.security.User;
import EDJC.security.UserBuilder;
import EDJC.util.Util;
import JCineFX.JCineFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class LoginRegister extends AnchorPane implements Initializable{
    @FXML
    public AnchorPane loginPane;
    public AnchorPane registerPane;
    public TitledPane titledLogin;
    public TitledPane titledRegister;
    protected Config conf;
        
    protected ArrayList<User> users;
    
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
        loadUsuarios();
        loadLoginPanel();
        loadConfiguration();
    }
    
    private void loadConfiguration() {
        conf = Config.loadFromDisk(JCineFX.CONFIG_PATH);
        if( conf == null)
            conf = Config.getDefault();
    }
    
    public void loadUsuarios(){
        users = UserBuilder.readUsers(JCineFX.USERSPATH);
        if( users.isEmpty() )
            users = UserBuilder.defaultList();
    }
    
    public static void show(){
        AnchorPane log = new LoginRegister();
        Scene scene = new Scene(log);
        Stage st = new Stage();
        st.setScene(scene);
        st.sizeToScene();
        st.setResizable(false);
        st.show();
    }

    public void loadLoginPanel() {
        titledLogin.setCollapsible(true);
        titledLogin.setExpanded(true);
        titledLogin.setCollapsible(false);
        LoginPanel log = new LoginPanel(this);
        Util.changeContent(log, loginPane);
    }

    public void loadRegisterPanel(){
        titledLogin.setCollapsible(true);
        titledRegister.setExpanded(true);
        titledLogin.setCollapsible(false);
        RegisterPanel rp = new RegisterPanel(this);
        Util.changeContent(rp, registerPane);
    }
}