/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas;

import EDJC.peliculas.Pelicula;
import EDJC.peliculas.Pelicula2D;

/**
 *
 * @author Jay C Espinoza
 */
public class Sala2D extends SalaCine{

    public Sala2D(){
        super();
    }
    
    @Override
    public void agregarPelicula(Pelicula peli) {
        if(peli instanceof Pelicula2D)
            this.peliculas.add(peli);
    }

    @Override
    public double getPrecioTicket() {
        return precioTicketNormal;
    }

    @Override
    public void setPrecioTicket(double precio) {
        precioTicketNormal = precio;
    }
}
