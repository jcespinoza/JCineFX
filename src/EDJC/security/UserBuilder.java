/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Edgardo Castellanos
 */
public class UserBuilder {
    private static RandomAccessFile raf;      

    private UserBuilder(){}

    public static User readUser(String username, String path){
        try{
            raf = new RandomAccessFile(path, "r");
            raf.seek(0);
        while(raf.getFilePointer() < raf.length()){
            User us = new User();
            us.setUsername(raf.readUTF());
            User param = new User();
            param.setUsername(username);

            if(us.equals(param)){
                us.setPassword(raf.readUTF().toCharArray());
                us.setFullName(raf.readUTF());
                us.setFotoPath(raf.readUTF());
                us.setFilePointer(raf.readLong());
                us.setCredencialActiva(raf.readBoolean());
                if(us.isCredencialActiva()){
                    return us;
                }
            }else{
                raf.readUTF();
                raf.readUTF();
                raf.readUTF();
                raf.readBoolean();
            }
        }
        raf.close();
        return null;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;}
    }
    
    public static ArrayList<User> readUsers(String path){
        ArrayList<User> li = new ArrayList<>();
        
        try{
            raf = new RandomAccessFile(path, "rw");
            raf.seek(0);
        while(raf.getFilePointer() < raf.length()){
            User us = new User();
            us.setFilePointer(raf.getFilePointer());
            us.setUsername(raf.readUTF());

            us.setPassword(raf.readUTF().toCharArray());
            us.setFullName(raf.readUTF());
            us.setFotoPath(raf.readUTF());
            us.setFilePointer(raf.readLong());
            us.setCredencialActiva(raf.readBoolean());
            li.add(us);
        }
        raf.close();
        }catch(Exception ex){
            ex.printStackTrace();
            return li;
        }
        
        return li;
    }
    
    @Deprecated
    public static User leerUser(String username) throws IOException{
        return readUser(username, "cinefilos.mov");
    }
    
    public static boolean writeUser(User user, String path){
        try{
            raf = new RandomAccessFile(path, "rw");
            raf.seek(raf.length());
            long pointer = raf.getFilePointer();
            raf.writeUTF(user.getUsername());
            raf.writeUTF(new String(user.getPassword()));
            raf.writeUTF(user.getFullName());
            raf.writeUTF(user.getFotoPath());
            raf.writeLong(pointer);
            raf.writeBoolean(true);
            raf.close();
            return true;
        }catch(Exception ex ){
            ex.printStackTrace();
            return false;}
    }
    
    public static boolean writeUsers(ArrayList<User> users, String path){
        ArrayList<User> backup = readUsers(path);
        File bak = new File(path);
        bak.renameTo(new File(path + ".bak"));
        File f = new File(path);
        try{
            for(User u: users){
                writeUser(u, path);
            }
            new File(path + ".bak").delete();
        }catch(Exception ex){
            ex.printStackTrace();
            f.delete();
            bak.renameTo(f);
            return false;
        }
        return true;
    }

    public static void writeUser(User user) throws FileNotFoundException, IOException{
        deactivateUser(user.getUsername());
        writeUser(user, "cinefilos.mov");
    }
    
    @Deprecated
    public static void deactivateUser(String username) throws FileNotFoundException, IOException {
        raf = new RandomAccessFile("cinefilos.mov", "rw");
        raf.seek(0);
        while(raf.getFilePointer() < raf.length()){
            if(raf.readUTF().equals(username)){
                raf.readUTF();
                raf.readUTF();
                raf.readUTF();
                raf.writeBoolean(false);
                //return;
            }else{
                raf.readUTF();
                raf.readUTF();
                raf.readUTF();
                raf.readBoolean();
            }
        }
        raf.close();
    }
    
    public static ArrayList<User> defaultList(){
        ArrayList<User> users = new ArrayList<>();
        User u = new User("guest", "password".toCharArray());
        u.setFilePointer(0);
        u.setFullName("Administrator");
        u.setFotoPath("");
        users.add(u);
        return users;
    }
}