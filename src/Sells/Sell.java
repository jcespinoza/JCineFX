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

    public Sell(String client, int nTickets, int[] tickets, double totalMoney) {
        this.client = client;
        this.nTickets = nTickets;
        this.tickets = tickets;
        this.totalMoney = totalMoney;
    }
    
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getnTickets() {
        return nTickets;
    }

    public void setnTickets(int nTickets) {
        this.nTickets = nTickets;
    }

    public int[] getTickets() {
        return tickets;
    }

    public void setTickets(int[] tickets) {
        this.tickets = tickets;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    
}