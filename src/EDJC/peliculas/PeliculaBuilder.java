/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.peliculas;

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
public class PeliculaBuilder {
    public static void escribirPelicula(Pelicula p) throws IOException{
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
    
    public static Pelicula leerPeliculas(int codigo) throws IOException{
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

            ret = new Pelicula(cod, durac, titulo, gen, clas, form3D);
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
                Pelicula p = leerPelicula(i);
                box.getChildren().add(new MovieTile(p, cont));
            }
        } catch (IOException ex) {
            System.out.println("Cant read configuration file");
            ex.printStackTrace();
        }
    }
}