/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas.sillas;

import EDJC.util.Util;
import java.awt.Color;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

/**
 *
 * @author Jay C Espinoza
 */
public enum SeatState implements ObservableValue<SeatState>, WritableValue<SeatState> {
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

    @Override
    public void addListener(ChangeListener<? super SeatState> cl) {
        super.add
    }

    @Override
    public void removeListener(ChangeListener<? super SeatState> cl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SeatState getValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addListener(InvalidationListener il) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListener(InvalidationListener il) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setValue(SeatState t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
