package EDJC.rooms;

import EDJC.rooms.seats.SeatState;
import java.io.Serializable;
import java.util.Arrays;

public class RoomLayout implements Serializable{
    private int code;
    private boolean is3D;
    private int[][] seats;
    private int nSeats;
    private int rows;
    private int cols;
    
    public RoomLayout(int r, int cols, boolean is3D){
        this.rows = r;
        this.cols = cols;
        this.is3D = is3D;
        seats = new int[r][cols];
        nSeats = r * cols;
    }
    
    public RoomLayout(int cod){
        this.code = cod;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int codigo) {
        this.code = codigo;
    }
    
    public int[][] getSeats() {
        return seats;
    }

    public boolean is3D() {
        return is3D;
    }

    public int getNSeats() {
        return nSeats;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public SeatState getSeatState(int row, int col){
        return SeatState.valueOf( seats[row][col] );
    }
    
    public void setSeat(int row, int col, SeatState s){
        seats[row][col] = s.toInt();
    }
    
    @Override
    public boolean equals(Object ot){
        return ((RoomLayout)(ot)).getCode() == this.code;
    }
    
    public static RoomLayout genSalaLayout(int rows, int cols, SeatState s, boolean _3D){
        RoomLayout room = new RoomLayout(rows, cols, _3D);
        room.makeAll(s);
        return room;
    }
    
    public void makeAll(SeatState s){
        int st = s.toInt();
        for(int i = 0; i < rows; i++){
            Arrays.fill(seats[i], st);
            /*for(int k = 0; k < cols; k++){
                sillas[i][k] = st;
            }*/
        }
    }
    
    public int howMany(SeatState s){
        int filter = s.toInt();
        int count = 0;
        for(int i = 0; i < rows; i++){
            for(int k = 0; k < cols; k++){
                if( seats[i][k] == filter)
                    count++;
            }
        }
        return count;
    }
    
    public void changeStates(SeatState old, SeatState newV){
        int filter = old.toInt();
        int newVal = newV.toInt();
        for(int i = 0; i < rows; i++){
            for(int k = 0; k < cols; k++){
                if(seats[i][k] == filter)
                    seats[i][k] = newVal;
            }
        }
    }
    
    @Override
    public String toString(){
        String res = "Sala ";
        res += (is3D)?"3D":"2D";
        res += " code: " + code + "\n";
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                //res += (sillas[i][j])?"O":" ";
                int temp = seats[i][j];
                if( temp == 0)
                    res += " ";
                else if( temp == 1)
                    res += "D";
                else if(temp==2)
                    res += "S";
                else
                    res += "R";
            }
            res += "\n";
        }
        return res;
    }
}