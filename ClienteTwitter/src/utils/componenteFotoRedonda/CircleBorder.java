package utils.componenteFotoRedonda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author daniel
 * 
 */
public class CircleBorder extends AbstractBorder {

    private Color color;
    private BasicStroke stroke = null;
    private RenderingHints hints;

    /**
     * Constructor
     */
    CircleBorder() {
        color = Color.BLACK;//negro
        stroke = new BasicStroke(0);//grosor del borde
        hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Constructor
     *
     * @param Color color color de borde
     * @param int value grosor del borde
     */
    CircleBorder(Color color, int value) {
        this.color = color;
        stroke = new BasicStroke(value);
        hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D circle2D = new Ellipse2D.Double();//java2d

        //si el ancho de linea del borde es igual a cero
        if (stroke.getLineWidth() == 0) {
            //dibuja el circulo en toda su extensión
            circle2D.setFrameFromCenter(
                    new Point(x + width / 2, y + height / 2), //centro 
                    new Point(width, height) //ancho y alto
            );
        } else {
            //pinta el circulo pero restando el grosor del borde
            circle2D.setFrameFromCenter(
                    new Point(x + width / 2, y + height / 2),
                    new Point(width - (int) stroke.getLineWidth(), height - (int) stroke.getLineWidth())
            );
        }

        Polygon pointer = new Polygon();
        Area area = new Area(circle2D);
        area.add(new Area(pointer));
        g2.setRenderingHints(hints);

        //pinta el fondo con el color del componente padre 
        Component parent = c.getParent();
        if (parent != null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0, 0, width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        if (stroke.getLineWidth() > 0) {
            g2.setColor(color);
            g2.setStroke(stroke);
        }

        g2.draw(area);
    }

}
