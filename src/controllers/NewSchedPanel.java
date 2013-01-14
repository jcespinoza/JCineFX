package controllers;

import EDJC.movies.Movie;
import EDJC.rooms.RoomLayout;
import EDJC.rooms.SchedEntry;
import EDJC.rooms.Schedule;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import jfxtras.labs.scene.control.CalendarTimeTextField;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class NewSchedPanel extends AnchorPane implements Initializable, EventHandler<MouseEvent> {
    private AdminWindow father;
    private Movie selMovie;
    private RoomLayout room;
    private SchedEntry sched;
    private CalendarTimeTextField time;
    private ChangeListener fListen;
    
    //FXML
    public ComboBox roomChoice;
    public VBox vboxPane;
    public ImageView movPic;
    public GridPane grid;
    public Label roomType;
    public Label movMsg;
    public Label roomMsg;
    public Label timeMsg;
    
    public NewSchedPanel(AdminWindow ad){
        father = ad;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/NewSchedPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMovies();
        addTimePicker();
        loadRoomCodes();
        initFocusListener();
    }

    @FXML
    private void handleSave(){
        if( checkMovie() & checkRoom() & checkTime()){
            createScheduleEntry();
            int index = father.scheds.indexOf(new Schedule(room.getCode()));
            if( index == -1){
                Schedule s = new Schedule(room.getCode());
                s.addEntry(sched);
                father.scheds.add(s);
            }
            father.loadSchedsPanel();
        }
    }
    
    private void loadMovies() {
        //Fetch movies from father's arrayList
        for(Movie m: father.movies){
            MovieTile mt = new MovieTile(m);
            mt.setOnMouseClicked(this);
            vboxPane.getChildren().add(mt);
        }
    }

    @Override
    public void handle(MouseEvent t) {
        MovieTile mt = ((MovieTile)(t.getSource()));
        selMovie = mt.getMovie();
        movPic.setImage(new Image(selMovie.getImgFile()));
    }
    
    private void loadRoomCodes() {
        roomChoice.getItems().removeAll(roomChoice.getItems());
        for(RoomLayout r: father.rooms){
            roomChoice.getItems().add(r.getCode());
        }
        roomChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldV, Object newV) {
                int s = (Integer)newV;
                int t = father.rooms.indexOf( new RoomLayout(s) );
                room = father.rooms.get(t);
                String v = (room.is3D())?"3D":"2D";
                roomType.setText(v);
            }
        });
    }

    private void addTimePicker() {
        time = new CalendarTimeTextField()
                .withPromptText("Seleccione la hora")
                .withShowLabels(Boolean.TRUE);
        GridPane.setConstraints(time, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        grid.add(time, 1, 4);
    }
    
    private void createScheduleEntry(){
        sched = new SchedEntry(room.getCode(), selMovie, time.getValue());
    }
    
    private boolean checkMovie(){
        movMsg.setText(null);
        if(selMovie == null)
            movMsg.setText("No ha seleccionado una película");
        else
            if(room != null)
                if( !room.is3D() && selMovie.is3D())
                    movMsg.setText("La película que eligió es incompatible con la sala");
                else
                    return true;
            
        return false;
    }
    
    private boolean checkRoom(){
        roomMsg.setText(null);
        if(room == null)
            roomMsg.setText("No ha seleccionado una sala");
        else
            return true;
        return false;
    }
    
    private boolean checkTime(){
        try{
            timeMsg.setText(null);
            time.getValue().get(Calendar.MINUTE);
            return true;
        }catch(Exception ex){
            timeMsg.setText("La hora que ha introducido es inválida");
        }
        return false;
    }

    private void initFocusListener() {
        fListen = new ChangeListener() {
            @Override
            public void changed(ObservableValue focusProperty, Object old, Object newValue) {
                //cast to ReadOnlyProperty, then to Node, to get the control that owns the changed property
                Node control = (Node)( ((ReadOnlyProperty)(focusProperty)).getBean() );
                boolean focus = (Boolean)newValue;

                if(control.equals(roomChoice))
                    handleRoomFocus(focus);
            }
        };
        
        roomChoice.focusedProperty().addListener(fListen);
    }
    
    private void handleRoomFocus(boolean focus) {
        if(!focus)
            checkRoom();
        else
            roomMsg.setText(null);
    }
}