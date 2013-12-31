
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;

public class SimFrame extends JFrame {

    JPanel contentPane;
    JMenuBar menuBar1 = new JMenuBar();
    JMenu menuFile = new JMenu();
    JMenuItem menuFileExit = new JMenuItem();
    JMenu menuHelp = new JMenu();
    JMenuItem menuHelpAbout = new JMenuItem();
    JLabel statusBar = new JLabel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    SimView drawPanel = new SimView();
    BorderLayout borderLayout2 = new BorderLayout();
    JPanel jPanel4 = new JPanel();
    BorderLayout borderLayout3 = new BorderLayout();
    JPanel jPanel5 = new JPanel();
    BorderLayout borderLayout4 = new BorderLayout();

    SimEngine simEngine = new SimEngine();
    SimRenderer simRenderer = new SimRenderer(simEngine);
    SteadyStateIterationThread ssiThread = null;
    JPanel jPanel2 = new JPanel();
    JPanel jPanel6 = new JPanel();
    JToggleButton stepButton = new JToggleButton();
    JToggleButton adjustDistButton = new JToggleButton();
    JButton resetButton = new JButton();
    GridLayout gridLayout3 = new GridLayout();
    BorderLayout borderLayout5 = new BorderLayout();
    JPanel jPanel7 = new JPanel();
    JLabel nodesChangedLabel = new JLabel();
    JLabel linksChangedLabel = new JLabel();
    GridLayout gridLayout2 = new GridLayout();
    JToggleButton runButton = new JToggleButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextField initialGridDistanceTextField = new JTextField();
    JTextField springConstantTextField = new JTextField();
    JTextField nodeMassTextField = new JTextField();
    JTextField nodeSlipForceTextField = new JTextField();
    JLabel jLabel12 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JTextField linkBreakForceVarianceTextField = new JTextField();
    JTextField nodeDistDeltaTextField = new JTextField();
    JTextField currentNodeDistanceTextField = new JTextField();
    JTextField gridWidthTextField = new JTextField();
    JPanel jPanel9 = new JPanel();
    JPanel jPanel8 = new JPanel();
    JTextField gridHeightTextField = new JTextField();
    JPanel jPanel3 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    BorderLayout borderLayout7 = new BorderLayout();
    BorderLayout borderLayout6 = new BorderLayout();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField linkBreakForceTextField = new JTextField();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JMenu jMenu1 = new JMenu();
    JCheckBoxMenuItem polygonCheckBoxMenuItem = new JCheckBoxMenuItem();
    JCheckBoxMenuItem wireFrameCheckBoxMenuItem = new JCheckBoxMenuItem();
    JCheckBoxMenuItem nodesCheckBoxMenuItem = new JCheckBoxMenuItem();
    JCheckBoxMenuItem antiAliasCheckBoxMenuItem = new JCheckBoxMenuItem();
    JMenuItem loadMenuItem = new JMenuItem();
    JMenuItem saveMenuItem = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JMenuItem jMenuItem1f = new JMenuItem();
    JMenuItem jMenuItem2f = new JMenuItem();
    JMenuItem jMenuItem5f = new JMenuItem();
    JMenuItem jMenuItem10f = new JMenuItem();
    JMenuItem jMenuItem20f = new JMenuItem();
    JMenuItem jMenuItem100f = new JMenuItem();
    JMenuItem jMenuItem500f = new JMenuItem();
    JMenuItem printMenuItem = new JMenuItem();


