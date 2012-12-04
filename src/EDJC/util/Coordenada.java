package EDJC.util;

public class Coordenada{
    private int x;
    private int y;
    private int fila;
    private int columna;
    public Coordenada(int x, int y){
        this.y = this.fila = y;
        this.x = this.columna = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.fila = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.columna = y;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        setX(fila);
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        setY(columna);
    }

}