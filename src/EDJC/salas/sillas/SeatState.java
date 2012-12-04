/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas.sillas;

import EDJC.util.Util;
import java.awt.Color;

/**
 *
 * @author Jay C Espinoza
 */
public enum SeatState {
    RESERVADO(101, 100, 95), SELECCIONADO(135, 229, 146), DISPONIBLE(193, 193, 191), OCULTO(255,255,255);
    private int red;
    private int green;
    private int blue;
    
    private SeatState(int r, int g, int b){
        this.red = r;
        this.green = g;
        this.blue = b;
    }
    
    @Override
    public String toString(){
        return Util.toTitleCase(super.toString());
    }

    public Color toColor() {
        return new Color(red, green, blue);
    }
}
