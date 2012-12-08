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
    

    public Pelicula3D(int cod, int d, String n, GeneroPelicula g, TipoClasificacion c, Formato3D f) {
        super(cod, d, n, g, c);
        this.tipo = TipoPelicula.PELICULA3D;
        this.formato3D = f;
    }
    
}
