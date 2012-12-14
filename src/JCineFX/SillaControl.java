/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.salas.sillas.SeatState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Jay C Espinoza
 */
public class SillaControl extends Label implements Initializable, ChangeListener<SeatState> {
    private ObservableValue<SeatState> state;
    private WritableValue<SeatState> st;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize the button with its state
    }
    
    public SillaControl(SeatState s){
        super("X");
        state = ((ObservableValue<SeatState>)(SeatState.valueOf(s.name())) );
    }

    public SeatState getState(){
        return state.getValue();
    }
    
    public void setState(SeatState s){
        SeatState s2 = this.state.getValue();
        s2 = s;
    }
    
    @Override
    public void changed(ObservableValue<? extends SeatState> ov, SeatState oldState, SeatState newState) {
        changeSillaColor(newState);
    }

    private void changeSillaColor(SeatState state) {
        System.out.println("I should change color");
    }
}