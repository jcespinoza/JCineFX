/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.seguridad.Usuario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jay C Espinoza
 */
public class IOtest {
    public static void main(String[] args) {
        
        FileOutputStream fout = null;
        try {
            Usuario source = new Usuario("jcespinoza", "password".toCharArray());
            source.SetNombreCompleto("Juan Carlos Espinoza");
            source.setCredencialActiva(true);
            source.setFotoPath("fotos/pic1.jpg");
            
            fout = new FileOutputStream("user1.usr");
            ObjectOutputStream output = new ObjectOutputStream(fout);
            
            System.out.print("Source: " + "username=" + source.getUsername()
                    + " password=" + new String(source.getPassword()) +
                    " nombre=" + source.getNombreCompleto()
                    + " path=" + source.getFotoPath() +
                    " credentialActiva=" + source.isCredencialActiva());
            
            output.writeObject(source);
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        } finally {
            try {
                fout.close();
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
        
        try{
            FileInputStream fin = new FileInputStream("user1.usr");
            ObjectInputStream obin = new ObjectInputStream(fin);
            
            Usuario dest = (Usuario)(obin.readObject());
            System.out.print("\nDestination: " + "username=" + dest.getUsername()
                    + " password=" + new String(dest.getPassword()) +
                    " nombre=" + dest.getNombreCompleto()
                    + " path=" + dest.getFotoPath() +
                    " credentialActiva=" + dest.isCredencialActiva());
            obin.close();
        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }
        
        
    }
}