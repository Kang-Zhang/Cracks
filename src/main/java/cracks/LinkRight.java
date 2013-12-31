
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

public class LinkRight implements Link, Serializable {


    public double forceX = 0.0;
    public double forceY = 0.0;
    public LinkLeft linkLeft = null;
    public Node node = null;

    public LinkRight(LinkLeft linkLeft) {
        this.linkLeft = linkLeft;
    }


    public void setChanged(SimEngine simEngine) {
        linkLeft.setChanged(simEngine);
    }

    public double getForceX() {
        return forceX;
    }

    public double getForceY() {
        return forceY;
    }

    public void setNode(Node node) {
        this.node = node;
        node.links.add(this);
    }

    public void recalcForce(SimEngine simEngine) {
        linkLeft.recalcForce(simEngine);
    }

    public Link getOtherLink() {
        return linkLeft;
    }

    public Node getNode() {
        return node;
    }


}