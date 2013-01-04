package controllers;

import EDJC.rooms.RoomLayout;
import EDJC.rooms.seats.SeatState;
import EDJC.security.Config;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class RoomDesign extends AnchorPane implements Initializable, EventHandler<MouseEvent>{
    private AdminWindow father;
    private RoomLayout layout;
    private GridPane grid;
    
    @FXML
    private BorderPane mainPane;
    
    public RoomDesign(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/RoomDesign.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }
    
    public RoomDesign(AdminWindow owner, RoomLayout r){
        this();
        father = owner;
        layout = r;
        createSeatGrid();
        addGridPane();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public static void show(AdminWindow w, RoomLayout r){
        AnchorPane rm = new RoomDesign(w, r);
        Scene scene = new Scene(rm);
        Stage st = new Stage();
        st.setScene(scene);
        st.setHeight(600);
        st.setWidth(800);
        st.setMinWidth(400);
        st.setMinHeight(400);
        st.initModality(Modality.WINDOW_MODAL);
        st.initOwner(w.getWindow());
        st.show();
    }

    private void createSeatGrid() {
        grid = new SeatGrid(layout, this);
        //grid = new GridPane();
        //setConstraints(layout.getRows(), layout.getCols());
        //addElements(layout);
    }

    @Override
    public void handle(MouseEvent t) {
        //change seat color
        SeatControl s = ((SeatControl)t.getSource());
        if( s.getState() == SeatState.AVAILABLE)
            s.setState(SeatState.HIDDEN);
        else if( s.getState() == SeatState.HIDDEN)
            s.setState(SeatState.AVAILABLE);
        layout.setSeat(s.getRow(), s.getNumber(), s.getState());
    }

    private void addGridPane() {
        mainPane.setCenter(grid);
    }
    
    @FXML
    private void handleSave(){
        father.rooms.add(layout);
        father.conf.incrementCounter(Config.ROOM_COUNTER);
        MessageBox.show(this.getScene().getWindow(), "Sala Guardada", "El disenio de la sala se guardó exitosamente.");
        this.getScene().getWindow().hide();
        father.loadNewRoomPanel();
        System.out.println(layout);
    }
}