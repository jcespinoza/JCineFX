/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.security.Configuracion;
import controllers.ModSelection;
import EDJC.security.UserBuilder;
import EDJC.security.User;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author Jay C Espinoza
 */
public class JCineFX extends Application{
    public static final String CONFIG_PATH = "config.conf";
    public static final String USERSPATH = "users.usr";
    public static final String MOVIES_PATH = "movies.movs";
    public static final String ROOMS_PATH = "rooms/";
    public static final String SCHEDULESPATH = "schedules/";
    private static int salaCounter = 1;
    private static int movCounter = 1;
    private static User currentUser;
    private static Configuracion conf;
   
    @Override
    public void start(Stage stage) throws Exception {
        /*crearTodo();
        conf = leerConf();
        salaCounter = conf.getContadorSala();
        currentUser = UserBuilder.leerUser( conf.getUsuarioActual() );*/
        ModSelection.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static int getMovCounter() throws IOException {
        movCounter = leerConf().getContadorPeli();
        return movCounter;
    }

    public static void setMovCounter(int movCounter) throws IOException {
        JCineFX.movCounter = leerConf().getContadorPeli() + 1;
    }

    public static int getSalaCounter() throws IOException {
        return salaCounter;
    }

    public static void setSalaCounter(int salaCounter) throws IOException{
        JCineFX.salaCounter = leerConf().getContadorSala() + 1;
    }

    public static User getCurrentUser() throws IOException{
        currentUser = UserBuilder.leerUser( leerConf().getUsuarioActual() );
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) throws IOException {
        actualizarConf(currentUser);
        JCineFX.currentUser = UserBuilder.leerUser(leerConf().getUsuarioActual());
    }
    
    private static void crearTodo() throws IOException{
        File confFile = new File(JCineFX.CONFIG_PATH);
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
                User nUser = new User("guest", "password".toCharArray());
                nUser.setFullName("Administrator");
                nUser.setPicturePath("file:JCineFX.jar!/res/user-icon-big.png" );
                UserBuilder.writeUser(nUser);
            }
            
            raf = new RandomAccessFile("peliculas.mov", "rw");
            
            //si el archivo de configuracion existe no es necesario crear nada
        }
    }

    public static Configuracion leerConf() throws IOException {
        File confFile = new File(JCineFX.CONFIG_PATH);
        
        if(confFile.exists()){
            RandomAccessFile raf = new RandomAccessFile(confFile, "r");
            String user = raf.readUTF();
            String dir = raf.readUTF();
            int countSala = raf.readInt();
            int countMov = raf.readInt();
            
            Configuracion c = new Configuracion();
            c.setUsuarioActual(user);
            c.setDirectorio(dir);
            c.setContadorSala(countSala);
            c.setContadorPeli(countMov);
            
            return c;
        }
        return new Configuracion();
    }
    
    public static void escribirConf(Configuracion conf) throws IOException{
        File confFile = new File(JCineFX.CONFIG_PATH);
        RandomAccessFile raf = new RandomAccessFile(confFile, "rw");
        raf.writeUTF(conf.getUsuarioActual());
        raf.writeUTF(conf.getDirectorio());
        raf.writeInt(conf.getContadorSala());
        raf.writeInt(conf.getContadorPeli());
    }
    
    public static Configuracion actualizarConf(User u) throws IOException{
        Configuracion conf = leerConf();
        conf.setUsuarioActual(u.getUsername());
        escribirConf(conf);
        JCineFX.conf = leerConf();
        return conf;
    }
    
    public static void aumentarContMov() throws IOException{
        Configuracion conf = leerConf();
        conf.setContadorPeli( conf.getContadorPeli() + 1);
        escribirConf(conf);
        JCineFX.conf = leerConf();
    }
    
    public static void aumentarContSala() throws IOException{
        Configuracion conf = leerConf();
        conf.setContadorSala( conf.getContadorSala() + 1);
        escribirConf(conf);
        JCineFX.conf = leerConf();
    }
}