    //Construct the frame
    public SimFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        drawPanel.setSimRenderer(simRenderer);
        copyParamsToUI();

    }

    //Component initialization
    private void jbInit() throws Exception {
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(800, 700));
        this.setTitle("Crack Simulator");
        statusBar.setText(" ");
        menuFile.setText("File");
        menuFileExit.setText("Exit");
        menuFileExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fileExit_actionPerformed(e);
            }
        });
        menuHelp.setText("Help");
        menuHelpAbout.setText("About");
        menuHelpAbout.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                helpAbout_actionPerformed(e);
            }
        });
        jPanel1.setLayout(borderLayout2);
        jPanel4.setLayout(borderLayout3);
        jPanel5.setLayout(borderLayout4);
        jPanel2.setLayout(borderLayout5);
        stepButton.setToolTipText("");
        stepButton.setText("One Iteration");
        stepButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                stepButton_actionPerformed(e);
            }
        });
        adjustDistButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                adjustDistButton_actionPerformed(e);
            }
        });
        adjustDistButton.setText("Adjust Distance");
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resetButton_actionPerformed(e);
            }
        });
        jPanel6.setLayout(gridLayout3);
        gridLayout3.setColumns(1);
        gridLayout3.setRows(0);
        nodesChangedLabel.setText("nodes: 1/200");
        linksChangedLabel.setText("links: 1/200");
        jPanel7.setLayout(gridLayout2);
        gridLayout2.setColumns(1);
        gridLayout2.setRows(0);
        runButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                runButton_actionPerformed(e);
            }
        });
        runButton.setText("Run Continuous");
        initialGridDistanceTextField.setText("21");
        initialGridDistanceTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                initialGridDistanceTextField_actionPerformed(e);
            }
        });
        springConstantTextField.setText("1.0123");
        springConstantTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                springConstantTextField_actionPerformed(e);
            }
        });
        nodeMassTextField.setText("jTextField1");
        nodeMassTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nodeMassTextField_actionPerformed(e);
            }
        });
        nodeSlipForceTextField.setText("jTextField1");
        nodeSlipForceTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nodeSlipForceTextField_actionPerformed(e);
            }
        });
        jLabel12.setText("Node Slip Force");
        jLabel11.setText("Grid Count W");
        jLabel10.setText("Grid Count H");
        linkBreakForceVarianceTextField.setText("10.123");
        linkBreakForceVarianceTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                linkBreakForceVarianceTextField_actionPerformed(e);
            }
        });
        nodeDistDeltaTextField.setText("0.0123");
        nodeDistDeltaTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nodeDistDeltaTextField_actionPerformed(e);
            }
        });
        currentNodeDistanceTextField.setText("10.11101010");
        currentNodeDistanceTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentNodeDistanceTextField_actionPerformed(e);
            }
        });
        gridWidthTextField.setText("101");
        gridWidthTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gridWidthTextField1_actionPerformed(e);
            }
        });
        jPanel9.setLayout(borderLayout7);
        jPanel8.setLayout(borderLayout6);
        gridHeightTextField.setText("101");
        gridHeightTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gridHeightTextField_actionPerformed(e);
            }
        });
        jPanel3.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(0);
        borderLayout7.setHgap(5);
        borderLayout6.setHgap(5);
        jLabel9.setText("Current Node Dist");
        jLabel7.setText("Link Break Force");
        jLabel6.setText("Link Break Force Var.");
        jLabel5.setText("Node Distance Delta");
        jLabel4.setText("Spring Constant");
        linkBreakForceTextField.setText("20.1234");
        linkBreakForceTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                linkBreakForceTextField_actionPerformed(e);
            }
        });
        jLabel3.setText("Node Mass");
        jLabel2.setText("Inital Grid Distance");
        jMenu1.setText("Draw");
        polygonCheckBoxMenuItem.setText("Polygon");
        polygonCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                polygonCheckBoxMenuItem_actionPerformed(e);
            }
        });
        wireFrameCheckBoxMenuItem.setSelected(true);
        wireFrameCheckBoxMenuItem.setText("Wireframe");
        wireFrameCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                wireFrameCheckBoxMenuItem_actionPerformed(e);
            }
        });
        nodesCheckBoxMenuItem.setText("Nodes");
        nodesCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nodesCheckBoxMenuItem_actionPerformed(e);
            }
        });
        antiAliasCheckBoxMenuItem.setText("Anti-Alias");
        antiAliasCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                antiAliasCheckBoxMenuItem_actionPerformed(e);
            }
        });
        loadMenuItem.setText("Load ...");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                loadMenuItem_actionPerformed(e);
            }
        });
        saveMenuItem.setText("Save ...");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveMenuItem_actionPerformed(e);
            }
        });
        jMenu2.setText("Update");
        jMenuItem1f.setText("Every Frame");
        jMenuItem1f.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem1f_actionPerformed(e);
            }
        });
        jMenuItem2f.setText("Every 2 Frames");
        jMenuItem2f.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem2f_actionPerformed(e);
            }
        });
        jMenuItem5f.setText("Every 10 Frames");
        jMenuItem5f.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem5f_actionPerformed(e);
            }
        });
        jMenuItem10f.setText("Every 100 Frames");
        jMenuItem10f.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem10f_actionPerformed(e);
            }
        });
        jMenuItem20f.setText("Every 1000 Frames");
        jMenuItem20f.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem20f_actionPerformed(e);
            }
        });
        jMenuItem100f.setText("Every 10,000 Frames");
        jMenuItem100f.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem100f_actionPerformed(e);
            }
        });
        jMenuItem500f.setText("Every 100,000 Frames");
        jMenuItem500f.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem500f_actionPerformed(e);
            }
        });
        printMenuItem.setText("Print ...");
        printMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printMenuItem_actionPerformed(e);
            }
        });
        menuFile.add(loadMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(printMenuItem);
        menuFile.addSeparator();
        menuFile.add(menuFileExit);
        menuHelp.add(menuHelpAbout);
        menuBar1.add(menuFile);
        menuBar1.add(menuHelp);
        menuBar1.add(jMenu1);
        menuBar1.add(jMenu2);
        this.setJMenuBar(menuBar1);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        contentPane.add(jPanel1, BorderLayout.CENTER);
        jPanel1.add(drawPanel, BorderLayout.CENTER);
        jPanel1.add(jPanel4, BorderLayout.EAST);
        jPanel4.add(jPanel5, BorderLayout.CENTER);
        jPanel5.add(jPanel2, BorderLayout.NORTH);
        jPanel2.add(jPanel6, BorderLayout.CENTER);
        jPanel6.add(runButton, null);
        jPanel6.add(stepButton, null);
        jPanel6.add(adjustDistButton, null);
        jPanel6.add(resetButton, null);
        jPanel2.add(jPanel7, BorderLayout.SOUTH);
        jPanel7.add(nodesChangedLabel, null);
        jPanel7.add(linksChangedLabel, null);
        jPanel5.add(jScrollPane1, BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jPanel3, null);
        jPanel3.add(jLabel8, null);
        jPanel3.add(jLabel9, null);
        jPanel3.add(currentNodeDistanceTextField, null);
        jPanel3.add(jLabel5, null);
        jPanel3.add(nodeDistDeltaTextField, null);
        jPanel3.add(jLabel7, null);
        jPanel3.add(linkBreakForceTextField, null);
        jPanel3.add(jLabel6, null);
        jPanel3.add(linkBreakForceVarianceTextField, null);
        jPanel3.add(jLabel4, null);
        jPanel3.add(springConstantTextField, null);
        jPanel3.add(jLabel3, null);
        jPanel3.add(nodeMassTextField, null);
        jPanel3.add(jLabel12, null);
        jPanel3.add(nodeSlipForceTextField, null);
        jPanel3.add(jLabel2, null);
        jPanel3.add(initialGridDistanceTextField, null);
        jPanel3.add(jPanel8, null);
        jPanel8.add(gridHeightTextField, BorderLayout.CENTER);
        jPanel8.add(jLabel10, BorderLayout.WEST);
        jPanel3.add(jPanel9, null);
        jPanel9.add(gridWidthTextField, BorderLayout.CENTER);
        jPanel9.add(jLabel11, BorderLayout.WEST);
        jMenu1.add(wireFrameCheckBoxMenuItem);
        jMenu1.add(polygonCheckBoxMenuItem);
        jMenu1.add(nodesCheckBoxMenuItem);
        jMenu1.add(antiAliasCheckBoxMenuItem);
        jMenu2.add(jMenuItem1f);
        jMenu2.add(jMenuItem2f);
        jMenu2.add(jMenuItem5f);
        jMenu2.add(jMenuItem10f);
        jMenu2.add(jMenuItem20f);
        jMenu2.add(jMenuItem100f);
        jMenu2.add(jMenuItem500f);

    }

    //File | Exit action performed
    public void fileExit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    //Help | About action performed
    public void helpAbout_actionPerformed(ActionEvent e) {
        SimFrame_AboutBox dlg = new SimFrame_AboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.show();
    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            fileExit_actionPerformed(null);
        }
    }

    void stepButton_actionPerformed(ActionEvent e) {

        //System.out.println();
        simEngine.runOneStep();
        drawPanel.repaint();
        stepButton.setSelected(false);

    }

    void adjustDistButton_actionPerformed(ActionEvent e) {

        //System.out.println();
        simEngine.adjustNodeDistance();
        drawPanel.repaint();
        updateDynamicGUIParams();

        adjustDistButton.setSelected(false);

    }

    void runButton_actionPerformed(ActionEvent e) {
        copyUIToParams();

        if (ssiThread != null) {
            ssiThread.interrupt();
            //System.out.println("ssi thread inteupted");
        }


        boolean selected = ((JToggleButton) e.getSource()).isSelected();
        if (!selected) {
            runButton.setText("Run Continuous");
        } else {
            runButton.setText("Stop");
            ssiThread = new SteadyStateIterationThread(simEngine, this);
            ssiThread.start();
            //System.out.println("ssi thread started");
        }
    }

    void resetButton_actionPerformed(ActionEvent e) {
        copyUIToParams();

        simEngine.reset();
        drawPanel.repaint();
        copyParamsToUI();

    }


    void copyUIToParams() {
        simEngine.springConstant = Double.parseDouble(springConstantTextField.getText());
        simEngine.initialNodeDistance = Double.parseDouble(initialGridDistanceTextField.getText());
        simEngine.nodeMass = Double.parseDouble(nodeMassTextField.getText());
        simEngine.linkBreakForceVariance = Double.parseDouble(linkBreakForceVarianceTextField.getText());
        simEngine.nodeDistanceDelta = Double.parseDouble(nodeDistDeltaTextField.getText());
        simEngine.nodeSlipForce = Double.parseDouble(nodeSlipForceTextField.getText());
        simEngine.linkBreakForce = Double.parseDouble(linkBreakForceTextField.getText());

        simEngine.nodesRows = Integer.parseInt(gridHeightTextField.getText());
        simEngine.nodesColumns = Integer.parseInt(gridWidthTextField.getText());
    }


    void copyParamsToUI() {
        updateDynamicGUIParams();

        springConstantTextField.setText(Double.toString(simEngine.springConstant));
        initialGridDistanceTextField.setText(Double.toString(simEngine.initialNodeDistance));
        nodeMassTextField.setText(Double.toString(simEngine.nodeMass));
        linkBreakForceVarianceTextField.setText(Double.toString(simEngine.linkBreakForceVariance));
        nodeDistDeltaTextField.setText(Double.toString(simEngine.nodeDistanceDelta));
        gridHeightTextField.setText(Integer.toString(simEngine.nodesRows));
        gridWidthTextField.setText(Integer.toString(simEngine.nodesColumns));
        nodeSlipForceTextField.setText(Double.toString(simEngine.nodeSlipForce));
        linkBreakForceTextField.setText(Double.toString(simEngine.linkBreakForce));
    }

    void updateDynamicGUIParams() {
        currentNodeDistanceTextField.setText(Float.toString((float) simEngine.currentNodeDistance));
        nodesChangedLabel.setText("links: " + simEngine.linkChangeList.size());
        linksChangedLabel.setText("nodes: " + simEngine.nodeChangeList.size());
    }

    void initialGridDistanceTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.initialNodeDistance = Double.parseDouble(
        ///        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void springConstantTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.springConstant = Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void nodeMassTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.nodeMass = Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void nodeSlipForceTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.nodeSlipForce = Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void linkBreakForceVarianceTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.linkBreakForceVariance = Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void nodeDistDeltaTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.nodeDistanceDelta = Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void currentNodeDistanceTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.currentNodeDistance = Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void gridWidthTextField1_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.nodesColumns = (int) Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void gridHeightTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.nodesRows = (int) Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void linkBreakForceTextField_actionPerformed(ActionEvent e) {
        copyUIToParams();
        //simEngine.linkBreakForce = Double.parseDouble(
        //        ((JTextField) e.getSource()).getText());
        copyParamsToUI();
    }

    void wireFrameCheckBoxMenuItem_actionPerformed(ActionEvent e) {
        simRenderer.drawWireFrame = ((JCheckBoxMenuItem) e.getSource()).getState();
        drawPanel.repaint();
    }

    void polygonCheckBoxMenuItem_actionPerformed(ActionEvent e) {
        simRenderer.drawPolys = ((JCheckBoxMenuItem) e.getSource()).getState();
        drawPanel.repaint();
    }

    void nodesCheckBoxMenuItem_actionPerformed(ActionEvent e) {
        simRenderer.drawNodes = ((JCheckBoxMenuItem) e.getSource()).getState();
        drawPanel.repaint();
    }

    void antiAliasCheckBoxMenuItem_actionPerformed(ActionEvent e) {
        simRenderer.antiAlias = ((JCheckBoxMenuItem) e.getSource()).getState();
        drawPanel.repaint();
    }


    void saveMenuItem_actionPerformed(ActionEvent e) {
        copyUIToParams();

        JFileChooser fc = new JFileChooser(".");
        int ret = fc.showSaveDialog(this);
        if (ret == JFileChooser.CANCEL_OPTION)
            return;

        if (ret == JFileChooser.APPROVE_OPTION) {
            //System.out.println("save file = " + fc.getSelectedFile() );
            try {
                simEngine.save(fc.getSelectedFile());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error on save\nException = " + ex);
            }
        }
    }


    void loadMenuItem_actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser(".");
        int ret = fc.showOpenDialog(this);
        if (ret == JFileChooser.CANCEL_OPTION)
            return;

        SimEngine newSimEngine = null;
        if (ret == JFileChooser.APPROVE_OPTION) {
            //System.out.println("load file = " + fc.getSelectedFile() );
            try {
                newSimEngine = simEngine.load(fc.getSelectedFile());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error on load\nException = " + ex);
            }

            simEngine = newSimEngine;
            simRenderer.setSimEngine(simEngine);
            drawPanel.repaint();
            copyParamsToUI();
        }

    }

    void setStatusText(String message) {
        statusBar.setText(message);
    }

    void jMenuItem1f_actionPerformed(ActionEvent e) {
        simEngine.cyclesToDraw = 1;
    }

    void jMenuItem2f_actionPerformed(ActionEvent e) {
        simEngine.cyclesToDraw = 2;
    }

    void jMenuItem5f_actionPerformed(ActionEvent e) {
        simEngine.cyclesToDraw = 10;
    }

    void jMenuItem10f_actionPerformed(ActionEvent e) {
        simEngine.cyclesToDraw = 100;
    }

    void jMenuItem20f_actionPerformed(ActionEvent e) {
        simEngine.cyclesToDraw = 1000;
    }

    void jMenuItem100f_actionPerformed(ActionEvent e) {
        simEngine.cyclesToDraw = 10000;
    }

    void jMenuItem500f_actionPerformed(ActionEvent e) {
        simEngine.cyclesToDraw = 100000;
    }

    void printMenuItem_actionPerformed(ActionEvent e) {
        try {
            PrinterJob printJob = PrinterJob.getPrinterJob();

            boolean pDialogState = printJob.printDialog();
            if (!pDialogState)
                return;

            Book book = new Book();
            PageFormat pageFormat = printJob.defaultPage();

            Paper paper = new Paper();
            paper.setImageableArea(0.0, 0.0, 8.25 * 72, 10.3 * 72);

            pageFormat.setPaper(paper);
            book.append(drawPanel, pageFormat);

            if (pageFormat.getOrientation() == pageFormat.LANDSCAPE)
                System.out.println("pageFormat.LANDSCAPE");
            if (pageFormat.getOrientation() == pageFormat.PORTRAIT)
                System.out.println("pageFormat.PORTRAIT");

            printJob.setPageable(book);


            if (pDialogState)
                printJob.print();

        } catch (java.security.AccessControlException ace) {
            String errmsg = "Applet access control exception; to allow " +
                    "access to printer, run policytool and set\n" +
                    "permission for \"queuePrintJob\" in " +
                    "RuntimePermission.";
            JOptionPane.showMessageDialog(this, errmsg, "Printer Access Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}