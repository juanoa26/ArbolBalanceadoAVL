package cliente;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author juseb
 */
public class GraficaArbol extends JPanel {
    
    private final ArbolAVL miArbol;
    public static final int DIAMETRO = 40;
    public static int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 40;

    public GraficaArbol(ArbolAVL miArbol) {
        this.miArbol = miArbol;
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pintar(g, getWidth() / 2, 20, miArbol.getRaiz());
    }
    private void pintar(Graphics g, int x, int y, NodoArbolAVL r) {
        if(r == null) {
            return;
        }
        g.setColor(Color.red);
        int EXTRA = new ArbolAVL().nodosCompletos(r) * (ANCHO/2);
        g.fillOval(x, y, DIAMETRO, DIAMETRO);
        g.setColor(Color.white);
        g.drawString(r.getDato() + "", x + 10, y + 20);
        g.setColor(Color.blue);
        if(r.getIzq() != null) {
            g.drawLine(x + RADIO - 5, y + RADIO + 15, x - ANCHO - EXTRA + RADIO, y - 15 + ANCHO + EXTRA + RADIO - EXTRA);
            }
        if(r.getDer() != null) {
            g.drawLine(x + RADIO - 5, y + RADIO + 15, x + ANCHO + EXTRA + RADIO, y - 15 + ANCHO + EXTRA + RADIO - EXTRA);
        }
        pintar(g, x - ANCHO - EXTRA, y + ANCHO, r.getIzq());
        pintar(g, x + ANCHO + EXTRA, y + ANCHO, r.getDer());
    }
 
}

