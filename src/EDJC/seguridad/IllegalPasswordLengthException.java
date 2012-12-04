/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.seguridad;

/**
 *
 * @author Jay C Espinoza
 */
public class IllegalPasswordLengthException extends IllegalArgumentException{

    public IllegalPasswordLengthException(int passLength) {
        super("Password no puede tener " + passLength + " caracteres.");
    }
    public IllegalPasswordLengthException(){
        super("La longitud del password no cumple con los requerimientos de seguridad");
    }
}
