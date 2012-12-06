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
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jay C Espinoza
 */
public class TestPane extends AnchorPane implements Initializable{
    @FXML
    private Button helloButton;

    public TestPane(Node parent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TestPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("Failed to to load TestPane");
        }
        //resize the control to completely fill the anchorpane
        AnchorPane.setBottomAnchor(this, 0d);
        AnchorPane.setTopAnchor(this, 0d);
        AnchorPane.setLeftAnchor(this, 0d);
        AnchorPane.setRightAnchor(this, 0d);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("I bee initialized!");
    }
    
    public Button getHelloButton() {
        return helloButton;
    }

    public void setHelloButton(Button helloButton) {
        this.helloButton = helloButton;
    }
    
    @FXML
    private void handleClickMe(ActionEvent e){
        System.out.println("I was clicked!");
    }
}
