/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.security;

/**
 *
 * @author Jay C Espinoza
 */
public class InvalidPasswordException extends IllegalArgumentException{

    public InvalidPasswordException() {
        super("El password suministrado es invalido");
    }   
}
