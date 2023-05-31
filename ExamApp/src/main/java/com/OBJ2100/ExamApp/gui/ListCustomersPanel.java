package com.OBJ2100.ExamApp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.OBJ2100.ExamApp.gui.listeners.CityDropDownListener;
//import com.OBJ2100.ExamApp.gui.Listeners.StateDropdownListener;
import com.OBJ2100.ExamApp.gui.listeners.CityRadioButtonListener;
import com.OBJ2100.ExamApp.gui.listeners.StateDropdownListener;
import com.OBJ2100.ExamApp.gui.listeners.StateRadioButtonListener;

//import com.OBJ2100.ExamApp.gui.Listeners.WriteToFileListener;

public class ListCustomersPanel extends JPanel {

    private final JRadioButton byCityRadioButton;
    private final JLabel byCityLabel;
    private final JComboBox<String> dropdownCity;
    private final JRadioButton byStateRadioButton;
    private final JLabel byStateLabel;
    private final JComboBox<String> dropdownState;
    private final JButton writeToFileButton;
    private final ButtonGroup radioButtonGroup;


    /**
     * A panel for listing customers and selecting search criteria.
     * 
     * @author 7162
     */
    public ListCustomersPanel() {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "List Customers"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        byCityRadioButton = new JRadioButton("City");
        add(byCityRadioButton, gbc);

        gbc.gridx = 1;
        byCityLabel = new JLabel("By City:");
        add(byCityLabel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dropdownCity = new JComboBox<>();
        dropdownCity.addItem("Select City");
        dropdownCity.setEnabled(false);
        add(dropdownCity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;

        gbc.fill = GridBagConstraints.NONE;
        byStateRadioButton = new JRadioButton("State");
        add(byStateRadioButton, gbc);

        gbc.gridx = 1;
        byStateLabel = new JLabel("By State:");
        add(byStateLabel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dropdownState = new JComboBox<>();
        dropdownState.setEnabled(false);
        dropdownState.addItem("Select State");
        add(dropdownState, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 0, 0, 0);
        writeToFileButton = new JButton("Write customers list that match selected criteria into file...");
        add(writeToFileButton, gbc);

        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(byCityRadioButton);
        radioButtonGroup.add(byStateRadioButton);

        // Set up action listener for the buttons/dropdown menu
        CityRadioButtonListener cityListener = new CityRadioButtonListener(this);
        byCityRadioButton.addActionListener(cityListener);
        byCityRadioButton.addActionListener(new CityDropDownListener(dropdownCity));
        
        StateRadioButtonListener stateListener = new StateRadioButtonListener(this);
        byStateRadioButton.addActionListener(stateListener);
        byStateRadioButton.addActionListener(new StateDropdownListener(dropdownState));

        // TODO cityDropDownListener = new CityDropDownListener(dropdownCity);
        // stateDropdownListener = new StateDropdownListener(this);
        // TODO dropdownCity.addActionListener(cityDropDownListener);
        // dropdownState.addActionListener(stateDropdownListener);
       
        //writeToFileButton.addActionListener((ActionListener) this );
    }

    public JComboBox<String> getDropdownCity() {
        return dropdownCity;
    }

    public JComboBox<String> getDropdownState() {
        return dropdownState;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("List Customers Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ListCustomersPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
