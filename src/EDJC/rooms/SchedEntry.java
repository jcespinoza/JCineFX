package EDJC.rooms;

import EDJC.movies.Movie;
import java.io.Serializable;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class SchedEntry implements Serializable, Comparable<SchedEntry> {
    private int roomCode;
    private Movie movie;
    private int length;
    private int startMinute;
    private int endMinute;

    public SchedEntry(int roomCode, Movie movie, int length, int statMinute, int endMinute) {
        this.roomCode = roomCode;
        this.movie = movie;
        this.length = length;
        this.startMinute = statMinute;
        this.endMinute = endMinute;
    }

    public SchedEntry() {
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

    @Override
    public int compareTo(SchedEntry o) {
        return 0;
    }
}
