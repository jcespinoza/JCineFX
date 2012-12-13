/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Jay C Espinoza
 */
public class Horario {
    private Calendar fecha;
    private int codPeli;
    private int duracion;
    
    public Horario(){
        fecha = new GregorianCalendar();
    }
    
    public static void guardarHorario(int cod, Horario h) throws IOException{
        RandomAccessFile raf = new RandomAccessFile("horarios/horarios_" + cod + ".mov", "rw");
        raf.seek(raf.length());
        raf.writeInt(cod);
        raf.writeInt(h.getAnio());
        raf.writeInt(h.getMes());
        raf.writeInt(h.getDia());
        raf.writeInt(h.getHora());
        raf.writeInt(h.getMinuto());
        raf.writeInt(h.getDuracion());
    }
    
    public Calendar getFecha(){
        return fecha;
    }
    
    public void setFecha(Date d){
        this.fecha.setTime(d);
    }
    
    public int getAnio(){
        return fecha.get(Calendar.YEAR);
    }
    
    public void setAnio(int a){
        this.fecha.set(Calendar.YEAR, a);
    }
    
    public int getMes(){
        return fecha.get(Calendar.MONTH);
    }
    
    public void setMes(int m){
        fecha.set(Calendar.MONTH, m);
    }
    
    public int getDia(){
        return fecha.get(Calendar.DATE);
    }
    
    public void setDia(int dia){
        fecha.set(Calendar.DAY_OF_MONTH, dia);
    }
    
    public int getHora(){
        return fecha.get(Calendar.HOUR_OF_DAY);
    }
    
    public void setHora(int h){
        fecha.set(Calendar.HOUR, h);
    }
    
    public int getMinuto(){
        return fecha.get(Calendar.MINUTE);
    }
    
    public void setMinuto(int m){
        fecha.set(Calendar.MINUTE, m);
    }
    
    public int getDuracion(){
        return this.duracion;
    }
    
    public void setDuracion(int d){
        this.duracion = d;
    }
}