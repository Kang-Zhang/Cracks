
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package cracks;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class SimRenderer {


    SimEngine simEngine;
    boolean drawNodes = false;
    boolean drawPolys = false;
    boolean drawWireFrame = true;
    boolean antiAlias = false;

    public SimRenderer(SimEngine simEngine) {
        this.simEngine = simEngine;
    }

    void setSimEngine(SimEngine simEngine) {
        this.simEngine = simEngine;
    }

    void draw(JPanel jPanel) {
        draw((Graphics2D) jPanel.getGraphics(), jPanel.getSize(), false);
    }

    void draw(Graphics2D g, Dimension d, boolean printing) {
        if (antiAlias)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

        if (printing)
            g.setColor(Color.white);
        else
            g.setColor(Color.black);

        //Ellipse2D.Double ellipse= new Ellipse2D.Double(
        //           (double)d.width/2 -20, (double)d.height/2 -20, 40.0, 40.0 );
        //g.draw(ellipse);
        Rectangle.Double rectangle = new Rectangle.Double(
                0.0, 0.0, (double) d.width - 1, (double) d.height - 1);
        //g.draw(rectangle);
        g.fill(rectangle);

        AffineTransform tf = calcBoundsTF(d, simEngine.bounds);
        g.transform(tf);
        //g.draw(simEngine.bounds);

        if (drawPolys)
            drawTriangles(g, printing);
        if (drawNodes)
            drawNodes(g, printing);
        if (drawWireFrame)
            drawLinks(g, printing);
    }

    void drawNodes(Graphics2D g, boolean printing) {
        Ellipse2D.Double dot = new Ellipse2D.Double(0, 0, 5.0, 5.0);

        if (printing)
            g.setColor(Color.black);
        else
            g.setColor(Color.blue);


        Iterator i = simEngine.nodes.iterator();
        while (i.hasNext()) {
            Node node = (Node) i.next();

            dot.x = node.positionX - 2.5;
            dot.y = node.positionY - 2.5;
            g.fill(dot);
        }

    }

    void drawLinks(Graphics2D g, boolean printing) {
        synchronized (simEngine.links) {
            g.setStroke(new BasicStroke(1.0f));

            if (printing)
                g.setColor(Color.black);
            else
                g.setColor(Color.red);

            Iterator i = simEngine.links.iterator();
            while (i.hasNext()) {
                Link link = (Link) i.next();
                Node node1 = link.getNode();
                Node node2 = link.getOtherLink().getNode();
                g.draw(new Line2D.Double(node1.positionX, node1.positionY,
                        node2.positionX, node2.positionY));

            }
        }
    }

    void drawTriangles(Graphics2D g, boolean printing) {
        synchronized (simEngine.triangles) {
            g.setStroke(new BasicStroke(0.5f));

            if (printing)
                g.setColor(Color.black);
            else
                g.setColor(Color.green);

            Iterator i = simEngine.triangles.iterator();
            while (i.hasNext()) {
                Triangle t = (Triangle) i.next();
                t.draw(g);
            }
        }
    }


    static AffineTransform calcBoundsTF(Dimension viewWin, Rectangle.Double bounds) {
        AffineTransform tf = new AffineTransform();

        double boundsAspect = bounds.height / bounds.width;
        double windowAspect = (double) viewWin.height / viewWin.width;

        double scale;
        if (windowAspect > boundsAspect)
            scale = viewWin.width / bounds.width;
        else
            scale = viewWin.height / bounds.height;

        scale *= 0.9;

        double centerX = bounds.x + bounds.width / 2.0;
        double centerY = bounds.y + bounds.height / 2.0;

        tf.translate(viewWin.width / 2, viewWin.height / 2);
        tf.scale(1.0, -1.0);
        tf.scale(scale, scale);
        tf.translate(-centerX, -centerY);

        return tf;
    }


}


