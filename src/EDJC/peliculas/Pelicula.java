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
    protected int codigo;
    protected int duracion;
    protected String nombre;
    protected GeneroPelicula genero;
    protected TipoClasificacion clasificacion;
    protected TipoPelicula tipo;
    protected Date fechaAdicion;
    protected String imgArchivo;
    protected Formato3D formato3D = Formato3D.NONE;

    public Pelicula(int cod, int d, String n, GeneroPelicula g, TipoClasificacion c, Formato3D f) {
        this.codigo = cod;
        this.duracion = d;
        this.nombre = n;
        this.genero = g;
        this.clasificacion = c;
        this.formato3D = f;
        if(f == Formato3D.NONE)
            tipo = TipoPelicula.PELICULA2D;
        else
            tipo = TipoPelicula.PELICULA3D;
    }

    public Formato3D getFormato3D() {
        return formato3D;
    }
    
    public boolean is3D(){
        return (formato3D != Formato3D.NONE);
    }

    public int getCodigo() {
        return codigo;
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