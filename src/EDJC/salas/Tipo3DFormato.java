/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas;

import EDJC.util.Util;

/**
 *
 * @author Jay C Espinoza
 */
public enum Tipo3DFormato {
    DIGITAL, REAL, EXTREME;
    
    @Override
    public String toString(){
        return Util.toTitleCase(super.toString()) + "3D";
    }
}
