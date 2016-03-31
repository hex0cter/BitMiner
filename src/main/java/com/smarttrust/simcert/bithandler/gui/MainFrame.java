package com.smarttrust.simcert.bithandler.gui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;



public class MainFrame extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 2949742879262976118L;

  public MainFrame() throws HeadlessException {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    initGUI();

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        init();
      }
    });

  }
  
  private void init() {
    
  }

  private void initGUI() {
    setTitle("Bit Miner 0.2");
    setIconImage(UIUtils.LOGO.getImage());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    
    JPanel topPanel = new JPanel(new BorderLayout());
    getContentPane().add(topPanel);

    NumberHandlerPanel numHandlerPanel = new NumberHandlerPanel();
    StringHandlerPanel strHandlerPanel = new StringHandlerPanel();
    NetworkHandlerPanel netHandlerPanel = new NetworkHandlerPanel();
    
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Number", numHandlerPanel);
    tabbedPane.addTab("String", strHandlerPanel);
    tabbedPane.addTab("Network", netHandlerPanel);
    add(tabbedPane, BorderLayout.CENTER);

    pack();
  }
}
