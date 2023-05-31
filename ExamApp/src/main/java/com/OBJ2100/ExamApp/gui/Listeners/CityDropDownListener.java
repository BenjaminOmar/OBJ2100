package com.OBJ2100.ExamApp.gui.Listeners;

import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.entities.Customer;
import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CityDropDownListener implements ActionListener {

    private final CustomerDao customerDao;
    private final ListCustomersPanel panel;

    public CityDropDownListener(CustomerDao customerDao, ListCustomersPanel panel) {
        this.customerDao = customerDao;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.getDropdownCity().removeAllItems();
        panel.getDropdownCity().addItem("Select City");

        List<Customer> customers = customerDao.getAll();

        for (Customer customer : customers) {
            String city = customer.getCity();
            if (!city.equals(panel.getDropdownCity().getSelectedItem())) {
                panel.getDropdownCity().addItem(city);
            }
        }

        panel.getDropdownCity().setEnabled(true);
        panel.getDropdownState().setEnabled(false);
    }
}
