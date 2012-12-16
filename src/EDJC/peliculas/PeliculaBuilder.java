/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.peliculas;

import JCineFX.JCineFX;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jay C Espinoza
 */
public class PeliculaBuilder {
    public static void escribirPelicula(Pelicula p) throws IOException{
        RandomAccessFile raf = new RandomAccessFile("peliculas.mov", "rw");
        raf.seek(raf.length());
        raf.writeInt(JCineFX.getMovCounter());
        raf.writeUTF(p.getNombre());
        raf.writeInt(p.getDuracion());
        raf.writeUTF(p.getGenero().toString());
        raf.writeUTF(p.getClasificacion().toString());
        raf.writeLong(new Date().getTime());
        raf.writeUTF(p.getTipo().toString());
        raf.writeUTF(p.getFormato3D().toString());
        raf.writeUTF(p.getImgArchivo());
    }
    
    public static Pelicula leerPelicula(int codigo) throws IOException{
        RandomAccessFile raf = new RandomAccessFile("peliculas.mov", "r");
        raf.seek(0);
        Pelicula ret = null;
        while(raf.getFilePointer() < raf.length()){
            int cod = raf.readInt();
            String titulo = raf.readUTF();
            int durac = raf.readInt();
            GeneroPelicula gen = GeneroPelicula.valueOf( raf.readUTF() );
            TipoClasificacion clas = TipoClasificacion.valueOf( raf.readUTF() );
            long fecha = raf.readLong();
            TipoPelicula tipo = TipoPelicula.valueOf( raf.readUTF() );
            String sForm3D = raf.readUTF();
            Formato3D form3D = Formato3D.valueOf(sForm3D);
            String imgPath = raf.readUTF();
            
            //if( sForm3D.equals("NONE") ){
                //ret = new Pelicula(cod, durac, titulo, gen, clas);
            //}else{
                ret = new Pelicula(cod, durac, titulo, gen, clas, form3D);
            //}
            ret.setFechaAdicion(new Date(fecha));
            ret.setImgArchivo(imgPath);
        }
        return ret;
    }  

    public static void fillMoviesPanel(VBox box) {
        //addEverySingleMovie
    }
}