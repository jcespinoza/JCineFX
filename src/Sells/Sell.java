package Sells;

import java.io.Serializable;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class Sell implements Serializable{
    private String client;
    private int nTickets;
    private int tickets[];
    private double totalMoney;
   
    
    public Sell(){
        tickets = new int[3];
    }
}