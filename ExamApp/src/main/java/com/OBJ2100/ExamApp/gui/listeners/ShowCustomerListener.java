package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcCustomerDao;
import com.OBJ2100.ExamApp.entities.Customer;
import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

/**
 * ActionListener for showing customer data according to the
 * chosen criteria (city or state) from the ListCustomersPanel and
 * presenting the result in a new window. 
 * 
 * @author 7162
 */

public class ShowCustomerListener implements ActionListener {

    private ListCustomersPanel panel;

    /**
     * Constructs the listener with the specified ListCustomersPanel.
     * 
     * @param panel The ListCustomersPanel to associate with the listener.
     */
    public ShowCustomerListener(ListCustomersPanel panel) {
        this.panel = panel;
    }

    /**
     * Handles the actionPerformed event.
     * Retrieves selected city and state from the panel,
     * fetches matching customers from the database,
     * and displays the result in a table using JOptionPane.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	// Get the selected city and state from the GUI
        String selectedCity = (String) panel.getDropdownCity().getSelectedItem();
        String selectedState = (String) panel.getDropdownState().getSelectedItem();
        JdbcCustomerDao customerDao = new JdbcCustomerDao(null);
        // Retrieve the matching customers from the database
        List<Customer> matchingCustomers = customerDao.getMatchingCustomers(selectedCity, selectedState);

        if (matchingCustomers == null) {
            JOptionPane.showMessageDialog(null, "Error retrieving customers from the database", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (matchingCustomers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No matching customers found");
        } else {
        	// Define column names for the table
            String[] columnNames = {"Customer Number", "Customer Name", "Contact Lastname", "Contact firstname",
                    "Phone", "Addressline 1", "Addressline 2", "City", "State", "PostalCode", "Country",
                    "SalesREP Employee Number", "Credit limit"};

            // Create a table model with the column names
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            // Populate the model with customer data
            for (Customer customer : matchingCustomers) {
                Object[] rowData = {
                        customer.getCustomerNumber(),
                        customer.getCustomerName(),
                        customer.getContactLastName(),
                        customer.getContactFirstName(),
                        customer.getPhone(),
                        customer.getAddressLine1(),
                        customer.getAddressLine2(),
                        customer.getCity(),
                        customer.getState(),
                        customer.getPostalCode(),
                        customer.getCountry(),
                        customer.getSalesRepEmployeeNumber(),
                        customer.getCreditLimit()
                };
                model.addRow(rowData);
            }

            // Create a table using the model and configure it
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
            
            // Create a scroll pane for the table and set its size
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(1500, 600));
            // Show the table in a JOptionPane message dialog
            JOptionPane.showMessageDialog(null, scrollPane, "Matching Customers", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
