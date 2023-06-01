package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.documents.DocumentsManager;
import com.OBJ2100.ExamApp.entities.Customer;
import com.OBJ2100.ExamApp.gui.ListCustomersPanel;

public class WriteToFileListener implements ActionListener {

	private ListCustomersPanel panel;

    public WriteToFileListener(ListCustomersPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCity = (String) panel.getDropdownCity().getSelectedItem();
        String selectedState = (String) panel.getDropdownState().getSelectedItem();

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
        	
            File file = new File(DocumentsManager.getFolderPath(), generateFilename());
            
            try {
            	DocumentsManager manager = new DocumentsManager();
                manager.writeToFile(customersData, file);
                JOptionPane.showMessageDialog(null, "Customer list written to file.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error writing to file");
            }
        }
    }
    
    private String generateFilename() {
    	String chosenCityOrState = null;
    	if (panel.getByCityRadioButton().isSelected())
    		chosenCityOrState = "city";
    	else if (panel.getByStateRadioButton().isSelected())
    		chosenCityOrState = "state";
    	
    	String timestamp = LocalDateTime.now()
				.format(DocumentsManager.DEFAULT_TIMESTAMP_FORMATTER);
    	
    	String name = Arrays.asList("customers", chosenCityOrState, timestamp).stream()
    			.filter(s -> s != null && s.length() > 0)
    			.collect(Collectors.joining("-"));
    	return String.format("%s.csv", name);
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
