package com.smarttrust.simcert.bithandler.gui;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UIUtils {

  public UIUtils() {
    // TODO Auto-generated constructor stub
 
  }
  
  public static JButton createButton(String title, String tips, ActionListener listener) {
    JButton button = new JButton(title);
    button.setToolTipText(tips);
    button.addActionListener(listener);
    
    return button;
  }

  public static final ImageIcon LOGO = getIcon("logo.png");

  private static ImageIcon getIcon(String file) {
    return new ImageIcon(UIUtils.class.getResource("icons/" + file));
  }
}
