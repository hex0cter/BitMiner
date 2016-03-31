package com.smarttrust.simcert.bithandler.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import com.smarttrust.simcert.bithandler.gui.event.ContextMenuListener;

public class NumberHandlerPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5336034084742437068L;
	private JTextArea mTextArea;
	private JButton mUndoButton;
	private JButton mRedoButton;
	private JButton mClearButton;
	private JButton mToHexButton;
	private JButton mToDecimalButton;
	private JButton mAddSpaceButton;
	private JButton mDelSpaceButton;
	private JButton mNibbleSwapButton;
	private JButton mRemoveHexPrefixButton;
	private JButton mSplitLinesButton;
	private JButton mJoinLinesButton;
	private JButton mCaptitalizeButton;
	private JButton mFilterChaosButton;
	private JButton mLengthButton;

	private UndoManager undoManager = new UndoManager();

	public NumberHandlerPanel() {
		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new BorderLayout());


		mTextArea = new JTextArea(18, 20);
		mTextArea.addMouseListener(new ContextMenuListener(EditAreaContextMenu.getInstance()));
		mTextArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						undoManager.addEdit(e.getEdit());
						updateButtons();
					}
				});
		JScrollPane taPanel = new JScrollPane(mTextArea);
		taPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		taPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JPanel btnPanel = new JPanel(new WrapLayout());
		mUndoButton = UIUtils.createButton("Undo", "Revert to last value", this);
		mRedoButton = UIUtils.createButton("Redo", "Recover to recent value", this);


		mUndoButton.setEnabled(false);
		mRedoButton.setEnabled(false);

		mClearButton = UIUtils.createButton("Clear", "Clear the text", this);
		mAddSpaceButton = UIUtils.createButton("Add spc", "Add space in between", this);
		mDelSpaceButton = UIUtils.createButton("Remove spc", "Remove space in between", this);
		mFilterChaosButton = UIUtils.createButton("Filter", "filter invalid chars", this); 
		mToHexButton = UIUtils.createButton("Hex", "Convert to hex format", this); 
		mToDecimalButton = UIUtils.createButton("Decimal", "Convert to decimal", this); 
		mLengthButton = UIUtils.createButton("Length", "Return the length of digits", this); 
		mNibbleSwapButton = UIUtils.createButton("Nibble Swap", "Nibble Swap", this);
		mRemoveHexPrefixButton = UIUtils.createButton("Remove 0x", "Remove the 0x prefix", this);
		mSplitLinesButton = UIUtils.createButton("Split", "Split into different lines", this);
		mJoinLinesButton = UIUtils.createButton("Join", "Combine different lines into a single line", this);
		mCaptitalizeButton = UIUtils.createButton("Upper", "Convert all characters to upper case", this);

		mUndoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					undoManager.undo();
				} catch (CannotRedoException cre) {
					cre.printStackTrace();
				}
				updateButtons();
			}
		});

		mRedoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					undoManager.redo();
				} catch (CannotRedoException cre) {
					cre.printStackTrace();
				}
				updateButtons();
			}
		});



		btnPanel.add(mUndoButton);
		btnPanel.add(mRedoButton);
		btnPanel.add(mClearButton);
		btnPanel.add(mAddSpaceButton);
		btnPanel.add(mDelSpaceButton);
		btnPanel.add(mFilterChaosButton);
		btnPanel.add(mCaptitalizeButton);
		btnPanel.add(mToHexButton);
		btnPanel.add(mToDecimalButton);
		btnPanel.add(mNibbleSwapButton);
		btnPanel.add(mRemoveHexPrefixButton);
		btnPanel.add(mSplitLinesButton);
		btnPanel.add(mJoinLinesButton);
		btnPanel.add(mLengthButton);

		//add(taPanel);
		//add(btnPanel);

		topPanel.add(taPanel, BorderLayout.CENTER);
		topPanel.add(btnPanel, BorderLayout.SOUTH);

		add(topPanel, BorderLayout.CENTER);
	}

	private void updateButtons() {
		//mUndoButton.setText(undoManager.getUndoPresentationName());
		//mRedoButton.setText(undoManager.getRedoPresentationName());
		mUndoButton.setEnabled(undoManager.canUndo());
		mRedoButton.setEnabled(undoManager.canRedo());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object object = e.getSource();
		if (object == mAddSpaceButton) {
			addSpace();
		} else if (object == mDelSpaceButton) {
			delSpace();
		} else if (object == mFilterChaosButton) {
			filterChaos();
		} else if (object == mToHexButton) {
			toHex();
		} else if (object == mToDecimalButton) {
			toDecimal();
		} else if (object == mNibbleSwapButton) {
			nibbleSwap();
		} else if (object == mLengthButton) {
			getLength();
		} else if (object == mRemoveHexPrefixButton) {
			del0x();
		} else if (object == mSplitLinesButton) {
			splitLines();
		} else if (object == mJoinLinesButton) {
			joinLines();
		} else if (object == mCaptitalizeButton) {
			toUpper();
		} else if (object == mClearButton) {
			clear();
		}
	}

	private void clear()  {
		mTextArea.setText("");
	}
	
	private void addSpace() {

		String text = mTextArea.getText();

		text = text.replaceAll("src/main", "$0 ");
		mTextArea.setText(text);
	}

	private void delSpace() {

		String text = mTextArea.getText();

		text = text.replaceAll(" ", "");
		mTextArea.setText(text);
	}

	private void del0x() {

		String text = mTextArea.getText();

		text = text.replaceAll("0x", "");
		mTextArea.setText(text);
	}

	private void splitLines() {

		String text = mTextArea.getText();

		text = text.replaceAll("....................", "$0\n");
		mTextArea.setText(text);
	}

	private void joinLines() {
		String text = mTextArea.getText();

		text = text.replaceAll("\n", "").replaceAll("\r\n", "");
		mTextArea.setText(text);

	}

	private void toUpper() {
		String text = mTextArea.getText();

		text = text.toUpperCase();
		mTextArea.setText(text);

	}

	private void filterChaos() {
		String text = mTextArea.getText();

		text = text.replaceAll("[^0-9A-Fa-f \r\n]", "");
		mTextArea.setText(text);
	}

	private void getLength() {
		String text = mTextArea.getText();

		String msg;
		if (text.length()%2 == 1) {
			msg = "Number of characters: " + text.length();
			JOptionPane.showMessageDialog(null, msg, "Length", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int len = text.length()/2;
			msg = "Number of characters: " + text.length() + ". \nNumber of double-characters in decimal: " + len + ", in hex: " + Integer.toString(len, 16).toUpperCase();
			JOptionPane.showMessageDialog(null, msg, "Length", JOptionPane.INFORMATION_MESSAGE);
		}


	}

	private void nibbleSwap() {
		String text = mTextArea.getText();

		if (text.length()%2 == 1) {
			String msg = "Number of digits is not even";
			JOptionPane.showMessageDialog(null, msg, "Length", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String ret = "";
		for (int i = 0; i < text.length(); i += 2) {
			char c1 = text.charAt(i);
			char c2 = text.charAt(i + 1);
			ret += c2;
			ret += c1;
		}

		mTextArea.setText(ret);

	}

	private void toHex() {
		String text = mTextArea.getText();
		text = text.replaceAll(" ", "");

		try {
			int value = Integer.parseInt(text, 10);
			mTextArea.setText(Integer.toString(value, 16).toUpperCase());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid format.", "Convert to Hex", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void toDecimal() {
		String text = mTextArea.getText();
		text = text.replaceAll(" ", "");

		try {
			int value = Integer.parseInt(text, 16);
			mTextArea.setText(Integer.toString(value));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid format.", "Convert to Decimal", JOptionPane.ERROR_MESSAGE);
		}
	}


}
