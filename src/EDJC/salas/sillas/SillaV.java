/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas.sillas;

import EDJC.util.Coordenada;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Jay C Espinoza
 */
public abstract class SillaV extends JButton implements SillaI, MouseListener{
    protected int numero;
    protected Coordenada coordenada;
    protected char fila;
    protected String fullName;
    
    public SillaV(int num, char fila){
        super("" + num);
        setNumber(num);
        setRowLetter(fila);
        setMargin(new Insets(0, 0, 0, 0));
        addMouseListener(this);
    }

    @Override
    public final Color getColorS() {
        return this.getBackground();
    }

    @Override
    public final int getNumber() {
        return this.numero;
    }

    @Override
    public final Coordenada getCoordinate() {
        return this.coordenada;
    }

    @Override
    public final void setSize(int width, int height) {
        super.setSize(width, height);
        this.repaint();
    }
   
    /*@Override
    protected void paintComponent(Graphics g) {
        String s = this.getText();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Font font = g2.getFont().deriveFont(36f);
        g2.setFont(font);
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics metrics = font.getLineMetrics(s, frc);
        // Try omitting the descent from the height variable.
        float height = metrics.getAscent() + metrics.getDescent();
        double width = font.getStringBounds(s, frc).getWidth();
        int w = (int)(getWidth() * 0.70);
        int h = getHeight();
        double xScale = w/width;
        double yScale = (double)h/height;
        double x = (w - xScale*width)/2;
        double y = (h + yScale*height)/2 - yScale*metrics.getDescent();
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(xScale, yScale);
        g2.setFont(font.deriveFont(at));
        g2.drawString(s, 0, 0);
    }*/

    @Override
    public final void setColorS(Color c) {
        setBackground(c);
    }

    @Override
    public final void setNumber(int num) {
        if(num > 0)
            this.numero = num;
    }

    @Override
    public final String getFullName() {
        return "" + fila + numero;
    }

    @Override
    public final int getHeightS() {
        return getHeight();
    }

    @Override
    public final int getWidthS() {
        return getWidth();
    }

    @Override
    public final void setCoordinate(Coordenada c) {
        if(c.getX() > 0 && c.getY() > 0)
            this.coordenada = c;
    }

    @Override
    public final char getRowLetter() {
        return fila;
    }

    @Override
    public final void setRowLetter(char let) {
        if(let >= 65 && let <= 90)
            this.fila = let;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //Not Needed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //No needed
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        //Not needed
    }
}