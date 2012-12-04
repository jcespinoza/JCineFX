/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.peliculas;

/**
 *
 * @author Jay C Espinoza
 */
public class Pelicula2D extends Pelicula{

    public Pelicula2D(int d, String nom, GeneroPelicula gen, TipoClasificacion clas) {
        super(d, nom, gen, clas);
        this.tipo = TipoPelicula.PELICULA2D;
    }
}
