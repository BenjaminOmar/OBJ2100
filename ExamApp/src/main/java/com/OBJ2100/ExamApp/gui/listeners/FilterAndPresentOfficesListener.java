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
import com.OBJ2100.ExamApp.db.dao.OfficeDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Office;
import com.OBJ2100.ExamApp.gui.FilterAndPresentOfficesPanel;


/**
 * This class represents a listener for filtering and presenting offices.
 * It retrieves office data based on the selected country and displays it in a table.
 * 
 * @author 7162
 */
public class FilterAndPresentOfficesListener implements ActionListener {

    private FilterAndPresentOfficesPanel panel;
    

    /**
     * Constructs a new FilterAndPresentOfficesListener.
     * 
     * @param panel the panel containing the filter and presentation components
     */
    public FilterAndPresentOfficesListener(FilterAndPresentOfficesPanel panel) {
        this.panel = panel;
    }
    
    /**
     * Called when an action event occurs.
     * Retrieves the selected country from the dropdown menu,
     * retrieves matching offices from the database,
     * and displays the office data in a table.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       String selectedCountry = (String) panel.getDropdownCountry().getSelectedItem();
       
       DataSource source = DataSourceFactory.getMySqlDataSource();
       
       try(Connection connection = source.getConnection()){
    	   DaoFactory daoFactory = new JdbcDaoFactory(connection);
    	   OfficeDao officeDao = daoFactory.getOfficeDao();
    	   List<Office> matchingOffices = officeDao.getCustomerByCountry(selectedCountry);
    	   
    	   if (matchingOffices == null ) {
    		   JOptionPane.showMessageDialog(null, "Error retrieving offices from the database", "Error", JOptionPane.ERROR_MESSAGE);
    	   }else if (matchingOffices.isEmpty()) {
               JOptionPane.showMessageDialog(null, "No matching offices found");
    	   }else {
    		   //Define column names for the table
    		   String[] columnNames = {"Office Code", "City", "Phone", "Addresline 1", "Adressline 2", "State", "Country", "Postal code", "Territory"};
    		   
    		   //Create a table model with the colum names
    		   DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    		   // Populate the model with customer data
    		   for (Office office : matchingOffices) {
    			   Object[] rowData = {
    					    office.getOfficeCode(),
    					    office.getCity(),
    					    office.getPhone(),
    					    office.getAddressLine1(),
    					    office.getAddressLine2(),
    					    office.getState(),
    					    office.getCountry(),
    					    office.getPostalCode(),
    					    office.getTerritory()
    					};
                   model.addRow(rowData);
               }
    		   //Create a table using the mode and configure it
    		   JTable table = new JTable(model);
               table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
               
               // Create a scroll pane for the table and set its size
               JScrollPane scrollPane = new JScrollPane(table);
               scrollPane.setPreferredSize(new Dimension(1300, 600));
               
               JOptionPane.showMessageDialog(null, scrollPane, "Matching Countries", JOptionPane.INFORMATION_MESSAGE);
    	   }
       }catch (SQLException e1) {
           e1.printStackTrace();
         };
    }
}