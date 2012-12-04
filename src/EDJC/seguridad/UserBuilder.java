/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.seguridad;

import EDJC.seguridad.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgardo Castellanos
 */
public class UserBuilder {
    private static RandomAccessFile raf;
    
    ArrayList<Usuario> users;
    Usuario user;
    
        public UserBuilder(File[] files){
            for(int i=0; i<files.length; i++){
            try{
                RandomAccessFile usuario=new RandomAccessFile("user_"+user.getNombreCompleto(),"rw");
                usuario.writeUTF("user");
                usuario.writeUTF(user.getUsername());
                usuario.writeUTF(user.getNombreCompleto());
                //password :S
            }catch(Exception e ){
                System.out.println("Error: "+e.getMessage());
            }
            
            }
        }
        
        public UserBuilder(String username, String nombre, char[] Password){
            try{
           RandomAccessFile usuario=new RandomAccessFile("user_"+username,"rw");
           usuario.writeUTF("user");
           usuario.writeUTF(username);
           usuario.writeUTF(nombre);
           //password
            }catch(Exception e ){
                System.out.println("Error: "+e.getMessage());
            }
        }
        
        public UserBuilder(File file){
            try{
            RandomAccessFile usuario=new RandomAccessFile("user_"+user.getNombreCompleto(),"rw");
            usuario.writeUTF("user");
            usuario.writeUTF(user.getUsername());
            usuario.writeUTF(user.getNombreCompleto());
           //password :S
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
        
        public UserBuilder(){
            
        }
        
        public static Usuario leerUser(String username){
            try {
                RandomAccessFile raf = new RandomAccessFile("cinefilos.mov", "r");
                raf.seek(0);
                Usuario leido = new Usuario();
                leido.setUsername(raf.readUTF());
                Usuario param = new Usuario();
                param.setUsername(username);
                
                if(leido.equals(param)){
                    leido.setPassword(raf.readUTF().toCharArray());
                    leido.SetNombreCompleto(raf.readUTF());
                    leido.setFotoPath(raf.readUTF());
                    leido.setCredencialActiva(raf.readBoolean());
                    return leido;
                }
            } catch (Exception ex) {
                System.out.println("");
            }
            return null;
        }
        
        public static void escribirUser(Usuario user){
            desactivarUsuario(user.getUsername());
            try{
                raf = new RandomAccessFile("cinefilos.mov", "w");
                raf.seek(raf.length());
                raf.writeUTF(user.getUsername());
                raf.writeUTF(new String(user.getPassword()));
                raf.writeUTF(user.getNombreCompleto());
                raf.writeUTF(user.getFotoPath());
                raf.writeBoolean(true);
            }catch(Exception ex){
                
            }
        }
    private static void desactivarUsuario(String username) {
        try {
            raf = new RandomAccessFile("cinefilos.mov", "rw");
            raf.seek(0);
            boolean stay = true;
            while(raf.getFilePointer() < raf.length() && stay == true){
                if(raf.readUTF().equals(username))
                    stay = false;
                raf.readUTF();
                raf.readUTF();
                raf.readUTF();
                if(!stay)
                    raf.writeBoolean(false);
                raf.readBoolean();
            }
            raf.close();
        } catch (Exception ex) {
            
        }
    }
    public void setUsername(String username){
        boolean error=false;
        do{
        
        if(username.length()<10){
            user.setUsername(username);
            error=false;
        }else{
            System.out.println("Username no debe tener mas de 10 de caracteres");
            error=true;
        }
             }while(error);
    }
    
    public void setNombreCompleto(String nombre){
        boolean error=false;
        do{
            if(nombre.length()<20){
                user.SetNombreCompleto(nombre);
                error=false;
            }else{
                System.out.println("Nombre no puede tener mas de 20 caracteres");
                error=true;
            }
        }while(error);
    }
    
    public void setPassword(char[] password){
        user.setPassword(password);
    }
    
    public String getUsername(){
        return user.getUsername();
    }
    
    public String getNombreCompleto(){
        return user.getNombreCompleto();
    }
    
    public char[] getPassword(){
        return user.getPassword();
    }
}
