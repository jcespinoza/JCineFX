/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.salas.Horario;
import EDJC.salas.HorarioBuilder;
import java.util.ArrayList;

/**
 *
 * @author Jay C Espinoza
 */
public class HorarioListTest {
    public static void main(String[] args) {
        ArrayList<Horario> horarios = new ArrayList<>();
        Horario h1 = new Horario(2012, 11, 14, 6, 0, 1, 30);
        Horario h2 = new Horario(2012, 11, 14, 6, 25, 2, 30);
        Horario h3 = new Horario(2012, 11, 14, 7, 10, 3, 10);
        Horario h4 = new Horario(2012, 11, 14, 8, 40, 4, 30);
        
        HorarioBuilder.addBetween(horarios, h2);
        HorarioBuilder.addBetween(horarios, h3);
        HorarioBuilder.addBetween(horarios, h4);
        HorarioBuilder.addBetween(horarios, h1);
        
        for(Horario h: horarios){
            System.out.println("Horario: " + h.getFecha().getTime() + "   Duracion:" + h.getDuracion());
        }
    }
}
