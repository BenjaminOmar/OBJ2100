package com.OBJ2100.ExamApp.gui.listeners;

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
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcCustomerDao;
import com.OBJ2100.ExamApp.documents.DocumentsManager;
import com.OBJ2100.ExamApp.documents.FolderManager;
import com.OBJ2100.ExamApp.entities.Customer;
import com.OBJ2100.ExamApp.gui.ListCustomersPanel;
import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;

/**
 * ActionListener for writing customer data according to the
 * chosen criteria (city or state) from the ListCustomersPanel and
 * store the result in a file.
 * 
 * @author 7162
 */

public class WriteToFileListener implements ActionListener {

	private ListCustomersPanel panel;

	/**
     * Constructs a new WriteToFileListener.
     * 
     * @param panel The ListCustomersPanel to retrieve selected city and state from.
     */
    public WriteToFileListener(ListCustomersPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	// Get the selected city and state from the GUI
        String selectedCity = (String) panel.getDropdownCity().getSelectedItem();
        String selectedState = (String) panel.getDropdownState().getSelectedItem();
        DataSource source = DataSourceFactory.getMySqlDataSource();
        try (Connection connection = source.getConnection()) {
          DaoFactory daoFactory = new JdbcDaoFactory(connection);
          CustomerDao customerDao = daoFactory.getCustomerDao();
          List<Customer> matchingCustomers = customerDao.getMatchingCustomers(selectedCity, selectedState);
          if (matchingCustomers.isEmpty()){
          	// If no matching customers found, display a message
              JOptionPane.showMessageDialog(null, "No matching customers found");
          }else{
          	// Create a StringBuilder to construct the customer data string
          	StringBuilder sb = new StringBuilder();
          	sb.append("Customer Number, Customer Name, Contact Lastname, Contact firstname, Phone, Addressline 1, Addressline 2"
          			+ "City, State, PostalCode, Country, SalesREP Employee Number, Credit limit\n");
          	
          	// Iterate over the matching customers and append their data to the StringBuilder
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
          	
              File file = new File(FolderManager.getFolderPath(), generateFilename());
              
              try {
              	DocumentsManager manager = new DocumentsManager();
              	// Write the customer data to the file
                  manager.writeToFile(customersData, file);
                  MessageHelper.displayMessage("Customer list written to file.", "Succsess!");
              } catch (IOException ex) {
                  ex.printStackTrace();
                  MessageHelper.displayMessage("Error writing to file", "Error!");
              }
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
    }
    
    /**
     * Generates a unique filename for the customer data file.
     * 
     * @return The generated filename.
     */
    private String generateFilename() {
    	String chosenCityOrState = null;
    	if (panel.getByCityRadioButton().isSelected())
    		chosenCityOrState = (String) panel.getDropdownCity().getSelectedItem();
    	else if (panel.getByStateRadioButton().isSelected())
    		chosenCityOrState = (String) panel.getDropdownState().getSelectedItem();
    	
    	 // Generate a timestamp using the current date and time
    	String timestamp = LocalDateTime.now()
				.format(DocumentsManager.DEFAULT_TIMESTAMP_FORMATTER);
    	
    	// Generate the filename based on chosen city/state and timestamp
    	String name = Arrays.asList("customers", chosenCityOrState, timestamp).stream()
    			.filter(s -> s != null && s.length() > 0)
    			.collect(Collectors.joining("-"));
    	return String.format("%s.csv", name);
    }
    
  
}
