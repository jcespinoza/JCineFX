/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Jay C Espinoza
 */
public class ListUsers {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile("cinefilos.mov", "r");
        
        while(raf.getFilePointer() < raf.length()){
            System.out.println("USUARIO");
            System.out.println("Username: " + raf.readUTF());
            System.out.println("Password: " + raf.readUTF());
            System.out.println("Nombre: " + raf.readUTF());
            System.out.println("Picture: " + raf.readUTF());
            System.out.println("Active: " + raf.readBoolean());
        }
        raf.close();
        
        System.out.println(System.getProperty("user.dir"));
    }
}