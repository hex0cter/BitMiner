package com.smarttrust.simcert.bithandler.gui.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.smarttrust.simcert.bithandler.gui.EditAreaContextMenu;


public class ContextMenuListener implements MouseListener {

  private EditAreaContextMenu mMenu;
  
  public ContextMenuListener(EditAreaContextMenu menu) {

    mMenu = menu;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    if (e.isPopupTrigger()) {
      mMenu.popup(e);
     }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
