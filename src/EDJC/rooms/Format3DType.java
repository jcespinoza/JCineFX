/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.rooms;

import EDJC.util.Util;

/**
 *
 * @author Jay C Espinoza
 */
public enum Format3DType {
    DIGITAL, REAL, EXTREME;
    
    @Override
    public String toString(){
        return Util.toTitleCase(super.toString()) + "3D";
    }
}
