/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas;

import EDJC.peliculas.Pelicula;
import java.util.ArrayList;

/**
 *
 * @author Jay C Espinoza
 */
public abstract class SalaCine implements SalaI{
    protected SalaLayout layout;
    protected Tablero sillas;
    protected ArrayList<Pelicula> peliculas;
    protected static double precioTicketDigital3D = 90;
    protected static double precioTicketReal3D = 110;
    protected static double precioTicketXtreme3D = 110;
    protected static double precioTicketNormal = 70;
    
    @Override
    public abstract void agregarPelicula(Pelicula peli);
    
    @Override
    public Tablero getSillero(){
        return sillas;
    }
    
    @Override
    public abstract double getPrecioTicket();

    @Override
    public abstract void setPrecioTicket(double precio);
}