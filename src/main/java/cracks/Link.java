
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package cracks;

public interface Link {

    public double getForceX();

    public double getForceY();


    public void setChanged(SimEngine simEngine);

    public void recalcForce(SimEngine simEngine);

    public void setNode(Node node);

    public Node getNode();

    public Link getOtherLink();

}