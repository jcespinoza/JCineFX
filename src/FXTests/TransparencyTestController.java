/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FXTests;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class TransparencyTestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void handleLabelClick(MouseEvent e){
        Label l = ((Label)e.getSource() );
        System.out.println("Opacity: " + l.getOpacity());
        if( l.getOpacity() == 0.0){
            l.setOpacity(1);
        }else{
            l.setOpacity(0);
        }
    }
}
