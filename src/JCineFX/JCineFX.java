/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.seguridad.JCineIO;
import EDJC.seguridad.UserBuilder;
import EDJC.seguridad.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
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
    public static final String confFilePath = "config.mov";
    private static int salaCounter;
    private static Usuario currentUser;
    private static Configuracion conf;
    
    @Override
    public void start(Stage stage) throws Exception {
        crearTodo();
        conf = leerConf();
        salaCounter = conf.getContador();
        currentUser = conf.getUsuarioActual();
        
        Parent root = FXMLLoader.load(getClass().getResource("ModSelection.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        System.out.println("Counter: "  + getSalaCounter());
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
    
    private static void crearTodo() throws FileNotFoundException, IOException{
        File confFile = new File(JCineFX.confFilePath);
        //ver si el archivo de configuracion existe
        if(!confFile.exists()){
            conf = new Configuracion();
            FileOutputStream fos = new FileOutputStream(JCineFX.confFilePath);
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(conf);

            File salas = new File("salas");
            if(!salas.exists())
                salas.mkdir();

            File horarios = new File("horarios");
            if(!horarios.exists())
                horarios.mkdir();
            
            RandomAccessFile raf = new RandomAccessFile("cinefilos.mov", "rw");
            if( raf.length() == 0){
                raf.close();
                Usuario nUser = new Usuario("guest", "password".toCharArray());
                nUser.SetNombreCompleto("Administrator");
                nUser.setFotoPath("src/res/user-icon-big.png");
                UserBuilder.escribirUser(nUser);
            }
            
            //si el archivo de configuracion existe no es necesario crear nada
        }else{
            System.out.println(confFile.getPath() + " ya existia");
        }
    }

    public static Configuracion leerConf() throws FileNotFoundException, IOException, ClassNotFoundException {
        File confFile = new File(JCineFX.confFilePath);
        
        if(confFile.exists()){
            FileInputStream fis = new FileInputStream(confFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Configuracion)(ois.readObject());
        }
        return new Configuracion();
    }
    
    public static void escribirConf(Configuracion conf) throws FileNotFoundException, IOException{
        File confFile = new File(JCineFX.confFilePath);
        
        if(!confFile.exists()){
            FileOutputStream fos = new FileOutputStream(confFile);
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(conf);
        }
    }
}