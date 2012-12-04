/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.peliculas.Pelicula;
import EDJC.salas.SalaCine;
import EDJC.seguridad.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Jay C Espinoza
 */
public class Cine{
    private ArrayList<SalaCine> salas;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Pelicula> peliculas;
    private String nombre;
    
    public Cine(String nom){
        nombre = nom;
        salas = new ArrayList<>();
        usuarios = new ArrayList<>();
        peliculas = new ArrayList<>();
    }

    public ArrayList<SalaCine> getSalas() {
        return salas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void imprimirPeliculas() {
        System.out.println("\nMOVIES\n");
        for(Pelicula p : peliculas){
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Duracion: " + p.getDuracion());
            System.out.println("Genero: " + p.getGenero());
            System.out.println("Clasificacion: " + p.getClasificacion());
            System.out.println("Tipo: " + p.getTipo());
        }
    }
}