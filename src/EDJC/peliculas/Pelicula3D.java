/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.peliculas;

/**
 *
 * @author Jay C Espinoza
 */
public class Pelicula3D extends Pelicula{

    public Pelicula3D(int d, String n, GeneroPelicula g, TipoClasificacion c) {
        super(d, n, g, c);
        this.tipo = TipoPelicula.PELICULA3D;
    }
    
}
