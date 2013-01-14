package EDJC.rooms;

import EDJC.movies.Movie;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class SchedEntry implements Serializable, Comparable<SchedEntry> {
    public static int START_MINUTE, LENGTH, END_MINUTE, REAL_END_MINUTE;
    private int roomCode;
    private Movie movie;
    private int length;
    private int startMinute;
    private int endMinute;
    private int realEndMinute;

    public SchedEntry(int roomCode, Movie movie, int length, int startMinute, int endMinute){
        this.roomCode = roomCode;
        this.movie = movie;
        this.length = length;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        this.realEndMinute = endMinute + 30;
    }
    
    public SchedEntry(int roomCode, Movie movie, Calendar c){
        this(roomCode, movie, c.get(Calendar.MINUTE) + c.get(Calendar.HOUR_OF_DAY) * 60);
    }

    public SchedEntry() {
    }
    
    public SchedEntry(int roomCode, Movie m, int startTime){
        this.roomCode = roomCode;
        this.movie = m;
        this.length = m.getLenght();
        this.startMinute = startTime;
        this.endMinute = calcEndMinute(startMinute, movie.getLenght());
        this.realEndMinute = calcRealEndMinute(endMinute);
    }

    public int getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(int roomCode) {
        this.roomCode = roomCode;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStatMinute(int statMinute) {
        this.startMinute = statMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }
    
    public int getTotalTime(){
        return length + 30;
    }

    public int getRealEndMinute() {
        return realEndMinute;
    }

    public void setRealEndMinute(int realEndMinute) {
        this.realEndMinute = realEndMinute;
    }

    private static int calcEndMinute(int startMinute, int lenght) {
        int res = startMinute + lenght;
        if(res <= 1440)
            return res;
        else
            return res - 1440;
    }
    
    private static int calcRealEndMinute(int endMinute) {
        int res = endMinute + 30;
        if(res <= 1440)
            return res;
        else
            return res - 1440;
    }
    
    public String getString(int field){
        return null;
    }
    
    @Override
    public String toString(){
        return "Start: " + (this.startMinute / 60) +
                ":" + (this.startMinute % 60) +
                " End: " + (this.endMinute / 60) +
                ":" + (this.endMinute % 60);
    }
    
    @Override
    public int compareTo(SchedEntry o) {
        return 0;
    }
}
