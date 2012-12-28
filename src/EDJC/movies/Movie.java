/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

import java.util.Date;

/**
 *
 * @author Jay C Espinoza
 */
public class Movie {
    protected int codigo;
    protected int duracion;
    protected String nombre;
    protected MovieGenre genero;
    protected Rating clasificacion;
    protected MovieType tipo;
    protected Date fechaAdicion;
    protected String imgArchivo;
    protected Format3D formato3D = Format3D.NONE;

    public Movie(int cod, int d, String n, MovieGenre g, Rating c, Format3D f) {
        this.codigo = cod;
        this.duracion = d;
        this.nombre = n;
        this.genero = g;
        this.clasificacion = c;
        this.formato3D = f;
        if(f == Format3D.NONE)
            tipo = MovieType.PELICULA2D;
        else
            tipo = MovieType.PELICULA3D;
    }

    public Format3D getFormato3D() {
        return formato3D;
    }
    
    public boolean is3D(){
        return (formato3D != Format3D.NONE);
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

    public MovieGenre getGenero() {
        return genero;
    }

    public Rating getClasificacion() {
        return clasificacion;
    }

    public MovieType getTipo() {
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