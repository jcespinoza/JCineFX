/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.rooms.Schedule;
import EDJC.rooms.ScheduleBuilder;
import java.util.ArrayList;

/**
 *
 * @author Jay C Espinoza
 */
public class ScheduleListTest {
    public static void main(String[] args) {
        ArrayList<Schedule> horarios = new ArrayList<>();
        Schedule h1 = new Schedule(2012, 11, 14, 6, 0, 1, 30);
        Schedule h2 = new Schedule(2012, 11, 14, 6, 25, 2, 30);
        Schedule h3 = new Schedule(2012, 11, 14, 7, 10, 3, 10);
        Schedule h4 = new Schedule(2012, 11, 14, 8, 40, 4, 30);
        
        ScheduleBuilder.addBetween(horarios, h2);
        ScheduleBuilder.addBetween(horarios, h3);
        ScheduleBuilder.addBetween(horarios, h4);
        ScheduleBuilder.addBetween(horarios, h1);
        
        for(Schedule h: horarios){
            System.out.println("Horario: " + h.getFecha().getTime() + "   Duracion:" + h.getDuracion());
        }
    }
}
