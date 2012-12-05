/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.seguridad.Usuario;
import java.io.Serializable;

/**
 *
 * @author Jay C Espinoza
 */
public class Configuracion implements Serializable{
    private Usuario usuarioActual = new Usuario("guest", "password".toCharArray());
    private String directorio = ".";
    private int contador = 1;

    public Usuario getUsuarioActual() {
        return usuarioActual; //MODIFIE THIS LINE TO FIX THE PROBLEM
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        if(contador > this.contador)
            this.contador = contador;
    }
    
    
}