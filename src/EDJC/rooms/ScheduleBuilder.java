package EDJC.rooms;

import EDJC.movies.Movie;
import EDJC.movies.MovieBuilder;
import EDJC.security.Config;
import JCineFX.Cartelera;
import JCineFX.HorarioControl;
import JCineFX.JCineFX;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class ScheduleBuilder {
    
    public static void escribirHorario(int cod, Schedule h) throws IOException{
//        RandomAccessFile raf = new RandomAccessFile("horarios/horarios_" + cod + ".mov", "rw");
//        raf.seek(raf.length());
//        raf.writeInt(h.getCodPeli());
//        raf.writeInt(h.getAnio());
//        raf.writeInt(h.getMes());
//        raf.writeInt(h.getDia());
//        raf.writeInt(h.getHora());
//        raf.writeInt(h.getMinuto());
//        raf.writeInt(h.getDuracion());
    }
    
    public static boolean dataMakeSense(Movie p, int codSala, CalendarTextField fecha, CalendarTimeTextField time){
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
    
    public static boolean areCompatible(int codSala, Movie p){
        try {
            RoomLayout s = RoomBuilder.leerSala(codSala);
            System.out.println(""+codSala);
            if(s.is3D() && p.is3D())
                return true;
            if(s.is3D() && !p.is3D())
                return true;
            if(!s.is3D() && !p.is3D())
                return true;
        } catch (Exception ex) {
            System.out.println("Error in compatibility check" + ex);
            ex.printStackTrace();
            return false;
        }
        System.out.println("Incompatible");
        return false;
    }
    
    public static boolean timeIsOK(Calendar time){
        long milis = time.getTimeInMillis();
        return false;
    }
    
    public static long getDiference(Schedule h1, Schedule h2){
//        return h2.getInicio() - h1.getTotalTime();
        return 0;
    }
    
    public static ArrayList<Schedule> readHorarios(int cod) throws IOException{
        ArrayList<Schedule> ret = new ArrayList<>();
//        RandomAccessFile raf = new RandomAccessFile("horarios/horarios_" + cod + ".mov", "rw");
//        raf.seek(0);
//        while(raf.getFilePointer() < raf.length()){
//            Schedule h = new Schedule();
//            h.setCodPeli(raf.readInt());
//            h.setAnio(raf.readInt());
//            h.setMes(raf.readInt());
//            h.setDia(raf.readInt());
//            h.setHora(raf.readInt());
//            h.setMinuto(raf.readInt());
//            h.setDuracion(raf.readInt());
//            ret.add(h);
//        }
        return ret;
    }
    public static void escribirHorarios(int cod, ArrayList<Schedule> array) throws IOException{
        for(Schedule h: array){
            escribirHorario(cod, h);
        }
    }
    
    //esta funcion agregaria el Schedule al arraylist solo si no traslapa los que ya estan
    //lo hace pero no los deja en orden
    public static boolean addBetween(ArrayList<Schedule> array, Schedule hor){
        /*
        if(array.isEmpty()){
            array.add(hor);
            return true;
        }
        Schedule prev = null;
        Schedule next = null;
        long difPrev = Long.MAX_VALUE;
        long difNext = Long.MAX_VALUE;
        long cDifPrev = 0;
        long cDifNext = 0;
        for(Schedule h: array){
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
        }*/
        return false;
    }

    public static void fillPanel(HBox box, ChoiceBox salas) {
        try {
            box.getChildren().removeAll(box.getChildren());
            int cod = salas.getSelectionModel().getSelectedIndex()+1;
            ArrayList<Schedule> hors = readHorarios(cod);
            for(Schedule h: hors){
                HorarioControl hc = new HorarioControl(cod,h, box);
                box.getChildren().add(hc);
            }
        } catch (IOException ex) {
            System.out.println("Error when trying to read Horarios");
        }
    }

    public static void fillPanel(HBox box, Cartelera cart) {
        try{
            box.getChildren().removeAll(box.getChildren());
            int count = JCineFX.leerConf().getContadorSala() - 1;
            for(int i = 1; i <= count; i++){
                ArrayList<Schedule> hors = readHorarios(i);
                for( Schedule h: hors){
                    HorarioControl hc = new HorarioControl(i,h, box);
                    hc.setOnMouseClicked(cart);
                    box.getChildren().add(hc);
                }
            }
        }catch(Exception ex){
            System.out.println("Error in BatchHorarioReading");
        }
    }
    
    public static boolean writeToFile(Schedule s, String path){
        try{
            File f = new File(path);
            FileOutputStream fous = new FileOutputStream(f);
            ObjectOutputStream ous = new ObjectOutputStream(fous);
            ous.writeObject(s);
            return true;
        }catch(Exception ex){return false;}
    }
    
    public static Schedule readFromFile(String path){
        Schedule s = null;
        try{
            File f = new File(path);
            FileInputStream fous = new FileInputStream(f);
            ObjectInputStream ous = new ObjectInputStream(fous);
            s = (Schedule)(ous.readObject());
        }catch(Exception ex){return null;}
        return s;
    }
    
    public static ArrayList<Schedule> readFromFolder(String path){
        ArrayList<Schedule> sa = new ArrayList<>();
        try{
            //File f = new File(path)
        }catch(Exception ex){return null;}
        return sa;
    }
}