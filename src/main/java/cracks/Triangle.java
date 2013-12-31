
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package cracks;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.io.Serializable;
import java.util.ArrayList;

public class Triangle implements Serializable {

    public ArrayList points = new ArrayList(3);

    public Triangle() {
    }

    public void draw(Graphics2D g) {
        GeneralPath p = new GeneralPath();
        Node n = (Node) points.get(0);
        p.moveTo((float) n.positionX, (float) n.positionY);
        n = (Node) points.get(1);
        p.lineTo((float) n.positionX, (float) n.positionY);
        n = (Node) points.get(2);
        p.lineTo((float) n.positionX, (float) n.positionY);
        p.closePath();
        g.fill(p);
    }

}