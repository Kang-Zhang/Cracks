
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package cracks;

public class SteadyStateIterationThread extends Thread {

    SimEngine simEngine;
    SimFrame simFrame;
    int cyclesCompleted = 0;

    public SteadyStateIterationThread(SimEngine simEngine, SimFrame simFrame) {
        this.simEngine = simEngine;
        this.simFrame = simFrame;
    }

    public void run() {
        this.setPriority(Thread.MIN_PRIORITY);

        while (!isInterrupted()) {
            simFrame.setStatusText("Adjusting Node Distance  clock=" + cyclesCompleted);
            simEngine.adjustNodeDistance();
            simFrame.updateDynamicGUIParams();

            while (simEngine.hasChanges() && !isInterrupted()) {
                //System.out.println();
                boolean updateCounts = cyclesCompleted % 100 == 0;

                cyclesCompleted++;
                if (updateCounts)
                    simFrame.setStatusText("Frame = " + cyclesCompleted);
                //simFrame.setStatusText("Recalculating Changed Nodes  clock=" + cyclesCompleted);
                if (updateCounts)
                    simFrame.updateDynamicGUIParams();

                simEngine.reCalcChangeNodes();

                if (isInterrupted()) {
                    exitCleanup();
                    return;
                }
                //simFrame.setStatusText("Recalculating Changed Links  clock=" + cyclesCompleted);
                if (updateCounts)
                    simFrame.updateDynamicGUIParams();

                simEngine.reCalcChangeLinks();

                //if( updateCounts )
                //  simFrame.setStatusText(" ");

                if (isInterrupted()) {
                    exitCleanup();
                    return;
                }

                if (cyclesCompleted % simEngine.cyclesToDraw == 0) {
                    //System.out.println("drawing" + simEngine.cyclesToDraw);
                    simFrame.drawPanel.repaint();
                }
                //else
                //  System.out.println("skip" + drawCount);

                if (isInterrupted()) {
                    exitCleanup();
                    return;
                }
            }
        }
        exitCleanup();
    }

    void exitCleanup() {
        simFrame.runButton.setSelected(false);
        simFrame.runButton.setText("Run Continuous");
        simFrame.ssiThread = null;
    }
}