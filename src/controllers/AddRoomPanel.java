package controllers;

import EDJC.rooms.RoomLayout;
import EDJC.rooms.seats.SeatState;
import EDJC.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class AddRoomPanel extends AnchorPane implements Initializable{
    private AdminWindow father;
    private RoomLayout room;
    private int cols;
    private int rows;
    private boolean is3D;
    private ChangeListener fListener;
    
    //FXML variables
    public ComboBox typeChoice;
    public TextField rowsT;
    public TextField colsT;
    public Label rowsMsg;
    public Label colsMsg;
    public Label typeMsg;
    
    public AddRoomPanel(AdminWindow ad){
        father = ad;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/AddRoomPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initFocusListener();
        regFocusListener();
    }
    
    private boolean checkRows(){
        if( Util.puedeConvertirse(rowsT.getText()) ){
            int t = Integer.parseInt(rowsT.getText());
            if( t < 1 )
                rowsMsg.setText("El valor de las filas es muy bajo.");
            else{
                rows = t;
                return true;}
        }else
            rowsMsg.setText("El valor de las filas es inválido");
        return false;
    }
    
    private boolean checkCols(){
        if( Util.puedeConvertirse(colsT.getText()) ){
            int c = Integer.parseInt(colsT.getText());
            if( c < 1 )
                colsMsg.setText("El valor de los asientos por fila es muy bajo.");
            else{
                cols = c;
                return true;}
        }else
            colsMsg.setText("El valor de los aslientos por fila es inválido");
        return false;
    }
    
    private boolean checkType(){
        if ( typeChoice.getValue() == null){
            typeMsg.setText("Debe seleccionar un tipo de Sala");
            return false;
        }
        if( typeChoice.getValue().toString().equals("Sala 3D") )
            is3D = true;
        else
            is3D = false;
        return true;
    }
    
    private void createRoom(){
        room = RoomLayout.genSalaLayout(rows, cols, SeatState.AVAILABLE, is3D);
        room.setCode(father.conf.getRoomCount());
    }

    private void initFocusListener() {
        fListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue focusProperty, Object old, Object newVal) {
                Node control = (Node)( ((ReadOnlyBooleanProperty)(focusProperty)).getBean() );
                boolean focus = (Boolean)newVal;
                
                if(control.equals(typeChoice))
                    handleTypeFocus(focus);
                else if( control.equals(rowsT))
                    handleRowFocus(focus);
                else
                    handleColsFocus(focus);
            }
        };
    }

    private void regFocusListener(){
        rowsT.focusedProperty().addListener(fListener);
        colsT.focusedProperty().addListener(fListener);
        typeChoice.focusedProperty().addListener(fListener);
    }
    
    private void handleTypeFocus(boolean focus) {
        if( focus )
            typeMsg.setText(null);
        else
            checkType();
    }

    private void handleColsFocus(boolean focus) {
        if( focus )
            colsMsg.setText(null);
        else
            checkCols();
    }

    private void handleRowFocus(boolean focus) {
        if( focus )
            rowsMsg.setText(null);
        else
            checkRows();
    }
    
    @FXML
    private void handleDesign(){
        if( checkCols() & checkRows() & checkType() ){
            createRoom();
            RoomDesign.show(father, room);
        }
    }
}