/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

/**
 *
 * @author Jay C Espinoza
 */
public enum MovieType {
    MOVIE2D, MOVIE3D;
    
    public static MovieType parseType(String text){
        if(text.equals("2D")){
            return MovieType.MOVIE2D;
        }else if( text.equals("Real 3D") || text.equals("Digital 3D") || text.equals("Xtreme 3D") ){
            return MovieType.MOVIE3D;
        }else{
            throw new IllegalArgumentException("No se puede encontrar un tipo con ese texto");
        }
    }
}
