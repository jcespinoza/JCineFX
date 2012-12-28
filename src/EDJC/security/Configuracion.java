/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.security;

import java.io.Serializable;

/**
 *
 * @author Jay C Espinoza
 */
public class Configuracion implements Serializable{
    private String usuarioActual = "";
    private String directorio = ".";
    private int contSala = 1;
    private int contPeli = 1;

    public int getContadorPeli() {
        return contPeli;
    }

    public void setContadorPeli(int contPeli) {
        this.contPeli = contPeli;
    }

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public int getContadorSala() {
        return contSala;
    }

    public void setContadorSala(int contador) {
        if(contador > this.contSala)
            this.contSala = contador;
    }
    
    /*REMOVE tHIS LINE*/
    @Override
    public String toString(){
        return "Contador: " + contSala +" UsuarioActual:" + usuarioActual;
    }
    
    public static void saveToDisk(String path, Configuracion conf){
        
    }
}