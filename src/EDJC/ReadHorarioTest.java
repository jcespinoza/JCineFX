package EDJC;

import EDJC.rooms.Horario;
import EDJC.rooms.HorarioBuilder;
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
public class ReadHorarioTest {
    public static void main(String[] args) throws IOException {
        ArrayList<Horario> horarios = new ArrayList<>();
        int count = JCineFX.leerConf().getContadorSala() - 1;
        for(int i = 1; i <= count; i++){
            horarios = HorarioBuilder.readHorarios(i);
            for(Horario h: horarios){
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