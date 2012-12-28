/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class ClientWindow extends AnchorPane implements Initializable{
    public AnchorPane content;
    private int[] tickets;
    private String clientName;
    private int nTickets;
    
    public ClientWindow(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/ClientWindow.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCartelera();
        resetClientData();
    }
    
    public static void show(){
        AnchorPane cli = new ClientWindow();
        Scene scene = new Scene(cli);
        Stage st = new Stage();
        st.setScene(scene);
        st.sizeToScene();
        st.setResizable(false);
        st.show();
    }
    
    @FXML
    private void handleGoAdmin(){
        LoginRegister.show();
        this.getScene().getWindow().hide();
    }
    
    @FXML
    private void handleExit(){Platform.exit();}
    
    public void loadCartelera(){
        Cartelera car = new Cartelera(this);
        Util.changeContent(car, content);
    }
    
    public void loadTicketPanel(){
        TicketPanel ti = new TicketPanel(this);
        Util.changeContent(ti, content);
    }
    
    public void setClientName(String name){clientName = name;}
    
    public String getClientName(){ return clientName;}
    
    public void setTickets(int adul, int menor, int ter){
        tickets[0] = adul;
        tickets[1] = menor;
        tickets[2] = ter;
        nTickets = adul + menor + ter;
    }
    
    public int getNTickets(){return nTickets;}
    
    public int[] getTickets(){return tickets;}

    public void resetClientData(){
        clientName = null;
        tickets = new int[3];
        nTickets = 0;
    }
}