package EDJC;

import EDJC.salas.SalaLayout;
import EDJC.salas.sillas.SeatState;
import EDJC.seguridad.IllegalPasswordLengthException;
import EDJC.seguridad.Usuario;
import EDJC.util.Util;
import java.applet.Applet;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jay C Espinoza
 */
public class Test extends Applet{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        /*
         * Testing SalaLayout
         */
        SalaLayout sl = new SalaLayout(4,5, true);
//        sl.setTodasVisibles();
//        sl.setSilla(2, 3, false);
        System.out.println(sl);
        
        /*
         * Testing usuario
         */
        Usuario us = new Usuario("Pascualito", "123456#".toCharArray());
        String pass = "nada";
        try {
            System.out.println("Trying to assign \"" + pass + "\" as the password for " + us.getUsername());
            us.setPassword(pass.toCharArray());
        } catch (IllegalPasswordLengthException ex) {
            System.out.println("ups! \"" + pass + "\" has " + (6 - pass.length()) + " less than the required six characters");
        }
        
        /*
         * Testing passwordsMatch function
         */
        testPasswordMatch();
        
        /*
         * Testing Login method
         */
        testLogin();
        
        /*
         * Testin enum
         */
        SeatState st = SeatState.RESERVADO;
        System.out.println(st);
    }
    
    public static void testLogin(){
        ArrayList<Usuario> users = new ArrayList<>();
        users.add(new Usuario("Michael_the_ladiesman", "nadie21".toCharArray()));
        users.add(new Usuario("edie79", "123abcd".toCharArray()));
        users.add(new Usuario("geekBoy78", "1990ty".toCharArray()));
        
        Usuario testUser = new Usuario("Michael_the_ladiesman", "nadie21".toCharArray());
        
        //Trying with a valid username and password
        System.out.println("\nLogin in with " + testUser.getUsername()
                + " and " + Util.charArrayToString( testUser.getPassword() ) );

        System.out.println("Login Operation " +
                ( (users.contains(testUser)) ? "Succesful!": "Failed!"));
        
        testUser = new Usuario("Michael_the_Ladiesman", "nadie21".toCharArray());
        
        //tryin with an invalid username and password combination
        System.out.println("\n\nLogin in with " + testUser.getUsername()
                + " and " + Util.charArrayToString( testUser.getPassword() ) );
        
        System.out.println("Login Operation " +
                ( (users.contains(testUser)) ? "Succesful!": "Failed!"));
    }
    
    public static void testPasswordMatch(){
        char[] pass1 = "hiDer".toCharArray();
        char[] pass2 = "hiDer".toCharArray();

        System.out.println("pass1  &  pass2 are equal? --> " + Util.passwordsMatch(pass1, pass2));
    }
}