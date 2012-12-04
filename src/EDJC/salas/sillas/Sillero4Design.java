/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas.sillas;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jay C Espinoza
 */
public class Sillero4Design extends Sillero{
    
    public Sillero4Design(int filas, int cols){
        super(filas, cols);
    }

    @Override
    void initTablero() {
        for(int i = 0; i < filas; i++){
            //Agrega una fila que contendrá las sillas por fila indicadas
            JPanel filaPanel = new JPanel(new GridLayout(1, (columnas + 2), 2, 2));
            //agrega a la fila una etiqueta con la letra de la fila; 65 es el Character 'A' en ASCII. + 1 sería 'B';
            filaPanel.add(new JLabel("" + (char)(65 + i), JLabel.CENTER));

            for(int j = 0; j < columnas; j++){
                /*Inicializa las posiciones del arreglo con una nueva Silla Para diseño
                 * para luego agregarla a la fila
                 */
                sillas[i][j] = new Silla4Design(j + 1, ((char)(65 + i)));
                filaPanel.add( (Silla4Design)(sillas[i][j]) );
            }
            filaPanel.add(new JLabel("" + (char)(65 + i), JLabel.CENTER));
            this.add(filaPanel);
        }
    }
    
}
