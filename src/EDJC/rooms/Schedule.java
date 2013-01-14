package EDJC.rooms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public final class Schedule implements Serializable{
    private int roomCode;
    private ArrayList<SchedEntry> entries;
    
    public Schedule(){}
    public Schedule(int cod){
        roomCode = cod;
    }
        
    public void addEntry(SchedEntry entry){
        if(entries.isEmpty())
            entries.add(entry);
        else{
            entries.add(entry);
            //sort(SchedComparator.START_SORT);
        }
    }
    
    private boolean validateTime(){
        return true;
    }
    
    private boolean isBusyAt(int minute){
        boolean greaterStart;
        boolean lessThanStart;
        for(SchedEntry s: entries){
            greaterStart = s.getStartMinute() < minute;
        }
        return false;
    }
    
    public static int minuteDifference(int min, int min2){
        return Math.abs(min2 - min);
    }
    
    public int getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(int roomCode) {
        this.roomCode = roomCode;
    }

    public ArrayList<SchedEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<SchedEntry> entries) {
        this.entries = entries;
    }
    
    private void sort(SchedComparator s) {
        Collections.sort(entries, s);
    } 
    
    @Override
    public boolean equals(Object o){
        return this.roomCode == ((Schedule)o).roomCode;
    }
//    @Override
//    public String toString(){
//        return getAnio()+ " "+ getMes()+" " + getDia() + " " +
//                getHora() + " " + getMinuto() + " D: " + getDuracion();
//    }
    
//    public String getDuracionStr(){
//        return getDuracion() + " min.";
//    }
//    
//    public String getInicioStr(){
//        return fecha.get(Calendar.HOUR_OF_DAY) + ":" + fecha.get(Calendar.MINUTE);
//    }
//    
//    public String getFinStr(){
//        Calendar c = new GregorianCalendar();
//        c.setTime(new Date(getFin()));
//        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
//    }
}