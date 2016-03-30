package com.smarttrust.simcert.bithandler.gui;

import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;


public class EditAreaContextMenu extends JPopupMenu {
  private static final long serialVersionUID = 8891485297491032390L;

  private JMenuItem mCopy;
  private JMenuItem mCut;
  private JMenuItem mPaste;
  
  private static EditAreaContextMenu mInstance = null;

  public EditAreaContextMenu(String label) {
    super(label);
  }
  
  private EditAreaContextMenu() {
    // TODO Auto-generated constructor stub
    initGUI();
  }

  public static synchronized EditAreaContextMenu getInstance() {
    if (mInstance == null) {
      mInstance = new EditAreaContextMenu();
    }
    return mInstance;
  }
  
  private void initGUI() {
    mCut = new JMenuItem(new DefaultEditorKit.CutAction());
    mCut.setText("Cut");

    mCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
    mCopy.setText("Copy");

    mPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
    mPaste.setText("Paste");

    add(mCut);
    add(mCopy);
    add(mPaste);

  }
  
  public void popup(MouseEvent e) {
    if (!(e.getSource() instanceof JTextComponent)) {
      return;
    }
    
    if (!((JTextComponent) e.getSource()).isEnabled()) {
      return;
    }
    
    String text = ((JTextComponent) e.getSource()).getSelectedText();
    mCut.setEnabled(text != null && !text.isEmpty());
    mCopy.setEnabled(text != null && !text.isEmpty());

    if (!((JTextComponent) e.getSource()).isEditable()) {
      mCut.setEnabled(false);
      mPaste.setEnabled(false);
    }


    show(e.getComponent(), e.getX(), e.getY());

    
  }

}
