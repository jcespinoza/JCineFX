/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Edgardo Castellanos
 */
public class UserBuilder {
    private static RandomAccessFile raf;      

    private UserBuilder(){}

    public static Usuario leerUser(String username) throws IOException{
        raf = new RandomAccessFile("cinefilos.mov", "r");
        raf.seek(0);
        while(raf.getFilePointer() < raf.length()){
            Usuario leido = new Usuario();
            long filePointer = raf.getFilePointer();
            leido.setUsername(raf.readUTF());
            Usuario param = new Usuario();
            param.setUsername(username);

            if(leido.equals(param)){
                leido.setPassword(raf.readUTF().toCharArray());
                leido.setNombreCompleto(raf.readUTF());
                leido.setFotoPath(raf.readUTF());
                leido.setCredencialActiva(raf.readBoolean());
                if(leido.isCredencialActiva()){
                    return leido;
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
    }

    public static void escribirUser(Usuario user) throws FileNotFoundException, IOException{
        desactivarUsuario(user.getUsername());
        raf = new RandomAccessFile("cinefilos.mov", "rw");
        raf.seek(raf.length());
        raf.writeUTF(user.getUsername());
        raf.writeUTF(new String(user.getPassword()));
        raf.writeUTF(user.getNombreCompleto());
        raf.writeUTF(user.getFotoPath());
        raf.writeBoolean(true);
        raf.close();
    }
    public static void desactivarUsuario(String username) throws FileNotFoundException, IOException {
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
}