/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.seguridad;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;

/**
 *
 * @author Jay C Espinoza
 */
public class JCineIO {

    public static void setFileStuff() {
        checkData();
    }

    private static void checkData() {
        File count = new File("counter.edc");
        if(!count.exists())
            getCurrentCounter();
        
        File salas = new File("salas");
        if(!salas.exists())
            salas.mkdir();

        File horarios = new File("horarios");
        if(!horarios.exists())
            horarios.mkdir();
    }
    
    public static int getCurrentCounter(){
        int count = 1;       
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile("contador.edc", "rw");
            if(raf.length() == 0){
                raf.writeInt(count);
            }else{
                count = raf.readInt();
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return count;
    }
    
    public static void deleteAllData(){
        try{
            
        }catch(Exception ex){
            System.out.println("Error " + ex);
        }
    }
}
