/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jay C Espinoza
 */
public class ChairGrid extends AnchorPane implements Initializable, EventHandler<MouseEvent> {
    private int countRow;
    private int countCol;
    private final int maxRows;
    private final int maxCols;
    
    @FXML
    private GridPane grid;
    
    public ChairGrid(Parent p, int maxR, int maxC){
        maxRows = maxR;
        maxCols = maxC;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void addChair(Node n){
        grid.add(n, countRow++, countCol++);
        
    }
    
    private void setActionListeners(){
        for(Node sc: this.getChildren()){
            ((Label)sc).setOnMouseClicked(this);
        }
    }

    @Override
    public void handle(MouseEvent t) {
        String text = ((Button)t.getSource()).getText();
        System.out.println("A button with the text: " + text + " was clicked");
    }
}