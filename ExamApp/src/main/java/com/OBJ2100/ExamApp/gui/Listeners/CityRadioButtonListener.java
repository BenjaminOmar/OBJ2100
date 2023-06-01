package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

/**
 * ActionListener implementation that handles the event when the city radio button is selected.
 * It enables the city dropdown and disables the state dropdown in the ListCustomersPanel.
 * This listener is used to switch the search criteria to "By City".
 * 
 * @author 7162
 */
public class CityRadioButtonListener implements ActionListener{

    private final ListCustomersPanel panel; 

    /**
     * Constructs a CityRadioButtonListener with the specified ListCustomersPanel.
     *
     * @param panel The ListCustomersPanel instance.
     */
    public CityRadioButtonListener(ListCustomersPanel panel){
        this.panel = panel;
    }
    
    /**
     * Handles the action event triggered when the city radio button is selected.
     * It enables the city dropdown and disables the state dropdown in the ListCustomersPanel.
     *
     * @param e The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent e){
        panel.getDropdownCity().setEnabled(true);
        panel.getDropdownState().setEnabled(false);
    }
    
}
