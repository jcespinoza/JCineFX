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
    RESERVADO(101, 100, 95, 3), SELECCIONADO(135, 229, 146, 2), DISPONIBLE(193, 193, 191, 1), OCULTO(255,255,255, 0);
    private int red;
    private int green;
    private int blue;
    private int index;
    
    private SeatState(int r, int g, int b, int i){
        this.red = r;
        this.green = g;
        this.blue = b;
        this.index = i;
    }
    
    public int r(){
        return red;
    }
    
    public int g(){
        return green;
    }
    public int b(){
        return blue;
    }
    
    @Override
    public String toString(){
        return Util.toTitleCase(super.toString());
    }

    public Color toColor() {
        return new Color(red, green, blue);
    }
    
    public int toInt(){
        return index;
    }
    
    public static SeatState valueOf(int index){
        switch(index){
            case 1:
                return SeatState.DISPONIBLE;
                case 2:
                    return SeatState.SELECCIONADO;
                case 3:
                    return SeatState.RESERVADO;
            default:
                //this is 0;
            return SeatState.OCULTO;
        }
    }
}
