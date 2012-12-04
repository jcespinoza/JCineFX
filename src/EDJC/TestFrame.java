/*
 * Frame used to test VisualCompoments not designed by Edgardo
 * So if he touches this file may the gods throw curses at him
 */
package EDJC;

import EDJC.salas.sillas.Sillero;
import EDJC.salas.sillas.Sillero4Design;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Jay C Espinoza
 */
public class TestFrame extends JDialog implements ActionListener{
    private Sillero sillas;
    
    public TestFrame(){
        super();
        setSize(new Dimension(640, 480));
        sillas = new Sillero4Design(14, 27);
        this.getContentPane().setLayout(new GridLayout(1, 1));
        getContentPane().add(sillas);
        goFullScreen();
    }
    
    public static void main(String[] args) {
        TestFrame tf = new TestFrame();
        tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    private void goFullScreen() {
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.setSize(maxBounds.width, maxBounds.height);
    }
}
