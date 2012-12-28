package EDJC.rooms;

import EDJC.rooms.seats.SeatState;
import java.io.Serializable;
import java.util.Arrays;

public class SalaLayout implements Serializable{
    private int codigo;
    private boolean is3D;
    private int[][] sillas;
    private int nSillas;
    private int filas;
    private int cols;
    
    public SalaLayout(int filas, int cols, boolean is3D){
        this.filas = filas;
        this.cols = cols;
        this.is3D = is3D;
        sillas = new int[filas][cols];
        nSillas = filas * cols;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int[][] getSillas() {
        return sillas;
    }

    public boolean is3D() {
        return is3D;
    }

    public int getnSillas() {
        return nSillas;
    }

    public int getFilas() {
        return filas;
    }
    
    public SeatState getSeatState(int row, int col){
        return SeatState.valueOf( sillas[row][col] );
    }

    public int getCols() {
        return cols;
    }
    
    public void setSilla(int fila, int col, SeatState s){
        sillas[fila][col] = s.toInt();
    }
    
    public static SalaLayout genSalaLayout(int rows, int cols, SeatState s, boolean _3D){
        SalaLayout sala = new SalaLayout(rows, cols, _3D);
        sala.makeAll(s);
        return sala;
    }
    
    public void makeAll(SeatState s){
        int st = s.toInt();
        for(int i = 0; i < filas; i++){
            Arrays.fill(sillas[i], st);
            /*for(int k = 0; k < cols; k++){
                sillas[i][k] = st;
            }*/
        }
    }
    
    public int howMany(SeatState s){
        int filter = s.toInt();
        int count = 0;
        for(int i = 0; i < filas; i++){
            for(int k = 0; k < cols; k++){
                if( sillas[i][k] == filter)
                    count++;
            }
        }
        return count;
    }
    
    public void changeStates(SeatState old, SeatState newV){
        int filter = old.toInt();
        int newVal = newV.toInt();
        for(int i = 0; i < filas; i++){
            for(int k = 0; k < cols; k++){
                if(sillas[i][k] == filter)
                    sillas[i][k] = newVal;
            }
        }
    }
    
    @Override
    public String toString(){
        String res = "";
        for(int i = 0; i < sillas.length; i++){
            for(int j = 0; j < sillas[i].length; j++){
                //res += (sillas[i][j])?"O":" ";
                int temp = sillas[i][j];
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