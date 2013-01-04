/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

import JCineFX.JCineFX;
import JCineFX.MovieTile;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jay C Espinoza
 */
public class MovieBuilder {
    
    @Deprecated
    public static void writeMovie(Movie p) throws IOException{
        writeMovie(p, JCineFX.getMovCounter(), "peliculas.mov");
    }
    
    public static void writeMovie(Movie p, int cod, String path){
        try{
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            raf.seek(raf.length());
            raf.writeInt(p.getCode());
            raf.writeUTF(p.getTitle());
            raf.writeInt(p.getLenght());
            raf.writeUTF(p.getGenre().name());
            raf.writeUTF(p.getRating().name());
            raf.writeLong(new Date().getTime());
            raf.writeUTF(p.getType().name());
            raf.writeUTF(p.getFormat3D().name());
            raf.writeUTF(p.getImgFile());
        }catch(Exception ex){ex.printStackTrace();}
    }
    
    public static boolean writeMovies(ArrayList<Movie> movs, String path){
        ArrayList<Movie> backup = readMovies(path);
        File f = new File(path);
        File bak = new File(path + ".bak");
        f.renameTo(bak);
        try{
            for(Movie m: movs){
                writeMovie(m, m.getCode(), path);
            }
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            raf.close();
            bak.delete();;
        }catch(Exception ex){
            f.delete();
            bak.renameTo(f);
            return false;
        }
        return true;
    }
    
    public static ArrayList<Movie> readMovies(String path){
        ArrayList<Movie> movs = new ArrayList<>();
        try{
            RandomAccessFile raf = new RandomAccessFile(path, "r");
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
                ret.setAditionDate(new Date(fecha));
                ret.setImgFile(imgPath);
                movs.add(ret);
            }
            raf.close();
        }catch(Exception ex){}
        return movs;
    }
    
    public static Movie readMovie(int codigo, String path){
        Movie ret = null;
        try{
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            raf.seek(0);
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
                ret.setAditionDate(new Date(fecha));
                ret.setImgFile(imgPath);
                if(cod == codigo)
                    break;
            }
        }catch(Exception ex){return null;};
        return ret;
    }
    
    public static Movie readMovie(int codigo) throws IOException{
        return readMovie(codigo, "peliculas.mov");
    }

    public static void fillMoviesPanel(VBox box, Node cont) {
        try {
            int count = JCineFX.leerConf().getContadorPeli() - 1;
            for(int i = 1; i <= count; i++){
                Movie p = readMovie(i);
                box.getChildren().add(new MovieTile(p, cont));
            }
        } catch (IOException ex) {
            System.out.println("Cant read configuration file");
            ex.printStackTrace();
        }
    }
    
    //public static void fillMoviesPanel(VBox box, Node con)
}