/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.seguridad.JCineIO;
import EDJC.seguridad.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Jay C Espinoza
 */
public class JCineFX extends Application{
    private static int salaCounter;
    private static Usuario currentUser;
    
    @Override
    public void start(Stage stage) throws Exception {
        JCineIO.setFileStuff();
        setData();
        
        Parent root = FXMLLoader.load(getClass().getResource("ModSelection.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        System.out.println("Counter: ");
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setContadorSalas() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static int getSalaCounter() {
        return salaCounter;
    }

    public static void setSalaCounter(int salaCounter) {
        JCineFX.salaCounter = salaCounter;
    }

    public static Usuario getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Usuario currentUser) {
        JCineFX.currentUser = currentUser;
    }

    private void setData() {
        setSalaCounter(JCineIO.getCurrentCounter());
    }
}