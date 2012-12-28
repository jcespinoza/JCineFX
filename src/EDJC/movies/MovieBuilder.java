/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

import JCineFX.JCineFX;
import JCineFX.MovieTile;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jay C Espinoza
 */
public class MovieBuilder {
    public static void escribirPelicula(Movie p) throws IOException{
        RandomAccessFile raf = new RandomAccessFile("peliculas.mov", "rw");
        raf.seek(raf.length());
        raf.writeInt(JCineFX.getMovCounter());
        raf.writeUTF(p.getNombre());
        raf.writeInt(p.getDuracion());
        raf.writeUTF(p.getGenero().name());
        raf.writeUTF(p.getClasificacion().name());
        raf.writeLong(new Date().getTime());
        raf.writeUTF(p.getTipo().name());
        raf.writeUTF(p.getFormato3D().name());
        raf.writeUTF(p.getImgArchivo());
    }
    
    public static Movie leerPelicula(int codigo) throws IOException{
        RandomAccessFile raf = new RandomAccessFile("peliculas.mov", "r");
        raf.seek(0);
        Movie ret = null;
        while(raf.getFilePointer() < raf.length()){
            int cod = raf.readInt();
            String titulo = raf.readUTF();
            int durac = raf.readInt();
            MovieGenre gen = MovieGenre.valueOf( raf.readUTF() );
            Rating clas = Rating.valueOf( raf.readUTF() );
            long fecha = raf.readLong();
            MovieType tipo = MovieType.valueOf( raf.readUTF() );
            String sForm3D = raf.readUTF();
            Format3D form3D = Format3D.valueOf(sForm3D);
            String imgPath = raf.readUTF();

            ret = new Movie(cod, durac, titulo, gen, clas, form3D);
            ret.setFechaAdicion(new Date(fecha));
            ret.setImgArchivo(imgPath);
            if(cod == codigo)
                break;
        }
        return ret;
    }

    public static void fillMoviesPanel(VBox box, Node cont) {
        try {
            int count = JCineFX.leerConf().getContadorPeli() - 1;
            for(int i = 1; i <= count; i++){
                Movie p = leerPelicula(i);
                box.getChildren().add(new MovieTile(p, cont));
            }
        } catch (IOException ex) {
            System.out.println("Cant read configuration file");
            ex.printStackTrace();
        }
    }
}