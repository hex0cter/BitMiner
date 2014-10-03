package com.smarttrust.simcert.bithandler.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;





import com.smarttrust.simcert.bithandler.gui.event.ContextMenuListener;

public class StringHandlerPanel extends JPanel implements ActionListener, DocumentListener, ChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2317746598339270755L;
	
	private boolean mInsertSpace = true;
	
	private JTextArea mStringArea;
	private JTextArea mHexArea;
	private JButton mClearButton;
	private JButton mFilterChaosButton;
	private JCheckBox mOptSpace;

	public StringHandlerPanel() {
		initGUI();
	}
	
	private void initGUI() {
	    setLayout(new BorderLayout());
	    
	    JPanel topPanel = new JPanel(new BorderLayout());
	    
	    mStringArea = createTextArea("Please put your text string here.");
	    mHexArea  = createTextArea("Please put your hex string here");

	    JScrollPane strPanel = new JScrollPane(mStringArea);
	    strPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    //strPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	    JScrollPane hexPanel = new JScrollPane(mHexArea);
	    hexPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    //hexPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    
	    mClearButton = UIUtils.createButton("Clear", "Clear the text", this);
	    mFilterChaosButton = UIUtils.createButton("Filter", "filter invalid chars", this);
	    
	    mOptSpace = new JCheckBox("Space between numbers");
	    mOptSpace.addChangeListener(this);
		mOptSpace.setSelected(mInsertSpace);

		btnPanel.add(mClearButton);
		btnPanel.add(mFilterChaosButton);
		btnPanel.add(mOptSpace);
		
		topPanel.add(strPanel, BorderLayout.CENTER);
		topPanel.add(hexPanel, BorderLayout.SOUTH);
		
		add(topPanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);

	}

	private JTextArea createTextArea(String tip) {
		JTextArea textArea = new JTextArea(10, 1);
		
		textArea.setToolTipText(tip);
		textArea.addMouseListener(new ContextMenuListener(EditAreaContextMenu.getInstance()));
		textArea.getDocument().addDocumentListener(this);
		textArea.getCaret().setSelectionVisible(true);
		textArea.setLineWrap(true);
		
		textArea.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent ce)  {
				int dot = ce.getDot();
			    int mark = ce.getMark();
			    JTextArea text = (JTextArea)ce.getSource();
	    	 
			    double factor = 2.0;
			    if (mInsertSpace)
			    	factor = 3.0;
			    	 
			    if (text == mStringArea) {
			    	select(mHexArea, (int) Math.ceil(dot * factor), (int) Math.ceil(mark * factor));
			    } else if (text == mHexArea) {
			    	select(mStringArea, (int) Math.ceil(dot / factor), (int) Math.ceil(mark / factor));
			    }
			    System.out.println(" [dot: " + dot + ",mark: " + mark + "]");
			     
			}
		});
		
		return textArea;
	}
	
	private void select(JTextArea text, int start, int end) {
		CaretListener[] listeners = text.getCaretListeners();
		
		for (CaretListener listener: listeners) {
			text.removeCaretListener(listener);
		}
		
		if (start < end)
			text.select(start, end);
		else
			text.select(end, start);
		
		text.getCaret().setSelectionVisible(true);
		
		for (CaretListener listener: listeners) {
			text.addCaretListener(listener);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == mClearButton) {
			clear();
		} else if (object == mFilterChaosButton) {
			filterChaos();
		}
	}

	private void clear()  {
		mStringArea.setText("");
	}

	private void filterChaos() {
		String text = mHexArea.getText();

		text = text.replaceAll("[^0-9A-Fa-f \r\n]", "");
		mHexArea.getDocument().removeDocumentListener(this);
		mHexArea.setText(text.toUpperCase());
		mHexArea.getDocument().addDocumentListener(this);
		
		if (mInsertSpace)
			addSpace();
		
		/*
		mHexArea.selectAll();
		mHexArea.getCaret().setSelectionVisible(true);
		mStringArea.selectAll();
		mStringArea.getCaret().setSelectionVisible(true);
		*/
	}

	private void addSpace() {
		delSpace();
		
		String text = mHexArea.getText();

		text = text.replaceAll("..", "$0 ");
		
		mHexArea.getDocument().removeDocumentListener(this);
		mHexArea.setText(text);
		mHexArea.getDocument().addDocumentListener(this);
	}

	private void delSpace() {

		String text = mHexArea.getText();

		text = text.replaceAll(" ", "");
		mHexArea.getDocument().removeDocumentListener(this);
		mHexArea.setText(text);
		mHexArea.getDocument().addDocumentListener(this);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
		if (e.getDocument() == mHexArea.getDocument()) {
			convert2String();
		} else if (e.getDocument() == mStringArea.getDocument()) {
			convert2Hex();
		}

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		
		if (e.getDocument() == mHexArea.getDocument()) {
			convert2String();
		} else if (e.getDocument() == mStringArea.getDocument()) {
			convert2Hex();
		}

	}

	@Override
	public void removeUpdate(DocumentEvent e) {

		if (e.getDocument() == mHexArea.getDocument()) {
			convert2String();
		} else if (e.getDocument() == mStringArea.getDocument()) {
			convert2Hex();
		}
		
	}

	private void convert2String() {
		String hex = mHexArea.getText();
		
		hex = hex.replaceAll(" ", "");
		hex = hex.replaceAll("..", "$0 ");
		
		String[] array = hex.split(" ");
		
		String string = "";
		for (String str: array) {
			if (str.isEmpty())
				continue;
			
			try {
				System.out.println("DBG: str = " + str);
				int ascii = Integer.valueOf(str, 16);
				String s1 = String.format("%c", ascii);
				//char character = (char) ascii;
				string += s1;
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return;
			}
		}
		
		mStringArea.getDocument().removeDocumentListener(this);
		mStringArea.setText(string);
		mStringArea.getDocument().addDocumentListener(this);
		
		//mStringArea.selectAll();
		
	}
	
	private void convert2Hex() {
		String string = mStringArea.getText();
		String hex = "";
		for (int index = 0; index < string.length(); index ++) {
			char character = string.charAt(index);
			int ascii = (int) character;
			hex += String.format("%02x", ascii).toUpperCase();
		}
		
		mHexArea.getDocument().removeDocumentListener(this);
		mHexArea.setText(hex);
		mHexArea.getDocument().addDocumentListener(this);
		
		if (mInsertSpace) {
			addSpace();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == mOptSpace) {
			mInsertSpace = mOptSpace.isSelected();
			if (mInsertSpace)
				addSpace();
			else
				delSpace();
		}
	}
}
