/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 *
 * @author Jay C Espinoza
 */
public class ListMovies {
    public static void main(String[] args) throws IOException {
    RandomAccessFile raf = new RandomAccessFile("peliculas.mov", "r");
        
        while(raf.getFilePointer() < raf.length()){
            System.out.println("PELICULA");
            System.out.println("Codigo: " + raf.readInt());
            System.out.println("Titulo: " + raf.readUTF());
            System.out.println("Duracion: " + raf.readInt());
            System.out.println("Genero: " + raf.readUTF());
            System.out.println("Clasificacion: " + raf.readUTF());
            System.out.println("Fecha adicion: " + new Date(raf.readLong()) );
            System.out.println("Tipo: " + raf.readUTF());
            System.out.println("Formato 3D: " + raf.readUTF());
            System.out.println("Imagen: " + raf.readUTF());
            System.out.println("\n");
        }
        raf.close();
    }
}
