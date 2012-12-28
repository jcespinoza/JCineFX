/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EDJC.security;

import EDJC.security.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class Config implements Serializable{
    private Usuario user;
    private int roomCount;
    private int movCount;
    private String lastPath;
    
    public Config(){}

    public Config(Usuario user, int roomCount, int movCount, String lastPath) {
        this.user = user;
        this.roomCount = roomCount;
        this.movCount = movCount;
        this.lastPath = lastPath;
    }

    public Usuario getUser() {return user;}

    public void setUser(Usuario user) {
        this.user = user;
    }

    public int getRoomCount() {return roomCount;}

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getMovCount() {return movCount;}

    public void setMovCount(int movCount) {
        this.movCount = movCount;
    }

    public String getLastPath() {return lastPath;}

    public void setLastPath(String lastPath) {
        this.lastPath = lastPath;
    }
    
    public static Config getDefault(){
        Config conf = new Config();
        Usuario s = new Usuario("guest", "password".toCharArray());
        conf.setUser(s);
        conf.setLastPath(".");
        return conf;
    }
    
    public static boolean saveToDisk(String path, Config con){
        try{
            File f = new File(path);
            FileOutputStream fous = new FileOutputStream(f);
            ObjectOutputStream ous = new ObjectOutputStream(fous);
            ous.writeObject(con);
        }catch(Exception ex){return false;}
        
        return true;
    }
    
    public static Config loadFromDisk(String path){
        Config c = null;
        try{
            File f = new File(path);
            FileInputStream fous = new FileInputStream(f);
            ObjectInputStream ous = new ObjectInputStream(fous);
            c = (Config)(ous.readObject());
        }catch(Exception ex){return null;}
        return c;
    }
}