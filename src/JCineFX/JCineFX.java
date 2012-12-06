/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

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
        currentUser = UserBuilder.leerUser( conf.getUsuarioActual() );
        
        Parent root = FXMLLoader.load(getClass().getResource("ModSelection.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        System.out.println("Counter: "  + getSalaCounter());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static int getSalaCounter() throws FileNotFoundException, IOException, ClassNotFoundException {
        salaCounter = leerConf().getContador();
        return salaCounter;
    }

    public static void setSalaCounter(int salaCounter) throws FileNotFoundException, IOException, ClassNotFoundException {
        JCineFX.salaCounter = leerConf().getContador() + 1;
    }

    public static Usuario getCurrentUser() throws FileNotFoundException, IOException, ClassNotFoundException {
        currentUser = UserBuilder.leerUser( leerConf().getUsuarioActual() );
        return currentUser;
    }

    public static void setCurrentUser(Usuario currentUser) throws ClassNotFoundException, IOException {
        actualizarConf(currentUser);
        JCineFX.currentUser = UserBuilder.leerUser(leerConf().getUsuarioActual());
    }
    
    private static void crearTodo() throws FileNotFoundException, IOException{
        File confFile = new File(JCineFX.confFilePath);
        //ver si el archivo de configuracion existe
        if(!confFile.exists()){
            conf = new Configuracion();
            escribirConf(conf);

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

    public static Configuracion leerConf() throws FileNotFoundException, IOException {
        File confFile = new File(JCineFX.confFilePath);
        
        if(confFile.exists()){
            RandomAccessFile raf = new RandomAccessFile(confFile, "r");
            String user = raf.readUTF();
            String dir = raf.readUTF();
            int count = raf.readInt();
            
            Configuracion c = new Configuracion();
            c.setUsuarioActual(user);
            c.setDirectorio(dir);
            c.setContador(count);
            
            System.out.println("Read: Returning " + c);
            return c;
        }
        return new Configuracion();
    }
    
    public static void escribirConf(Configuracion conf) throws FileNotFoundException, IOException{
        File confFile = new File(JCineFX.confFilePath);
        System.out.println("Conf is: " + conf);
        RandomAccessFile raf = new RandomAccessFile(confFile, "rw");
        raf.writeUTF(conf.getUsuarioActual());
        raf.writeUTF(conf.getDirectorio());
        raf.writeInt(conf.getContador());

        /*if(!confFile.exists()){
            raf.writeInt(1);
        }*/
    }
    
    public static Configuracion actualizarConf(Usuario u) throws FileNotFoundException, IOException, ClassNotFoundException{
        Configuracion conf = leerConf();
        conf.setUsuarioActual(u.getUsername());
        escribirConf(conf);
        System.out.println("escrito: " + conf);
        JCineFX.conf = leerConf();
        return conf;
    }
}