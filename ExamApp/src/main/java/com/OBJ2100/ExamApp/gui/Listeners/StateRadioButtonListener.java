package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

public class StateRadioButtonListener implements ActionListener {

    private final ListCustomersPanel panel; 

    public StateRadioButtonListener(ListCustomersPanel panel){
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        panel.getDropdownState().setEnabled(true);
        panel.getDropdownCity().setEnabled(false);
    }
    
}
