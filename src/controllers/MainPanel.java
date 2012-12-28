/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class MainPanel extends AnchorPane implements Initializable{
    private AdminWindow father;
    
    public MainPanel(AdminWindow ad){
        father = ad;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/MainPanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void handleAddSala(){
        AddRoomPanel ad = new AddRoomPanel(father);
        Util.changeContent(ad, father.content);
    }
    
    @FXML
    private void handleAddMovie(){
        father.loadNewMoviePanel();
    }
    
    @FXML
    private void handleModProfile(){
        ProfilePanel adm = new ProfilePanel(father);
        Util.changeContent(adm, father.content);
    }
    
    @FXML
    private void handleListMovies(){
        MovieListPanel mo = new MovieListPanel(father);
        Util.changeContent(mo, father.content);
    }
    
    @FXML
    private void handleListScheds(){
        father.loadSchedsPanel();
    }
}