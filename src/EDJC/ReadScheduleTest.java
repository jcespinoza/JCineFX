package EDJC;

import EDJC.rooms.Schedule;
import EDJC.rooms.ScheduleBuilder;
import JCineFX.JCineFX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Jay C Espinoza
 */
public class ReadScheduleTest {
    public static void main(String[] args) throws IOException {
        ArrayList<Schedule> horarios = new ArrayList<>();
        int count = JCineFX.leerConf().getContadorSala() - 1;
        for(int i = 1; i <= count; i++){
            horarios = ScheduleBuilder.readHorarios(i);
            for(Schedule h: horarios){
                System.out.println("Horarios en Sala" + i);
                System.out.println("Cod Pelicula: " + h.getCodPeli());
                System.out.println("Hora Inicio: " + new Date(h.getInicio()));
                Calendar c = new GregorianCalendar();
                c.setTime(new Date(h.getFin()));
                System.out.println("Hora de Fin: " + c.getTime() );
            }
        }
    }
}