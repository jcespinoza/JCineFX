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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author Jay C Espinoza
 */
public class SillaControl extends Button implements Initializable, ChangeListener<SeatState> {
    private ObservableValue<SeatState> state;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize the button with its state
    }

    @Override
    public void changed(ObservableValue<? extends SeatState> ov, SeatState oldState, SeatState newState) {
        changeSillaColor(newState);
    }

    private void changeSillaColor(SeatState state) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}