/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.security.User;
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
            User source = new User("jcespinoza", "password".toCharArray());
            source.setFullName("Juan Carlos Espinoza");
            source.setCredential(true);
            source.setPicturePath("fotos/pic1.jpg");
            
            fout = new FileOutputStream("user1.usr");
            ObjectOutputStream output = new ObjectOutputStream(fout);
            
            System.out.print("Source: " + "username=" + source.getUsername()
                    + " password=" + new String(source.getPassword()) +
                    " nombre=" + source.getFullName()
                    + " path=" + source.getPicturePath() +
                    " credentialActiva=" + source.isCredentialActive());
            
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
            
            User dest = (User)(obin.readObject());
            System.out.print("\nDestination: " + "username=" + dest.getUsername()
                    + " password=" + new String(dest.getPassword()) +
                    " nombre=" + dest.getFullName()
                    + " path=" + dest.getPicturePath() +
                    " credentialActiva=" + dest.isCredentialActive());
            obin.close();
        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }
        
        
    }
}