package EDJC.salas;

import EDJC.peliculas.Pelicula;

/**
 *
 * @author Jay C Espinoza
 */
public class Sala3D extends SalaCine{
    Tipo3DFormato tipo3D;
    
    public Sala3D(Tipo3DFormato tipo){
        super();
        tipo3D = tipo;
    }

    public Sala3D() {
        tipo3D = Tipo3DFormato.DIGITAL;
    }
    
    /**
     *
     * @param peli
     */
    @Override
    public void agregarPelicula(Pelicula peli) {
        this.peliculas.add(peli);
    }

    @Override
    public double getPrecioTicket() {
        switch(tipo3D){
        case DIGITAL:
            return precioTicketDigital3D;
        case EXTREME:
            return precioTicketXtreme3D;
        default:
            return precioTicketReal3D;
        }
    }

    @Override
    public void setPrecioTicket(double precio) {
        switch(tipo3D){
        case DIGITAL:
            precioTicketDigital3D = precio;
        case EXTREME:
            precioTicketXtreme3D = precio;
        default:
            precioTicketReal3D = precio;
        }
    }
    
}