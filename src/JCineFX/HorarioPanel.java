/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.CalendarTextField;
import jfxtras.labs.scene.control.CalendarTimeTextField;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class HorarioPanel extends AnchorPane implements Initializable {
    @FXML
    public GridPane rGrid;
    public SplitPane split;
    private CalendarTextField fechaControl;
    private CalendarTimeTextField timeControl;
    public Button saveButton;
    private TitledPane unclePane;
    
    public HorarioPanel(Node uncle){
        unclePane = (TitledPane)uncle;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewHorarioWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("Failed to to load HorarioPane");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //resize the control to completely fill the anchorpane
        setBottomAnchor(rGrid, 0d);
        setTopAnchor(rGrid, 0d);
        setLeftAnchor(rGrid, 0d);
        setRightAnchor(rGrid, 0d);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rGrid.setGridLinesVisible(true);
        fechaControl = new CalendarTextField();
        timeControl = new CalendarTimeTextField();
        timeControl.setShowLabels(Boolean.TRUE);
        //column, row
        rGrid.add(fechaControl, 2, 2);
        rGrid.add(timeControl, 2, 3);                
    }
    
    @FXML
    private void handleSave(ActionEvent e){
        unclePane.setExpanded(true);
    }
}