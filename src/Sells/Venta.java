package Sells;

import java.io.Serializable;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class Venta implements Serializable{
    private String client;
    private int nTickets;
    private int tickets[];
    private double totalMoney;
   
    
    public Venta(){
        tickets = new int[3];
    }
}