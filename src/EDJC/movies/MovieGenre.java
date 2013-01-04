/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

import EDJC.util.Util;

/**
 *
 * @author Jay C Espinoza
 */
public enum MovieGenre {
    COMEDIA, DRAMA, ACCION, MUSICAL, DOCUMENTAL, ANIMADA;

    public static MovieGenre parseGenre(String t) {
        switch(t){
            case "Comedia":
                return COMEDIA;
            case "Drama":
                return DRAMA;
            case "Acción": case "Accion":
                return ACCION;
            case "Musical":
                return MUSICAL;
            case "Documental":
                return DOCUMENTAL;
            case "Animada":
                return ANIMADA;
            default:
                throw new IllegalArgumentException("Genero inválido");
        }
    }
    
    @Override
    public String toString(){
        return Util.toTitleCase(super.toString());
    }
}
