package controllers;

import EDJC.rooms.RoomLayout;
import EDJC.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * @author Juan Carlos Espinoza
 */
public class SchedulePanel extends AnchorPane implements Initializable{
    private AdminWindow father;

    //fxml
    public ComboBox roomChoice;
    public HBox mPane;

    public SchedulePanel(AdminWindow ad){
        father = ad;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/SchedulePanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadRoomCodes();
        setSelectedCode();
        loadSchedules();
    }

    @FXML
    private void handleNewSched(){
        NewSchedPanel ns = new NewSchedPanel(father);
        Util.changeContent(ns, father.content);
    }

    private void setSelectedCode() {
        roomChoice.getSelectionModel().select(father.conf.getSelectedRoom());
    }

    private void loadRoomCodes() {
        roomChoice.getItems().removeAll(roomChoice.getItems());
        for(RoomLayout r: father.rooms){
            roomChoice.getItems().add(r.getCode());
        }
    }

    private int getSelectedCode(){
        int t = -1;
        try{
            t = Integer.parseInt((String)( roomChoice.getValue() ));
        }catch(Exception ex){};
        return t;
    }

    private void loadSchedules() {
        father.conf.
    }
}