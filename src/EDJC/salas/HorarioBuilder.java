package EDJC.salas;

import EDJC.peliculas.Pelicula;
import EDJC.peliculas.PeliculaBuilder;
import JCineFX.HorarioControl;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import jfxtras.labs.scene.control.CalendarTextField;
import jfxtras.labs.scene.control.CalendarTimeTextField;

public class HorarioBuilder {
    
    public static void escribirHorario(int cod, Horario h) throws IOException{
        RandomAccessFile raf = new RandomAccessFile("horarios/horarios_" + cod + ".mov", "rw");
        raf.seek(raf.length());
        raf.writeInt(h.getCodPeli());
        raf.writeInt(h.getAnio());
        raf.writeInt(h.getMes());
        raf.writeInt(h.getDia());
        raf.writeInt(h.getHora());
        raf.writeInt(h.getMinuto());
        raf.writeInt(h.getDuracion());
    }
    
    public static boolean dataMakeSense(Pelicula p, int codSala, CalendarTextField fecha, CalendarTimeTextField time){
        if(p == null)
            return false;
        if( codSala <= 0)
            return false;
        if( fecha.getValue().getTimeInMillis() <= 0)
            return false;
        if( time.getValue().getTimeInMillis() <= 0)
            return false;
        return true;
    }
    
    public static Calendar joinTime(Calendar fecha, Calendar time){
        Calendar ret = fecha;
        int horas = time.get(Calendar.HOUR_OF_DAY);
        int mins = time.get(Calendar.MINUTE);
        ret.add(Calendar.HOUR_OF_DAY, horas);
        ret.add(Calendar.MINUTE, mins);
        return ret;
    }
    
    public static boolean areCompatible(int codSala, int codPeli){
        try {
            SalaLayout s = SalaBuilder.leerSala(codSala);
            Pelicula p = PeliculaBuilder.leerPelicula(codPeli);
            if(s.is3D() && p.is3D())
                return true;
            if(s.is3D() && !p.is3D())
                return true;
            if(!s.is3D() && !p.is3D())
                return true;
        } catch (Exception ex) {
            return false;
        }
        System.out.println("Incompatible");
        return false;
    }
    
    public static boolean timeIsOK(Calendar time){
        long milis = time.getTimeInMillis();
        return false;
    }
    
    public static long getDiference(Horario h1, Horario h2){
        return h2.getInicio() - h1.getTotalTime();
    }
    
    public static ArrayList<Horario> readHorarios(int cod) throws IOException{
        ArrayList<Horario> ret = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile("horarios/horarios_" + cod + ".mov", "rw");
        raf.seek(0);
        while(raf.getFilePointer() < raf.length()){
            Horario h = new Horario();
            h.setCodPeli(raf.readInt());
            h.setAnio(raf.readInt());
            h.setMes(raf.readInt());
            h.setDia(raf.readInt());
            h.setHora(raf.readInt());
            h.setMinuto(raf.readInt());
            h.setDuracion(raf.readInt());
            ret.add(h);
        }
        return ret;
    }
    public static void escribirHorarios(int cod, ArrayList<Horario> array) throws IOException{
        for(Horario h: array){
            escribirHorario(cod, h);
        }
    }
    
    //esta funcion agregaria el Horario al arraylist solo si no traslapa los que ya estan
    //lo hace pero no los deja en orden
    public static boolean addBetween(ArrayList<Horario> array, Horario hor){
        if(array.isEmpty()){
            array.add(hor);
            return true;
        }
        Horario prev = null;
        Horario next = null;
        long difPrev = Long.MAX_VALUE;
        long difNext = Long.MAX_VALUE;
        long cDifPrev = 0;
        long cDifNext = 0;
        for(Horario h: array){
            long hTotal = h.getTotalTime();
            System.out.println("\nHFin: " + new Date(hTotal));
            long horIni = hor.getInicio();
            System.out.println("horIni: " + new Date(horIni));
            if(hTotal < horIni){
                cDifPrev = getDiference(h, hor);
                if( cDifPrev < difPrev && cDifPrev > 0){
                    prev = h;
                    difPrev = cDifPrev;
                }
            }
            long hIni = h.getInicio();
            long horTotal = hor.getTotalTime();
            System.out.println("\nhorFin: " + new Date(horTotal));
            System.out.println("hIni: " + new Date(hIni));
            if(hIni > horTotal){
                cDifNext = getDiference(hor, h);
                if( cDifNext < difNext && cDifNext > 0){
                    next = h;
                    difNext = cDifNext;
                }
            }
        }
        if( prev != null){
            array.add(array.indexOf(prev), hor);
            return true;
        }else if(next != null){
            array.add(array.indexOf(next), hor);
        }
        return false;
    }

    public static void fillPanel(HBox box, ChoiceBox salas) {
        try {
            box.getChildren().removeAll(box.getChildren());
            ArrayList<Horario> hors = readHorarios(salas.getSelectionModel().getSelectedIndex()+1);
            for(Horario h: hors){
                HorarioControl hc = new HorarioControl(h, box);
                box.getChildren().add(hc);
            }
        } catch (IOException ex) {
            System.out.println("Error when trying to read Horarios");
        }
    }
}