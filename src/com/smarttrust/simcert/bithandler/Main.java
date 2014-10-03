package com.smarttrust.simcert.bithandler;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.smarttrust.simcert.bithandler.gui.MainFrame;

public class Main {

  public Main() {
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.out.println("Error in setting java look and feel library: " + e.getMessage());
    }
    
    MainFrame frame = new MainFrame();
    frame.validate();

    //Center the window
    centerFrame(frame);
    frame.setVisible(true);
  }

  private static void centerFrame(JFrame frame) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
      frameSize.height = screenSize.height;
    if (frameSize.width > screenSize.width)
      frameSize.width = screenSize.width;
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
  }
}
