/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.movies.Movie;
import EDJC.movies.MovieBuilder;
import EDJC.rooms.Schedule;
import EDJC.rooms.ScheduleBuilder;
import EDJC.rooms.RoomBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import jfxtras.labs.scene.control.CalendarTextField;
import jfxtras.labs.scene.control.CalendarTimeTextField;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class HorarioPanel extends AnchorPane implements Initializable, EventHandler<MouseEvent> {
    @FXML
    public GridPane rGrid;
    public SplitPane split;
    private CalendarTextField fechaControl;
    private CalendarTimeTextField timeControl;
    public Button saveButton;
    private TitledPane unclePane;
    public ImageView movImg;
    public VBox vboxPane;
    public ChoiceBox salasChoiceBox;
    private Movie peli;
    public Label tipoSala;
    private ArrayList<String> salas;
    private final AdminWindowController admin;
    
    public HorarioPanel(Node uncle, AdminWindowController ad){
        this.admin = ad;
        salas = new ArrayList<>();
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
        //rGrid.setGridLinesVisible(true);
        fechaControl = new CalendarTextField();
        timeControl = new CalendarTimeTextField();
        timeControl.setShowLabels(Boolean.TRUE);
        //column, row
        rGrid.add(fechaControl, 2, 2);
        rGrid.add(timeControl, 2, 3);
        tipoSala.setText("");
        salasChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                tipoSala.setText(salas.get(t1.intValue()));
            }
        });
        MovieBuilder.fillMoviesPanel(vboxPane, this);
        emptySalaChoices();
        RoomBuilder.fillSalaChoices(salasChoiceBox, salas);
    }
    
    public void emptyMoviesPanel(){
        vboxPane.getChildren().removeAll(vboxPane.getChildren());
    }

    public void emptySalaChoices(){
        salasChoiceBox.getItems().removeAll(salasChoiceBox.getItems());
    }
    
    @FXML
    private void handleSave(ActionEvent e){
        unclePane.setCollapsible(true);
        unclePane.setExpanded(true);
        unclePane.setCollapsible(false);
        int codSala = Integer.parseInt( (String)salasChoiceBox.getValue() );
        if( ScheduleBuilder.dataMakeSense(peli, codSala, fechaControl, timeControl) ){
            try {
                if( ScheduleBuilder.areCompatible(codSala, peli)){/*
                    Schedule h = new Schedule(peli, fechaControl.getValue(), timeControl.getValue());
                    ScheduleBuilder.escribirHorario(codSala, h);
                    RoomBuilder.escribirSalaT(RoomBuilder.leerSala(codSala), h);*/
                }
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }else{
            System.out.println("Doesnt make sense");
        }
        admin.loadHorarios();
    }

    @Override
    public void handle(MouseEvent t) {
        MovieTile mov = ((MovieTile)t.getSource());
        peli = mov.getPelicula();
        movImg.setImage(new Image(peli.getImgFile()));
    }
}