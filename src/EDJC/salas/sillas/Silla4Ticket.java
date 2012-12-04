/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas.sillas;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Jay C Espinoza
 */
public class Silla4Ticket extends SillaV{
    SeatState state;
    
    
    public Silla4Ticket(Silla4Design s){
        super(s.getNumber(), s.getRowLetter());
        setInitialState(s);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*Will change seat color based on the available states
         * if the state is RESERVADO or HIDDEN it shouldn't do anything*
         */
        if(state == SeatState.DISPONIBLE)
            setState(SeatState.SELECCIONADO);
        else if(state == SeatState.SELECCIONADO)
            setState(SeatState.DISPONIBLE);
            
        //JOptionPane.showMessageDialog(getParent(), "I'm changing colors!!");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /*Will play a sound when the mouse hover the component
         * 
         */
        if(state == SeatState.DISPONIBLE || state == SeatState.SELECCIONADO)
            JOptionPane.showMessageDialog(getParent(), "I'm playing sounds!");
    }
    
    public void setState(SeatState st){
        this.state = st;
        this.setColorS(st.toColor());
    }

    private void setInitialState(Silla4Design s) {
        if(s.getVisibility() == false){
            this.state = SeatState.OCULTO;
            this.setEnabled(false);
            this.setVisible(true);
        }else{
            this.state = SeatState.DISPONIBLE;
        }
    }
}
