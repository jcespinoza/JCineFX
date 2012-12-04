/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.peliculas;

/**
 *
 * @author Jay C Espinoza
 */
public enum TipoClasificacion {
    TODO_PUBLICO("Todo publico", 100), MAYORES_15("Mayores de 15 Años", 15), MAYORES_18("Mayores de 18 años", 18), ADULTOS("Adultos", 21);
    private String descrip;
    private int minEdadPermitida;
    
    private TipoClasificacion(String descrip, int edad){
        this.descrip = descrip;
        this.minEdadPermitida = edad;
    }
    
    @Override
    public String toString(){
        return this.descrip;
    }
}
