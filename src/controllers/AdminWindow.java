/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.movies.Movie;
import EDJC.rooms.Schedule;
import EDJC.rooms.RoomLayout;
import EDJC.security.User;
import EDJC.util.Util;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class AdminWindow extends AnchorPane implements Initializable{
    public AnchorPane content;
    protected ArrayList<User> users;
    protected ArrayList<Movie> movies;
    protected ArrayList<Schedule> scheds;
    protected ArrayList<RoomLayout> rooms;
    
    public AdminWindow(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/AdminWindow.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMainPanel();
    }

    public static void show(){
        AnchorPane adm = new AdminWindow();
        Scene scene = new Scene(adm);
        Stage st = new Stage();
        st.setScene(scene);
        st.sizeToScene();
        st.setResizable(false);
        st.show();
    }
    
    @FXML
    private void handleGoClient(){
        ClientWindow.show();
        this.getScene().getWindow().hide();
    }
    
    @FXML
    private void handleMod(){
        ProfilePanel p = new ProfilePanel(this);
        Util.changeContent(p, content);
    }
    
    @FXML
    private void handleExit(){
        Platform.exit();
    }
    
    @FXML
    private void handleLogOut(){
        LoginRegister.show();
        this.getScene().getWindow().hide();
    }
    
    public void loadMainPanel(){
        MainPanel mp = new MainPanel(this);
        Util.changeContent(mp, content);
    }
    
    public void loadNewMoviePanel(){
        AddMoviePanel adm = new AddMoviePanel(this);
        Util.changeContent(adm, content);
    }
    
    public void loadSchedsPanel(){
        SchedulePanel sc = new SchedulePanel(this);
        Util.changeContent(sc, content);
    }
}