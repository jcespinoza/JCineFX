/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.salas.Disenio;
import EDJC.salas.SalaLayout;
import EDJC.salas.sillas.SeatState;
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
    private SalaLayout layout;
    private final int maxRows;
    private final int maxCols;
    private boolean design;
    
    private GridPane grid;
    
    public ChairGrid(SalaLayout lay, boolean design){
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void setActionListeners(){
        EventHandler<MouseEvent> handler;
        if( design ){
            handler = Disenio.getHandler();
        }else{
            handler = null;
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
            grid.getRowConstraints().add(row);
        }
        for(int i = 0; i < maxCols; i++){
            ColumnConstraints cols = new ColumnConstraints();
            cols.setPercentWidth(100/(maxCols));
            cols.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(cols);
        }
    }
}