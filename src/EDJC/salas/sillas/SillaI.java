/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas.sillas;

import EDJC.util.Coordenada;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

/**
 *
 * @author Jay C Espinoza
 */
public interface SillaI extends MouseListener{
    Dimension getSize();
    int getHeightS();
    int getWidthS();
    Color getColorS();
    /**
     *Metodo para unir la letra de la fila y el numero de silla.
     * @return Una <code>String</code> representando el codigo de la silla.
     */
    @Override
    String toString();
    
    int getNumber();
    Coordenada getCoordinate();
    String getFullName();
    char getRowLetter();
    
    void setColorS(Color c);
    void setNumber(int num);
    void setCoordinate(Coordenada c);
    void setRowLetter(char let);
}
