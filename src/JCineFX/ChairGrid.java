/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jay C Espinoza
 */
public class ChairGrid extends AnchorPane implements Initializable{
    private int countRow;
    private int countCol;
    private final int maxRows;
    private final int maxCols;
    
    private GridPane grid;
    
    public ChairGrid(Parent p, int maxR, int maxC, boolean design){
        maxRows = maxR;
        maxCols = maxC;
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);*/
        grid = new GridPane();
        addElements();
        setActionListeners(design);
        this.getChildren().add(grid);
        System.out.println("Constructor terminated");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void addChair(Node n){
        grid.add(n, countRow++, countCol++);
        
    }
    
    private void setActionListeners(boolean design){
        EventHandler<MouseEvent> handler;
        if( design ){
            handler = new Disenio();
        }else{
            handler = null;
        }
        for(Node sc: this.grid.getChildren()){
            sc.setOnMouseClicked(handler);
        }
        System.out.println("Listeners set");
    }

    private void addElements() {
        for(int i = 0; i < maxRows; i++){
            for(int k = 0; i < maxCols; k++){
                grid.add(new SillaControl(), k, i);
            }
        }
        System.out.println("Finished addeing elements");
    }
}