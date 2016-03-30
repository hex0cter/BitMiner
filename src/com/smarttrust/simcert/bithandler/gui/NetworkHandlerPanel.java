package com.smarttrust.simcert.bithandler.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NetworkHandlerPanel extends JPanel implements ActionListener, DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 202173609958941801L;
	private JTextField mHexadecimalButton;
	private JTextField mDecimalArea;
	private JButton mClearButton;

	public NetworkHandlerPanel() {
		initGUI();
	}
	
	private void initGUI() {
	    setLayout(new BorderLayout());
	    
	    LabelItemPanel topPanel = new LabelItemPanel();
	    topPanel.setBorder(BorderFactory.createTitledBorder("IPv4:"));
	    
	    mDecimalArea = createTextArea("Please use dot as divider");
	    mHexadecimalButton = createTextArea("Please use space as divider");

	    JScrollPane decPanel = new JScrollPane(mDecimalArea);
	    decPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    JScrollPane hexPanel = new JScrollPane(mHexadecimalButton);
	    hexPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    
	    mClearButton = UIUtils.createButton("Clear", "Clear the text", this);
	    
		btnPanel.add(mClearButton);
		
		topPanel.addItem("Human Readable: ", mDecimalArea);
		topPanel.addItem("Hexadecimal: ", mHexadecimalButton);
		
		add(topPanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);

	}
	
	private JTextField createTextArea(String tip) {
		JTextField textArea = new JTextField();
		textArea.setToolTipText(tip);
		textArea.getDocument().addDocumentListener(this);
		
		return textArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == mClearButton) {
			clear();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		if (e.getDocument() == mHexadecimalButton.getDocument()) {
			convert2Readable();
		} else if (e.getDocument() == mDecimalArea.getDocument()) {
			convert2Hex();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (e.getDocument() == mHexadecimalButton.getDocument()) {
			convert2Readable();
		} else if (e.getDocument() == mDecimalArea.getDocument()) {
			convert2Hex();
		}		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (e.getDocument() == mHexadecimalButton.getDocument()) {
			convert2Readable();
		} else if (e.getDocument() == mDecimalArea.getDocument()) {
			convert2Hex();
		}		
	}

	private void convert2Readable() {
		String text = mHexadecimalButton.getText();
		text = text.replaceAll("[^0-9A-Fa-f \r\n]", "");
		
		String[] array = text.split(" ");
		
		String output = "";
		for (String hex: array) {
			if (hex.isEmpty())
				continue;
			
			int dec = Integer.valueOf(hex, 16);
			output += Integer.toString(dec) + ".";
		}
		
		
		mDecimalArea.getDocument().removeDocumentListener(this);
		if (output.length() >= 1)
			mDecimalArea.setText(output.substring(0, output.length()-1));
		else
			mDecimalArea.setText("");
		mDecimalArea.getDocument().addDocumentListener(this);
	}

	private void convert2Hex() {
		String text = mDecimalArea.getText();
		text = text.replaceAll("[^0-9.\r\n]", "");
		String[] array = text.split("\\.");

		String output = "";
		for (String hex: array) {
			if (hex.isEmpty())
				continue;

			//System.out.println("convert2Hex: " + hex);
			int dec = Integer.valueOf(hex);
			output +=  String.format("%02x", dec) + " ";
		}
		
		mHexadecimalButton.getDocument().removeDocumentListener(this);
		mHexadecimalButton.setText(output.toUpperCase());
		mHexadecimalButton.getDocument().addDocumentListener(this);
		
	}
	
	private void clear()  {
		mDecimalArea.getDocument().removeDocumentListener(this);
		mHexadecimalButton.getDocument().removeDocumentListener(this);
		
		mHexadecimalButton.setText("");
		mDecimalArea.setText("");
		
		mDecimalArea.getDocument().addDocumentListener(this);
		mHexadecimalButton.getDocument().addDocumentListener(this);
	}
}
