/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.rooms.Design;
import EDJC.rooms.RoomLayout;
import EDJC.rooms.seats.SeatState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Jay C Espinoza
 */
public class ChairGrid extends AnchorPane implements Initializable{
    private RoomLayout layout;
    private final int maxRows;
    private final int maxCols;
    private boolean design;
    private EventHandler<MouseEvent> handler;
    
    private GridPane grid;
    
    public ChairGrid(RoomLayout lay, boolean design){
        this.design = design;
        layout = lay;
        maxRows = lay.getFilas();
        maxCols = lay.getCols();
        grid = new GridPane();
        addElements();
        setConstrainst();
        setActionListeners();
        this.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);
        setTopAnchor(grid, 0.0);
        setBottomAnchor(grid, 0.);
        setLeftAnchor(grid, 0.0);
        setRightAnchor(grid, 0.0);
        setPrefSize(maxCols*100, maxRows*100);
        setMaxSize(800, 600);
    }

    ChairGrid(RoomLayout sala, EventHandler<MouseEvent> handler) {
        this(sala, false);
        this.handler = handler;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void setActionListeners(){
        EventHandler<MouseEvent> handler;
        if( design ){
            handler = Design.getHandler();
        }else{
            handler = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    SillaControl s = ((SillaControl)t.getSource());
                    if( s.getState() == SeatState.DISPONIBLE)
                        s.setState(SeatState.SELECCIONADO);

                    else if( s.getState() == SeatState.SELECCIONADO)
                        s.setState(SeatState.DISPONIBLE);
                    layout.setSilla(s.getRow(), s.getNumber(), s.getState());
                }
            };
        }
        for(Node sc: this.grid.getChildren()){
            sc.setOnMouseClicked(handler);
        }
    }

    private void addElements() {
        //grid.setGridLinesVisible(true);
        SeatState s;
        for(int i = 0; i < maxRows; i++){
            for(int k = 0; k < maxCols; k++){
                s = layout.getSeatState(i, k);
                SillaControl silla = new SillaControl(s, i, k);
                grid.add(silla, k, i);
            }
        }
    }

    private void setConstrainst() {
        for(int i = 0; i < maxRows; i++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100/maxRows);
            row.setValignment(VPos.CENTER);
            row.setFillHeight(true);
            grid.getRowConstraints().add(row);
        }
        for(int i = 0; i < maxCols; i++){
            ColumnConstraints cols = new ColumnConstraints();
            cols.setPercentWidth(100/(maxCols));
            cols.setHalignment(HPos.CENTER);
            cols.setFillWidth(true);
            grid.getColumnConstraints().add(cols);
        }
    }
}