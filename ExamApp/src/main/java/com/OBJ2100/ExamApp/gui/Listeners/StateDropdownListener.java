package com.OBJ2100.ExamApp.gui.Listeners;

import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.entities.Customer;
import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StateDropdownListener implements ActionListener {

    private final CustomerDao customerDao;
    private final ListCustomersPanel panel;

    public StateDropdownListener(CustomerDao customerDao, ListCustomersPanel panel) {
        this.customerDao = customerDao;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.getDropdownState().removeAllItems();
        panel.getDropdownState().addItem("Select State");

        List<Customer> customers = customerDao.getAll();

        for (Customer customer : customers) {
            String state = customer.getState();
            if (!state.equals(panel.getDropdownState().getSelectedItem())) {
                panel.getDropdownState().addItem(state);
            }
        }

        panel.getDropdownState().setEnabled(true);
        panel.getDropdownCity().setEnabled(false);
    }
}
