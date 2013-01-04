package controllers;

import EDJC.movies.Movie;
import EDJC.movies.MovieBuilder;
import EDJC.rooms.RoomBuilder;
import EDJC.rooms.RoomLayout;
import EDJC.rooms.Schedule;
import EDJC.security.Config;
import EDJC.security.User;
import EDJC.security.UserBuilder;
import EDJC.util.Util;
import JCineFX.JCineFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

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
    protected Config conf;
    protected int currentUser;
    protected boolean wasModified = false;
    
    @FXML
    protected Menu userMenu;
    
    public AdminWindow(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/AdminWindow.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    private AdminWindow(ArrayList<User> us, Config c) {
        this();
        users = us;
        conf = c;
        currentUser = users.indexOf(conf.getUser());
        setUserMenuLabel();
        reloadConf();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMainPanel();
        loadMovies();
        loadRooms();
        loadSchedules();
    }
    
    public static void show(){show(new ArrayList<User>(), null);}

    public static void show(ArrayList<User> us, Config c){
        AnchorPane adm = new AdminWindow(us, c);
        Scene scene = new Scene(adm);
        Stage st = new Stage();
        st.setScene(scene);
        st.sizeToScene();
        st.setResizable(false);
        st.show();
        ((AdminWindow)adm).regExitListener();
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
    
    protected void setUserMenuLabel(){
        try{
        userMenu.setText("Usuario: " + conf.getUser().getUsername());
        }catch(Exception ex){ex.printStackTrace();}
    }
    
    public void loadMainPanel(){
        MainPanel mp = new MainPanel(this);
        Util.changeContent(mp, content);
    }
    
    public void loadMovieListPanel(){
        MovieListPanel ml = new MovieListPanel(this);
        Util.changeContent(ml, content);
    }
    
    public void loadNewMoviePanel(AnchorPane caller){
        AddMoviePanel adm = new AddMoviePanel(this, caller);
        Util.changeContent(adm, content);
    }
    
    public void loadNewRoomPanel(){
        AddRoomPanel rm = new AddRoomPanel(this);
        Util.changeContent(rm, content);
    }
    
    public void loadSchedsPanel(){
        SchedulePanel sc = new SchedulePanel(this);
        Util.changeContent(sc, content);
    }
    
    public void saveUsers(){UserBuilder.writeUsers(users, JCineFX.USERSPATH);}
    public void saveMovies(){MovieBuilder.writeMovies(movies, JCineFX.MOVIES_PATH);}
    public void saveRooms(){RoomBuilder.writeRooms(rooms, JCineFX.ROOMS_PATH);}
    
    public void saveSchedules(){}
    
    private void loadMovies(){
        movies = MovieBuilder.readMovies(JCineFX.MOVIES_PATH);
        for(Movie m: movies){
            System.out.println(m);
        }
    }
    
    private void loadRooms(){
        rooms = RoomBuilder.readRooms(JCineFX.ROOMS_PATH);
        for(RoomLayout r: rooms){
            System.out.println(r);
        }
    }
    
    private void loadSchedules(){
        
    }
    
    protected void regExitListener(){
        EventHandler<WindowEvent> winE = new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                saveConfig(conf);
                saveRooms();
                saveMovies();
            }
        };
        this.getScene().getWindow().setOnCloseRequest(winE);
        this.getScene().getWindow().setOnHiding(winE);
    }
    
    public static void saveConfig(Config conf){
        Config.saveToDisk(JCineFX.CONFIG_PATH, conf);
    }
    
    public void reloadConf(){
        if(conf.getMovCount() == 1 && !movies.isEmpty())
            conf.setMovCount(Config.getSafeCode(Config.MOVIE_COUNTER, movies));
        if(conf.getRoomCount() == 1 && !rooms.isEmpty())
            conf.setRoomCount(Config.getSafeCode(Config.ROOM_COUNTER, rooms));
        System.out.println(conf);
    }
    
    public Window getWindow() {
        return getScene().getWindow();
    }
}