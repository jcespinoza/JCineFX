/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

/**
 *
 * @author Jay C Espinoza
 */
public enum TipoPelicula {
    PELICULA2D, PELICULA3D;
    
    public static TipoPelicula parseTipo(String text){
        if(text.equals("2D")){
            return TipoPelicula.PELICULA2D;
        }else if( text.equals("Real 3D") || text.equals("Digital 3D") || text.equals("Xtreme 3D") ){
            return TipoPelicula.PELICULA3D;
        }else{
            throw new IllegalArgumentException("No se puede encontrar un tipo con ese texto");
        }
    }
}
