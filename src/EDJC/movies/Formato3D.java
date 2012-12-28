/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

/**
 *
 * @author Jay C Espinoza
 */
public enum Formato3D {
    DIGITAL, EXTREME3D, REAL3D, NONE;
    
    public static Formato3D parseFormato(String text){
        
        switch(text){
            case "NONE": case "2D":
                return Formato3D.NONE;
            case "Real 3D":
                return Formato3D.REAL3D;
            case "Xtreme 3D":
                return Formato3D.EXTREME3D;
            case "Digital 3D":
                return Formato3D.DIGITAL;
            default:
                throw new IllegalArgumentException("Tipo de Formato invalido");
        }

    }
}
