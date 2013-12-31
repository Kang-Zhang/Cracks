
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package cracks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Node implements Serializable {

    public double positionX = 0.0;
    public double positionY = 0.0;
    public boolean changed = false;

    public ArrayList links = new ArrayList(6);

    public Node(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setChanged(SimEngine simEngine) {
        if (changed)
            return;

        this.changed = true;
        simEngine.nodeChangeList.add(this);
    }

    public void recalcPosition(SimEngine simEngine) {
        double forceX = 0.0;
        double forceY = 0.0;
        Iterator i = links.iterator();
        while (i.hasNext()) {
            Link link = (Link) i.next();
            forceX += link.getForceX();
            forceY += link.getForceY();
        }

        double mag = Math.sqrt(forceX * forceX + forceY * forceY);
        if (mag < simEngine.nodeSlipForce) {
            changed = false;
            return;
        }

        positionX += forceX / simEngine.nodeMass;
        positionY += forceY / simEngine.nodeMass;

        i = links.iterator();
        while (i.hasNext()) {
            Link link = (Link) i.next();
            link.setChanged(simEngine);
        }

        changed = false;
    }


}