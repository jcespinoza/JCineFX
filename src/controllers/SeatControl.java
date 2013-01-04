/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.rooms.seats.SeatState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class SeatControl extends AnchorPane implements Initializable{
    private SeatState state;
    private int row;
    private int number;
    
    public Label label;

    public SeatControl(SeatState s, int row, int col){
        state = s;
        this.row = row;
        this.number = col;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/SeatControl.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){ex.printStackTrace();}
        //the size of the AnchorPane
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //the size of the Label
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setState(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText("" + (char)(65+row) + (number + 1));
    }
    
    public SeatState getState(){
        return state;
    }

    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }
    
    public void setState(SeatState s){
        state = s;
        changeSillaColor(state);
    }
    
    private void changeSillaColor(SeatState s) {
        switch(s){
            case AVAILABLE: case SELECTED: case RESERVED:
                label.setText(getName());
                break;
            default:
                label.setText("");
        }
        label.setId(s.name());       
    }

    public String getName() {
        return "" + (char)(65 + row) + (number+1);
    }
    
    public void setListener(EventHandler<MouseEvent> e){
        setOnMouseClicked(e);
    }
}