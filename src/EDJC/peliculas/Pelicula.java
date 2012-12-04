/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.peliculas;

import java.util.Date;

/**
 *
 * @author Jay C Espinoza
 */
public class Pelicula {
    protected int duracion;
    protected String nombre;
    protected GeneroPelicula genero;
    protected TipoClasificacion clasificacion;
    protected TipoPelicula tipo;
    protected Date fechaAdicion;
    protected String imgArchivo;

    public Pelicula(int d, String n, GeneroPelicula g, TipoClasificacion c) {
        this.duracion = d;
        this.nombre = n;
        this.genero = g;
        this.clasificacion = c;
    }
    
    public boolean is3D(){
        return (this instanceof Pelicula3D);
    }

    public int getDuracion() {
        return duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public GeneroPelicula getGenero() {
        return genero;
    }

    public TipoClasificacion getClasificacion() {
        return clasificacion;
    }

    public TipoPelicula getTipo() {
        return tipo;
    }
    public String getImgArchivo() {
        return imgArchivo;
    }

    public void setImgArchivo(String imgArchivo) {
        this.imgArchivo = imgArchivo;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }
}