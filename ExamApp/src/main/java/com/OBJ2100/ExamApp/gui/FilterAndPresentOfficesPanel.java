package com.OBJ2100.ExamApp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.OBJ2100.ExamApp.gui.listeners.FilterAndPresentOfficesListener;

public class FilterAndPresentOfficesPanel extends JPanel {
	
	private JLabel byCountryLabel;
    private JComboBox<String> dropdownCountry;
    private JButton ReadFromDatabaseButton;
	
	public FilterAndPresentOfficesPanel() {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Filter offices"));
        setLayout(new GridBagLayout());
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        
    	// Create a label for the city selection
        byCountryLabel = new JLabel("By Country:");
        add(byCountryLabel, gbc);
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Create a dropdown menu for city selection
        dropdownCountry = new JComboBox<>();
        dropdownCountry.addItem("Select country");
        // Disable the dropdown initially
        dropdownCountry.setEnabled(true);
        add(dropdownCountry, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        
        ReadFromDatabaseButton = new JButton("Execute");
        add(ReadFromDatabaseButton, gbc);
        
        FilterAndPresentOfficesListener officeListener = new FilterAndPresentOfficesListener(this);
        ReadFromDatabaseButton.addActionListener(officeListener);
        ReadFromDatabaseButton.addActionListener(officeListener);

         
	}
	
	
    public JComboBox<String> getDropdownCountry() {
        return dropdownCountry;
    }


    
}
