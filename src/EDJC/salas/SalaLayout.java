package EDJC.salas;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase para manipular la visibilidad de las sillas en un <code>SalaCine<code>.
 */
public class SalaLayout {
    private boolean[][] sillas;
    private int nSillas;
    private int filas;
    private int cols;
    
    /**
     * Crea un objeto <code>SalaLayout<code>, inicializa el numero de sillas
     * con los parametros recibidos.
     * @param filas El numero de filas que tendra la sala de cine.
     * @param cols El numero de columnas que tendra la sala de cine.
     */
    public SalaLayout(int filas, int cols){
        this.filas = filas;
        this.cols = cols;
        sillas = new boolean[filas][cols];
        nSillas = filas * cols;
    }
    
    /**
     * Metodo para obtener la visibilidad de la silla en la posicion especificada.
     * @param fila La posicion en la fila del cine.
     * @param col La columna donde se ubica esa silla.
     * @return <code>true</code> si la silla esta visible.
     * @return <code>false</code> si la silla es invisible.
     */
    public boolean sillaVisible(int fila, int col){
        return sillas[fila][col];
    }
    
    /**
     * Establece el estado de la Silla.
     * @param fila La fila donde se ubica la silla.
     * @param col La fila de la silla.
     * @param estado <code>true</code> o <code>false</code> para poner visible o no esa silla.
     */
    public void setSilla(int fila, int col, boolean estado){
        sillas[fila][col] = estado;
    }
    
    /**
     * Oculta todas las sillas inicializando de nuevo el arreglo de sillas.
     */
    public void setTodasOcultas(){
        sillas = new boolean[filas][cols];
    }

    /**
     * Establece visibles todas las sillas con la ayuda de <code>Arrays.fill(array, value)</code>
     */
    public void setTodasVisibles(){
        for(int i = 0; i < filas; i++){
            java.util.Arrays.fill(sillas[i], true);
        }
    }
    
    /**
     * Metodo para obtener el Layout en forma de una cadena de caracteres.
     * @return Un String representando el Layout de la Sala con una O donde hay una silla visible.
     */
    @Override
    public String toString(){
        String res = "";
        for(int i = 0; i < sillas.length; i++){
            for(int j = 0; j < sillas[i].length; j++){
                res += (sillas[i][j])?"O":" ";
            }
            res += "\n";
        }
        return res;
    }
}