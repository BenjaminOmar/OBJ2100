package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

/**
 * ActionListener implementation that handles the event when the state radio button is selected.
 * It enables the state dropdown and disables the city dropdown in the ListCustomersPanel.
 * This listener is used to switch the search criteria to "By State".
 * 
 * @author 7162
 */
public class StateRadioButtonListener implements ActionListener {

    private final ListCustomersPanel panel; 

    /**
     * Constructs a StateRadioButtonListener with the specified ListCustomersPanel.
     *
     * @param panel The ListCustomersPanel instance.
     */
    public StateRadioButtonListener(ListCustomersPanel panel){
        this.panel = panel;
    }

    /**
     * Handles the action event triggered when the state radio button is selected.
     * It enables the state dropdown and disables the city dropdown in the ListCustomersPanel.
     *
     * @param e The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent e){
        panel.getDropdownState().setEnabled(true);
        panel.getDropdownCity().setEnabled(false);
    }
    
}
