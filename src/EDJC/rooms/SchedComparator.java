/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.rooms;

import java.util.Comparator;

/**
 *
 * @author Jay C Espinoza
 */
public enum SchedComparator implements Comparator<SchedEntry> {
    START_SORT{
        @Override
        public int compare(SchedEntry s1, SchedEntry s2){
            return Integer.valueOf(s1.getStartMinute()).compareTo(s2.getStartMinute());
        }
    },
    END_SORT{
        @Override
        public int compare(SchedEntry s1, SchedEntry s2){
            return Integer.valueOf(s1.getEndMinute()).compareTo(s2.getEndMinute());
        }
    },
    LENGTH_SORT{
        @Override
        public int compare(SchedEntry s1, SchedEntry s2){
            return Integer.valueOf(s1.getLength()).compareTo(s2.getLength());
        }
    }
    
}
