/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.util;

/**
 *
 * @author Jay C Espinoza
 */
public class Util {

    public static boolean puedeConvertirse(String text) {
        try{
            Integer.parseInt(text);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    /**
     * Don't let anyone instantiate this class. :D Just like Math !^_^!
     */
    private Util(){}
    
    /**
     * Metodo escrito por Edgardo para transformar una cadena de caracteres
     * a formato Titulo por ejemplo: "alGo" se convierte en "Algo".
     * @param cad La String a transformar.
     * @return Una String con la transformacion.
     */
    public static String toTitleCase(String cad){
        if(cad != null && cad.length()>0){
            //cad=cad.replace(cad.substring(0, 1),cad.substring(0,1).toUpperCase());
            cad = cad.toUpperCase().charAt(0) + cad.substring(1).toLowerCase();
            return cad;
        }
        throw new NullPointerException();
    }
    
    public static boolean passwordsMatch(char[] pass1, char[] pass2){
        for(int i = 0; i < pass1.length; i++) {
            if(pass1[i] != pass2[i])
                return false;
        }
        return true;
    }
    
    public static String charArrayToString(char[] array){
        String result = null;
        if(array.length > 0){
            result = "";
            for(char c : array)
                result += c;
        }
        return result;
    }
}
