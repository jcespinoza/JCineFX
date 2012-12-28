/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.movies.Movie;
import EDJC.movies.MovieBuilder;
import EDJC.rooms.RoomBuilder;
import EDJC.rooms.Schedule;
import EDJC.rooms.RoomLayout;
import EDJC.security.User;
import EDJC.security.UserBuilder;
import EDJC.util.Util;
import JCineFX.JCineFX;
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

    private AdminWindow(ArrayList<User> us) {
        this();
        users = us;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMainPanel();
        loadMovies();
        loadRooms();
        loadSchedules();
    }
    
    public static void show(){show(new ArrayList<User>());}

    public static void show(ArrayList<User> us){
        AnchorPane adm = new AdminWindow(us);
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
    
    public void saveUsers(){UserBuilder.writeUsers(users, JCineFX.USERSPATH);}
    public void saveMovies(){MovieBuilder.writeMovies(movies, JCineFX.MOVIESPATH);}
    
    public void saveRooms(){RoomBuilder.writeRooms(rooms, JCineFX.ROOMSPATH);}
    
    public void saveSchedules(){}
    
    private void loadMovies(){
        movies = MovieBuilder.readMovies(JCineFX.MOVIESPATH);
    }
    
    private void loadRooms(){
        rooms = RoomBuilder.readRooms(JCineFX.ROOMSPATH);
    }
    
    private void loadSchedules(){
        
    }
}