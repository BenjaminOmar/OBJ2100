package com.OBJ2100.ExamApp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCustomersPanel extends JPanel {

    private final JRadioButton byCityRadioButton;
    private final JLabel byCityLabel;
    private final JComboBox<String> dropdownCity;
    private final JRadioButton byStateRadioButton;
    private final JLabel byStateLabel;
    private final JComboBox<String> dropdownState;
    private final JButton writeToFileButton;

    public ListCustomersPanel() {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "List Customers"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 10);

        byCityRadioButton = new JRadioButton("City");
        add(byCityRadioButton, gbc);

        gbc.gridx = 1;
        byCityLabel = new JLabel("Label City:");
        add(byCityLabel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dropdownCity = new JComboBox<>();
        add(dropdownCity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        byStateRadioButton = new JRadioButton("State");
        add(byStateRadioButton, gbc);

        gbc.gridx = 1;
        byStateLabel = new JLabel("Label State:");
        add(byStateLabel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dropdownState = new JComboBox<>();
        add(dropdownState, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 0, 0, 0);
        writeToFileButton = new JButton("Write customers list that match selected criteria into file...");
        add(writeToFileButton, gbc);

        // Set up action listener for the button
        writeToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click event
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("List Customers Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ListCustomersPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
