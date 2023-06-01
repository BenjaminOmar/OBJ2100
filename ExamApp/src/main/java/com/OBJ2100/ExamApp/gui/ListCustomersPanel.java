package com.OBJ2100.ExamApp.gui;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.OBJ2100.ExamApp.gui.listeners.CityDropDownListener;
import com.OBJ2100.ExamApp.gui.listeners.CityRadioButtonListener;
import com.OBJ2100.ExamApp.gui.listeners.ShowCustomerListener;
import com.OBJ2100.ExamApp.gui.listeners.StateDropdownListener;
import com.OBJ2100.ExamApp.gui.listeners.StateRadioButtonListener;
import com.OBJ2100.ExamApp.gui.listeners.WriteToFileListener;


/**
 * A panel for listing customers and selecting search criteria.
 * This class extends JPanel to provide a custom panel component.
 * It implements functionality to display search options for city and state.
 * It also provides a button to write selected criteria to a file.
 * The class utilizes Swing components for GUI elements and listeners for user interaction.
 * @author 7162
 */

public class ListCustomersPanel extends JPanel {

    private JRadioButton byCityRadioButton;
    private JLabel byCityLabel;
    private JComboBox<String> dropdownCity;
    private JRadioButton byStateRadioButton;
	private JLabel byStateLabel;
    private JComboBox<String> dropdownState;
    private JButton writeToFileButton;
    private ButtonGroup radioButtonGroup;
    private WriteToFileListener writeToFileListener;

    /**
     * Constructs a ListCustomersPanel object.
     * This initializes the panel with appropriate components and layout.
     * It sets up radio buttons, dropdowns, and a button for user interaction.
     * It also attaches listeners to handle events.
     */
    public ListCustomersPanel() {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "List Customers"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Create a radio button for selecting search by city
        byCityRadioButton = new JRadioButton("City");
        add(byCityRadioButton, gbc);
        gbc.gridx = 1;
        
    	// Create a label for the city selection
        byCityLabel = new JLabel("By City:");
        add(byCityLabel, gbc);
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Create a dropdown menu for city selection
        dropdownCity = new JComboBox<>();
        dropdownCity.addItem("Select City");
        // Disable the dropdown initially
        dropdownCity.setEnabled(false);
        add(dropdownCity, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        
        // Create a radio button for selecting search by state
        byStateRadioButton = new JRadioButton("State");
        add(byStateRadioButton, gbc);
        gbc.gridx = 1;
        
        // Create a label for the state selection
        byStateLabel = new JLabel("By State:");
        add(byStateLabel, gbc);
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Create a dropdown menu for state selection
        dropdownState = new JComboBox<>();
        // Disable the dropdown initially
        dropdownState.setEnabled(false);
        dropdownState.addItem("Select State");
        add(dropdownState, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 0, 0, 0);
        
        //Create a button for writing the customers list to a file
        writeToFileButton = new JButton("Write customers list that match selected criteria into file...");
        add(writeToFileButton, gbc);

        // Create a button group to manage the radio buttons
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(byCityRadioButton);
        radioButtonGroup.add(byStateRadioButton);
        
        //Creates listeners for user interactions
        CityRadioButtonListener cityListener = new CityRadioButtonListener(this);
        byCityRadioButton.addActionListener(cityListener);
        byCityRadioButton.addActionListener(new CityDropDownListener(dropdownCity));
        
        
        StateRadioButtonListener stateListener = new StateRadioButtonListener(this);
        byStateRadioButton.addActionListener(stateListener);
        byStateRadioButton.addActionListener(new StateDropdownListener(dropdownState));
       
        
        writeToFileListener = new WriteToFileListener(this);
        writeToFileButton.addActionListener(writeToFileListener);        
        writeToFileButton.addActionListener(new ShowCustomerListener(this));
       
    }

    public JComboBox<String> getDropdownCity() {
        return dropdownCity;
    }

    public JComboBox<String> getDropdownState() {
        return dropdownState;
    }

    public JRadioButton getByCityRadioButton() {
    	return byCityRadioButton;
    }

    public JRadioButton getByStateRadioButton() {
		return byStateRadioButton;
	}
    
}
