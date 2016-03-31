package com.smarttrust.simcert.bithandler.gui;

/*
 * 
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * This class provides a panel for laying out labelled elements neatly with
 * all the labels and elements aligned down the screen.
 *
 * @author David Fraser
 * @author Michael Harris
 */
public class LabelItemPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4586662845038229785L;
	/** The row to add the next labelled item to */
	private int myNextItemRow = 0;

	/**
	 * This method is the default constructor.
	 */
	public LabelItemPanel()
	{
		init();
	}

	/**
	 * This method initialises the panel and layout manager.
	 */
	private void init()
	{
		setLayout(new GridBagLayout());

		// Create a blank label to use as a vertical fill so that the
		// label/item pairs are aligned to the top of the panel and are not
		// grouped in the centre if the parent component is taller than
		// the preferred size of the panel.

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx   = 0;
		constraints.gridy   = 99;
//		constraints.insets  = new Insets(10, 0, 0, 0);
		constraints.insets  = new Insets(8, 0, 0, 0);
		constraints.weightx = 0.0;
		constraints.weighty = 1.0;
		constraints.fill    = GridBagConstraints.VERTICAL;

		JLabel verticalFillLabel = new JLabel();

		add(verticalFillLabel, constraints);
	}

	/**
	 * This method adds a labelled item to the panel. The item is added to
	 * the row below the last item added.
	 *
	 * @param labelText The label text for the item.
	 * @param item      The item to be added.
	 */
	public void addItem(String labelText, JComponent item)
	{
		// Create the label and its constraints

		JLabel label = new JLabel(String.format("%1$18s", labelText));

		GridBagConstraints labelConstraints = new GridBagConstraints();

		labelConstraints.gridx   = 0;
		labelConstraints.gridy   = myNextItemRow;
//		labelConstraints.insets  = new Insets(10, 10, 0, 0);
		labelConstraints.weightx = 0.0;
		labelConstraints.weighty = 0.0;
		labelConstraints.insets  = new Insets(8, 8, 0, 0);
		labelConstraints.anchor  = GridBagConstraints.NORTHEAST;
		labelConstraints.fill    = GridBagConstraints.NONE;

		add(label, labelConstraints);

		// Add the component with its constraints

		GridBagConstraints itemConstraints = new GridBagConstraints();

		itemConstraints.gridx   = 1;
		itemConstraints.gridy   = myNextItemRow;
//		itemConstraints.insets  = new Insets(10, 10, 0, 10);
		itemConstraints.insets  = new Insets(8, 8, 0, 8);
		itemConstraints.weightx = 1.0;
		itemConstraints.weighty = 0.0;
		itemConstraints.anchor  = GridBagConstraints.WEST;
		itemConstraints.fill    = GridBagConstraints.HORIZONTAL;

		add(item, itemConstraints);

		myNextItemRow++;
	}

	/**
	 * This method adds a labelled item to the panel. The item is added to
	 * the row below the last item added.
	 *
	 * @param labelText The label text for the item.
	 * @param item      The item to be added.
	 */
	public void addItem(JComponent item1, JComponent item2)
	{
		GridBagConstraints gbc1 = new GridBagConstraints();

		gbc1.gridx   = 0;
		gbc1.gridy   = myNextItemRow;
		gbc1.weightx = 1.0;
//		gbc1  = new Insets(10, 10, 0, 0);
//		gbc1.insets  = new Insets(8, 8, 0, 0);
		gbc1.anchor  = GridBagConstraints.WEST;
		gbc1.fill    = GridBagConstraints.HORIZONTAL;

		add(item1, gbc1);

		// Add the component with its constraints

		GridBagConstraints gbc2 = new GridBagConstraints();

		gbc2.gridx   = 1;
		gbc2.gridy   = myNextItemRow;
//		gbc2  = new Insets(10, 10, 0, 10);
		gbc2.insets  = new Insets(0, 8, 0, 0);
		gbc2.anchor  = GridBagConstraints.WEST;
		gbc2.fill    = GridBagConstraints.NONE;

		add(item2, gbc2);

		myNextItemRow++;
	}

	/**
	 * This method adds a labelled item to the panel. The item is added to
	 * the row below the last item added.
	 *
	 * @param labelText The label text for the item.
	 * @param item      The item to be added.
	 */
	public void addItem(JComponent item)
	{
		// Add the component with its constraints

		GridBagConstraints itemConstraints = new GridBagConstraints();

		itemConstraints.gridx   = 0;
		itemConstraints.gridwidth   = 2;
		itemConstraints.gridy   = myNextItemRow;
//		itemConstraints.insets  = new Insets(10, 10, 0, 10);
		itemConstraints.insets  = new Insets(8, 8, 0, 8);
		itemConstraints.weightx = 1.0;
		itemConstraints.weighty = 0.0;
		itemConstraints.anchor  = GridBagConstraints.WEST;
		itemConstraints.fill    = GridBagConstraints.HORIZONTAL;

		add(item, itemConstraints);

		myNextItemRow++;
	}

	/**
	 * This method adds a labelled item to the panel. The item is added to
	 * the row below the last item added.
	 *
	 * @param labelText The label text for the item.
	 * @param item      The item to be added.
	 */
	public void addButtons(JButton btn1, JButton btn2)
	{
		GridBagConstraints gbc1 = new GridBagConstraints();

		gbc1.gridx   = 0;
		gbc1.gridy   = myNextItemRow;
		gbc1.weightx = 1.0;
//		gbc1  = new Insets(10, 10, 0, 0);
//		gbc1.insets  = new Insets(8, 8, 0, 0);
    
		gbc1.anchor  = GridBagConstraints.WEST;
		gbc1.fill    = GridBagConstraints.HORIZONTAL;

		add(btn1, gbc1);

		// Add the component with its constraints

		GridBagConstraints gbc2 = new GridBagConstraints();

		gbc2.gridx   = 1;
		gbc2.gridy   = myNextItemRow;
//		gbc2  = new Insets(10, 10, 0, 10);
		gbc2.insets  = new Insets(0, 8, 0, 0);
		gbc2.anchor  = GridBagConstraints.WEST;
		gbc2.fill    = GridBagConstraints.NONE;

		add(btn2, gbc2);

		myNextItemRow++;
	}

	/**
	 * This method adds a separator to the panel.
	 *
	 */
	public void addSeparator()
	{
		JSeparator separator = new JSeparator();

		//GridBagConstraints labelConstraints = new GridBagConstraints();

		GridBagConstraints itemConstraints = new GridBagConstraints();

		itemConstraints.gridx     = 0;
		itemConstraints.gridwidth = 2;
		itemConstraints.gridy   = myNextItemRow;
		itemConstraints.insets  = new Insets(8, 8, 0, 8);
		itemConstraints.weightx = 1.0;
		itemConstraints.anchor  = GridBagConstraints.WEST;
		itemConstraints.fill    = GridBagConstraints.HORIZONTAL;

		add(separator, itemConstraints);

		myNextItemRow++;
	}
	
	public void addLabel(String labelText) 
	{
		JLabel label = new JLabel(labelText);

		GridBagConstraints labelConstraints = new GridBagConstraints();

		labelConstraints.gridx   = 0;
		labelConstraints.gridy   = myNextItemRow;
		labelConstraints.weightx = 0.0;
		labelConstraints.weighty = 0.0;
		labelConstraints.insets  = new Insets(8, 8, 0, 8);
		labelConstraints.anchor  = GridBagConstraints.NORTHEAST;
		labelConstraints.fill    = GridBagConstraints.NONE;
		
		label.setForeground(Color.blue);
		
		add(label, labelConstraints);

		
		//------
		JSeparator separator = new JSeparator();		
		GridBagConstraints itemConstraints = new GridBagConstraints();

		itemConstraints.gridx     = 1;
		itemConstraints.gridwidth = 2;
		itemConstraints.gridy   = myNextItemRow;
		itemConstraints.insets  = new Insets(8, 8, 0, 8);
		itemConstraints.weightx = 1.0;
		itemConstraints.anchor  = GridBagConstraints.WEST;
		itemConstraints.fill    = GridBagConstraints.HORIZONTAL;

		add(separator, itemConstraints);
		//------
		
		
		
		myNextItemRow++;
		
	}
	
	/*
  public void closeSelection() {
  }*/

}