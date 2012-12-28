/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

/**
 *
 * @author Jay C Espinoza
 */
public enum Rating {
    TODO_PUBLICO("Todo publico", 100), MAYORES_15("Mayores de 15 Años", 15), MAYORES_18("Mayores de 18 años", 18), ADULTOS("Adultos", 21);

    private String descrip;
    private int minEdadPermitida;
    
    private Rating(String descrip, int edad){
        this.descrip = descrip;
        this.minEdadPermitida = edad;
    }
    
    @Override
    public String toString(){
        return this.descrip;
    }
    
    public static Rating parseRating(String text) {
        switch(text){
            case "Todo Público":
                return TODO_PUBLICO;
            case "Mayores de 15":
                return MAYORES_15;
            case "Mayores de 18":
                return MAYORES_18;
            case "Adultos":
                return ADULTOS;
            default:
                throw new IllegalArgumentException("Clasificacion Inválida");
        }
    }
}
