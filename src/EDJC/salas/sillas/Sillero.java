/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas.sillas;

import java.awt.Container;
import java.awt.GridLayout;

/**
 *
 * @author Jay C Espinoza
 */
public abstract class Sillero extends Container{
    protected SillaI sillas[][];
    protected int filas;
    protected int columnas;
    
    public Sillero(int filas, int cols){
        this.filas = filas;
        this.columnas = cols;
        sillas = new SillaI[filas][cols];
        this.setLayout(new GridLayout(filas, 1, 2, 2));
        initTablero();
    }

    public int getFilas() {
        return this.filas;
    }
    
    SillaI[][] getSillas(){
        return sillas;
    }
    
    public int getColumnas(){
        return this.columnas;
    }

    abstract void initTablero();
}