package com.OBJ2100.ExamApp.gui.Listeners;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcCustomerDao;
import com.OBJ2100.ExamApp.documents.DocumentsManager;
import com.OBJ2100.ExamApp.documents.IDocumentsManager;
import com.OBJ2100.ExamApp.entities.Customer;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WriteToFileListener implements ActionListener {

    private JComboBox<String> dropdownCity;
    private JComboBox<String> dropdownState;

    public WriteToFileListener(JComboBox<String> dropdownCity, JComboBox<String> dropdownState) {
        this.dropdownCity = dropdownCity;
        this.dropdownState = dropdownState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCity = (String) dropdownCity.getSelectedItem();
        String selectedState = (String) dropdownState.getSelectedItem();

        List<Customer> matchingCustomers = getMatchingCustomers(selectedCity, selectedState);

        if (matchingCustomers.isEmpty()){
            JOptionPane.showMessageDialog(null, "No matching customers found");
        }else{
        	StringBuilder sb = new StringBuilder();
        	sb.append("Customer Number, Customer Name, Contact Lastname, Contact firstname, Phone, Addressline 1, Addressline 2"
        			+ "City, State, PostalCode, Country, SalesREP Employee Number, Credit limit\n");
        		
        	for (Customer customer : matchingCustomers) {
                   sb.append(customer.getCustomerNumber()).append(",")
                     .append(customer.getCustomerName()).append(",")
                     .append(customer.getContactLastName()).append(",")
                     .append(customer.getContactFirstName()).append(",")
                     .append(customer.getPhone()).append(",")
                     .append(customer.getAddressLine1()).append(",")
                     .append(customer.getAddressLine2()).append(",")
                     .append(customer.getCity()).append(",")
                     .append(customer.getState()).append(",")
                     .append(customer.getPostalCode()).append(",")
                     .append(customer.getCountry()).append(",")
                     .append(customer.getSalesRepEmployeeNumber()).append(",")
                     .append(customer.getCreditLimit()).append("\n");
            }
        	String customersData = sb.toString();
        	
            IDocumentsManager documentsManager = new DocumentsManager();
            String timestamp = Long.toString(System.currentTimeMillis()); 
            File file = new File("C:/Temp/customers_" + timestamp + ".csv");
            
            try {
                documentsManager.writeToFile(customersData, file);
                JOptionPane.showMessageDialog(null, "Customer list written to file.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error writing to file");
            }
        }
    }

    private List<Customer> getMatchingCustomers(String city, String state) {
        DataSource source = DataSourceFactory.getMySqlDataSource();
    
        try (Connection connection = source.getConnection()) {
            DaoFactory daoFactory = new JdbcDaoFactory(connection);
            CustomerDao customerDao = daoFactory.getCustomerDao();
    
            if (city != null && !city.equals("Select City")) {
                return customerDao.getByCity(city);
            } else if (state != null && !state.equals("Select State")) {
                return customerDao.getByState(state);
            } else {
                return customerDao.getAll();
            }
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }
    }
}
