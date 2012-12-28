package EDJC.rooms;

import java.io.Serializable;
import java.util.Calendar;

public class Room4Ticket implements Serializable{
    private RoomLayout sala;
    private int codPeli;
    private String nombreArchivo;
    
    public Room4Ticket(RoomLayout s, int cod, Calendar fecha){
        sala =s;
        codPeli = cod;
        nombreArchivo = parseNombre(sala.getCodigo(), cod, fecha);
    }

    public String getNombreArchivo(){
        return nombreArchivo;
    }
    
    public void setSalaLayout(RoomLayout s){
        this.sala = s;
    }
    
    public static String parseNombre(int codS, int codP, Calendar fecha) {
        String s = "MovS" +codS ;
        s += "P" + codP + "_";
        s += fecha.get(Calendar.YEAR);
        s += "-" + fecha.get(Calendar.MONTH);
        s += "-" + fecha.get(Calendar.DAY_OF_MONTH) + "_";
        s += "-" + fecha.get(Calendar.HOUR_OF_DAY);
        s += "-" + fecha.get(Calendar.MINUTE);
        s += ".mov";
        
        return s;
    }
}