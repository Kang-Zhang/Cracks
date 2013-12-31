
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

public class LinkLeft implements Link, Serializable {

    public double forceX = 0.0;
    public double forceY = 0.0;

    public boolean changed = false;
    LinkRight linkRight = null;
    public Node node = null;

    private double breakingForceSQ;

    public ArrayList triangles = new ArrayList(2);

    public LinkLeft(SimEngine simEngine) {
        this.linkRight = new LinkRight(this);

        breakingForceSQ = simEngine.linkBreakForce +
                simEngine.linkBreakForceVariance * (2.0 * Math.random() - 1.0);

        breakingForceSQ *= breakingForceSQ;
    }


    public void setChanged(SimEngine simEngine) {
        if (changed == true)
            return;

        simEngine.linkChangeList.add(this);
        changed = true;
    }

    public double getForceX() {
        return forceX;
    }

    public double getForceY() {
        return forceY;
    }


    public LinkRight getLinkRight() {
        return linkRight;
    }

    public void setNode(Node node) {
        this.node = node;
        node.links.add(this);
    }


    public void recalcForce(SimEngine simEngine) {
        Node nodeRight = linkRight.node;
        Node nodeLeft = this.node;
        double posX = nodeRight.positionX - nodeLeft.positionX;
        double posY = nodeRight.positionY - nodeLeft.positionY;

        double mag = Math.sqrt(posX * posX + posY * posY);
        double k = simEngine.springConstant *
                (mag - simEngine.currentNodeDistance) / mag;
        forceX = k * posX;
        forceY = k * posY;

        if (forceX * forceX + forceY * forceY > breakingForceSQ) {
            nodeRight.links.remove(linkRight);
            nodeLeft.links.remove(this);
            simEngine.links.remove(this);

            synchronized (simEngine.triangles) {
                Iterator i = triangles.iterator();
                while (i.hasNext()) {
                    Triangle t = (Triangle) i.next();
                    simEngine.triangles.remove(t);
                }
            }
            //System.out.println("broke link");
        }

        linkRight.forceX = -forceX;
        linkRight.forceY = -forceY;

        nodeRight.setChanged(simEngine);
        nodeLeft.setChanged(simEngine);

        changed = false;
    }

    public Link getOtherLink() {
        return linkRight;
    }

    public Node getNode() {
        return node;
    }


}
  
  
  
  
  
  