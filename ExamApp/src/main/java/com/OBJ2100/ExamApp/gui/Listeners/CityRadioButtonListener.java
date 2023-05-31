package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

public class CityRadioButtonListener implements ActionListener{

    private final ListCustomersPanel panel; 

    public CityRadioButtonListener(ListCustomersPanel panel){
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        panel.getDropdownCity().setEnabled(true);
        panel.getDropdownState().setEnabled(false);
    }
    
}
