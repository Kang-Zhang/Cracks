
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
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class SimView extends JPanel implements Printable {
    SimRenderer simRenderer = null;

    public SimView() {
        super();
    }

    public void setSimRenderer(SimRenderer simRenderer) {
        this.simRenderer = simRenderer;
    }


    public void paint(Graphics g0) {
        super.paint(g0);
        if (simRenderer != null)
            simRenderer.draw((Graphics2D) g0, this.getSize(), false);
        else
            System.out.println("Here");
    }


    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws
            PrinterException {
        if (pageIndex >= 1)
            return Printable.NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) graphics;

        double h = pageFormat.getImageableHeight();
        double w = pageFormat.getImageableWidth();
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();

        g2d.translate(x, y);
        Dimension size = new Dimension((int) w, (int) h);

        simRenderer.draw(g2d, size, true);

        return Printable.PAGE_EXISTS;
    }


}





