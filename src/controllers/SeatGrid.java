/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.rooms.RoomLayout;
import EDJC.rooms.seats.SeatState;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class SeatGrid extends GridPane{
    private RoomLayout layout;
    private int maxRows;
    private int maxCols;
    protected EventHandler<MouseEvent> handler;
    
    public SeatGrid(RoomLayout r, EventHandler<MouseEvent> h){
        layout = r;
        maxRows = r.getRows();
        maxCols = r.getCols();
        handler = h;
        setConstraints();
        addElements();
    }
    
    private void setConstraints() {
        for(int i = 0; i < maxRows; i++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100/maxRows);
            row.setValignment(VPos.CENTER);
            row.setFillHeight(true);
            this.getRowConstraints().add(row);
        }
        for(int i = 0; i < maxCols; i++){
            ColumnConstraints cols = new ColumnConstraints();
            cols.setPercentWidth(100/(maxCols));
            cols.setHalignment(HPos.CENTER);
            cols.setFillWidth(true);
            this.getColumnConstraints().add(cols);
        }
    }
    
    private void addElements(){
        SeatState s;
        for(int i = 0; i < maxRows; i++){
            for(int k = 0; k < maxCols; k++){
                s = layout.getSeatState(i, k);
                SeatControl seat = new SeatControl(s, i, k);
                seat.setListener(handler);
                this.add(seat, k, i);
            }
        }
    }
    
}
