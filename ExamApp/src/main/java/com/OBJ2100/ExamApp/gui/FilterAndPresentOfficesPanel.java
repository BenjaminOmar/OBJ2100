package com.OBJ2100.ExamApp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.OBJ2100.ExamApp.gui.listeners.FilterAndPresentOfficesListener;
import com.OBJ2100.ExamApp.gui.listeners.CountryDropDownListener;

/**
 * This class presents a panel with a dropown menu containing options to choose witch country
 * to filter the offices. It presents the results in a table popup.
 * @author 7162, 7132
 *
 */
public class FilterAndPresentOfficesPanel extends JPanel {
	
	private JLabel byCountryLabel;
    private JComboBox<String> dropdownCountry;
    private JButton ReadFromDatabaseButton;
    
    /**
     * Constructs a new FilterAndPresentOfficesPanel.
     */
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
        
        
        add(dropdownCountry, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        
        ReadFromDatabaseButton = new JButton("Execute");
        add(ReadFromDatabaseButton, gbc);
        
        CountryDropDownListener countryListener = new CountryDropDownListener(dropdownCountry);
        countryListener.actionPerformed(null);
        
        
        FilterAndPresentOfficesListener officeListener = new FilterAndPresentOfficesListener(this);
        ReadFromDatabaseButton.addActionListener(officeListener);
        

         
	}
	
	/**
     * Returns the dropdown menu for country selection.
     *
     * @return the dropdown menu for country selection
     */

    public JComboBox<String> getDropdownCountry() {
        return dropdownCountry;
    }
    
  


    
}
