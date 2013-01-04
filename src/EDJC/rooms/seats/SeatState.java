/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.rooms.seats;

import EDJC.util.Util;
import java.awt.Color;

/**
 *
 * @author Jay C Espinoza
 */
public enum SeatState {
    RESERVED(101, 100, 95, 3), SELECTED(135, 229, 146, 2), AVAILABLE(193, 193, 191, 1), HIDDEN(255,255,255, 0);
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
                return SeatState.AVAILABLE;
                case 2:
                    return SeatState.SELECTED;
                case 3:
                    return SeatState.RESERVED;
            default:
                //this is 0;
            return SeatState.HIDDEN;
        }
    }
}
