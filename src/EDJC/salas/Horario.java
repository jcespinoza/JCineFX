/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas;

import EDJC.peliculas.Pelicula;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import jfxtras.labs.scene.control.CalendarTextField;

/**
 *
 * @author Jay C Espinoza
 */
public final class Horario {
    private Calendar fecha;
    private int codPeli;
    private int duracion;
    
    public Horario(){
        fecha = new GregorianCalendar();
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
    public Horario(int anio, int mes, int dia, int hour, int min, int cod, int duracion){
        this();
        setAnio(anio);
        setMes(mes);
        setDia(dia);
        setHora(hour);
        setMinuto(min);
        setCodPeli(cod);
        setDuracion(duracion);
    }
    public Horario(Pelicula p, Calendar fecha, Calendar hora){
        this();
        setFecha(fecha.getTime());
        setHora(hora.get(Calendar.HOUR_OF_DAY));
        setMinuto(hora.get(Calendar.MINUTE));
        setCodPeli(p.getCodigo());
        setDuracion(p.getDuracion());
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
        fecha.set(Calendar.HOUR_OF_DAY, h);
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

    public int getCodPeli() {
        return codPeli;
    }
    public void setCodPeli(int cod){
        codPeli = cod;
    }
    
    public long getInicio(){
        return fecha.getTimeInMillis();
    }
    
    public long getFin(){
        return fecha.getTimeInMillis() + (duracion * 60000);
    }
    
    public long getTotalTime(){
        long mins30 = 30*60*1000;
        long duracion = this.duracion * 60*1000;
        return fecha.getTimeInMillis() + mins30 + duracion;
    }
    
    @Override
    public String toString(){
        return getAnio()+ " "+ getMes()+" " + getDia() + " " +
                getHora() + " " + getMinuto() + " D: " + getDuracion();
    }
}