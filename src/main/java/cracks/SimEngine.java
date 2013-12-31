
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package cracks;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SimEngine implements Serializable {

    double linkBreakForce = 20.0;
    double linkBreakForceSQ = linkBreakForce * linkBreakForce;
    double linkBreakForceVariance = 7.5;
    double initialNodeDistance = 20.0;
    double currentNodeDistance = 20.0;
    double nodeDistanceDelta = 0.00002;
    double springConstant = 2.0;
    double nodeMass = 1000.0;
    double nodeSlipForce = 0.5;
    int nodesRows = 100;
    int nodesColumns = 100;
    int cyclesToDraw = 1000;

    static final double sin60 = Math.sin(Math.toRadians(60.0));

    transient Rectangle2D.Double bounds = new Rectangle2D.Double();

    transient ArrayList nodes = null;
    transient ArrayList links = null;
    transient ArrayList triangles = null;
    transient ArrayList nodeChangeList = null;
    transient ArrayList linkChangeList = null;


    public SimEngine() {
        reset();
    }

    public void recalcBounds() {
        if (bounds == null)
            bounds = new Rectangle2D.Double();
        bounds.x = 0.0;
        bounds.y = 0.0;
        bounds.width = (nodesColumns - 1) * initialNodeDistance + initialNodeDistance / 2;
        bounds.height = (nodesRows - 1) * initialNodeDistance * sin60;
    }

    public void reset() {
        nodes = new ArrayList(nodesRows * nodesColumns);
        links = new ArrayList(nodesRows * nodesColumns);
        nodeChangeList = new ArrayList();
        linkChangeList = new ArrayList();
        triangles = new ArrayList();

        currentNodeDistance = initialNodeDistance;
        linkBreakForceSQ = linkBreakForce * linkBreakForce;

        recalcBounds();
        currentNodeDistance = initialNodeDistance - (linkBreakForce - linkBreakForceVariance) / springConstant;

        //System.out.println("initialNodeDistance =" + initialNodeDistance);
        //System.out.println("currentNodeDistance =" + currentNodeDistance);

        if (cyclesToDraw < 1000)
            cyclesToDraw = 1000;

        buildNewGrid();

        printStatus();
    }

    void buildNewGrid() {
        Node gridReference[][] = new Node[nodesRows][nodesColumns];
        //for( int r=0; r<nodesRows ;r++ )
        //  gridReference[r] = new Node[nodesColumns];

        // Make grid
        for (int r = 0; r < nodesRows; r++)
            for (int c = 0; c < nodesColumns; c++) {
                Point2D.Double pos = calcPos(r, c);
                Node node;
                if (r == 0 || c == 0 || r == nodesRows - 1 || c == nodesColumns - 1)
                    node = new ImobileNode(pos.getX(), pos.getY());
                else
                    node = new Node(pos.getX(), pos.getY());

                nodes.add(node);
                gridReference[r][c] = node;
            }

        // Make Triangles
        Triangle triGridReference[][][] = new Triangle[nodesRows - 1][nodesColumns - 1][2];

        for (int r = 0; r < nodesRows - 1; r++)
            for (int c = 0; c < nodesColumns - 1; c++)
                for (int i = 0; i < 2 - 1; i++) {
                    Triangle t = new Triangle();
                    if (r % 2 == 0) {
                        t.points.add(gridReference[r][c]);
                        t.points.add(gridReference[r + 1][c]);
                        t.points.add(gridReference[r][c + 1]);
                    } else {
                        t.points.add(gridReference[r][c]);
                        t.points.add(gridReference[r + 1][c + 1]);
                        t.points.add(gridReference[r + 1][c]);
                    }
                    triangles.add(t);
                    triGridReference[r][c][0] = t;

                    t = new Triangle();
                    if (r % 2 == 0) {
                        t.points.add(gridReference[r + 1][c + 1]);
                        t.points.add(gridReference[r + 1][c]);
                        t.points.add(gridReference[r][c + 1]);
                    } else {
                        t.points.add(gridReference[r][c]);
                        t.points.add(gridReference[r + 1][c + 1]);
                        t.points.add(gridReference[r][c + 1]);
                    }
                    triangles.add(t);
                    triGridReference[r][c][1] = t;
                }


        // Horizontal Links
        for (int r = 0; r < nodesRows; r++)
            for (int c = 0; c < nodesColumns - 1; c++) {
                if (r == nodesRows / 2 && c == nodesColumns / 2)
                    continue;

                LinkLeft leftLink = new LinkLeft(this);
                LinkRight rightLink = leftLink.getLinkRight();
                leftLink.setNode(gridReference[r][c]);
                rightLink.setNode(gridReference[r][c + 1]);
                links.add(leftLink);

                if (r != 0)
                    leftLink.triangles.add(triGridReference[r - 1][c][r % 2]);
                if (r != nodesRows - 1)
                    leftLink.triangles.add(triGridReference[r][c][r % 2]);
            }

        // Vertical Links
        for (int r = 0; r < nodesRows - 1; r++)
            for (int c = 0; c < nodesColumns; c++) {
                LinkLeft leftLink = new LinkLeft(this);
                LinkRight rightLink = leftLink.getLinkRight();
                leftLink.setNode(gridReference[r][c]);
                rightLink.setNode(gridReference[r + 1][c]);
                links.add(leftLink);

                if (c != 0)
                    leftLink.triangles.add(triGridReference[r][c - 1][1]);
                if (c != nodesColumns - 1)
                    leftLink.triangles.add(triGridReference[r][c][0]);
            }

        // diagonal Links
        for (int r = 0; r < nodesRows - 1; r++)
            for (int c = 0; c < nodesColumns - 1; c++) {
                LinkLeft leftLink = new LinkLeft(this);
                LinkRight rightLink = leftLink.getLinkRight();
                if (r % 2 == 0) {
                    leftLink.setNode(gridReference[r + 1][c + 0]);
                    rightLink.setNode(gridReference[r + 0][c + 1]);
                } else {
                    leftLink.setNode(gridReference[r][c]);
                    rightLink.setNode(gridReference[r + 1][c + 1]);
                }

                links.add(leftLink);

                leftLink.triangles.add(triGridReference[r][c][0]);
                leftLink.triangles.add(triGridReference[r][c][1]);
            }


    }

    Point2D.Double calcPos(int r, int c) {
        double x = initialNodeDistance * c + (r % 2) * initialNodeDistance / 2;
        double y = initialNodeDistance * r * sin60;

        return new Point2D.Double(x, y);
    }


    public void reCalcChangeLinks() {
        //System.out.print("reCalcChangeLinks ... ");
        //System.out.println("links change = " +linkChangeList.size()+ " / " + links.size());

        synchronized (links) {
            Iterator i = linkChangeList.iterator();
            while (i.hasNext()) {
                ((Link) i.next()).recalcForce(this);
            }
        }

        linkChangeList.clear();
        //System.out.println("done");
    }

    public void reCalcChangeNodes() {
        //System.out.print("reCalcChangeNodes ... ");
        //System.out.println("nodes change = " +nodeChangeList.size() + " / " + nodes.size());
        Iterator i = nodeChangeList.iterator();
        while (i.hasNext()) {
            ((Node) i.next()).recalcPosition(this);
        }
        nodeChangeList.clear();
        //System.out.println("done");
    }

    public void adjustNodeDistance() {
        //System.out.print("adjustNodeDistance ... ");
        currentNodeDistance -= nodeDistanceDelta;

        //System.out.println("currentNodeDistance = " +currentNodeDistance);

        synchronized (links) {

            Iterator i = ((ArrayList) links.clone()).iterator();
            while (i.hasNext()) {
                ((Link) i.next()).recalcForce(this);
            }

        }

        //System.out.println("done");

        printStatus();
    }

    public boolean hasChanges() {
        return linkChangeList.size() != 0 || nodeChangeList.size() != 0;
    }

    public void runOneStep() {
        reCalcChangeNodes();
        //printStatus();
        reCalcChangeLinks();
        //printStatus();
    }


    void printStatus() {
        //System.out.println("links change = " +linkChangeList.size()+ " / " + links.size());
        //System.out.println("nodes change = " +nodeChangeList.size() + " / " + nodes.size());

    }

    static SimEngine load(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        SimEngine simEngine = (SimEngine) ois.readObject();
        fis.close();
        ois.close();

        simEngine.reset();
        return simEngine;
    }

    void save(File file) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);

        fos.close();
        oos.close();
    }

}


