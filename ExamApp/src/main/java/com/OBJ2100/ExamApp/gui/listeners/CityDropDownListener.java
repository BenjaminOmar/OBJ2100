package com.OBJ2100.ExamApp.gui.listeners;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Customer;
import javax.sql.DataSource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ActionListener implementation that handles the event when the city dropdown is selected.
 * It populates the city dropdown with cities fetched from the database.
 * This listener is used to fetch the available states for selection.
 * @author 7162
 */
public class CityDropDownListener implements ActionListener {
    private JComboBox<String> dropdownCity;
    private List<String> cities;
    
    /**
     * Constructs a CityDropDownListener with the specified dropdownState.
     *
     * @param dropdownCity The JComboBox component for city dropdown.
     */
    public CityDropDownListener(JComboBox<String> dropdownCity) {
        this.dropdownCity = dropdownCity;
        this.cities = null;
    }
    
    
    /**
     * Handles the action event triggered when the city dropdown is selected.
     * It populates the city dropdown with distinvt cities fetched from the database.
     * @param e The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (cities != null) return; // Do nothing if cities are already populated
        populateCityDropdown();       
    }
    
    /**
     * Populates the city dropdown with cities fetched from the database.
     */
    private void populateCityDropdown() {
    	DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			DaoFactory daoFactory = new JdbcDaoFactory(connection);
			CustomerDao customerDao = daoFactory.getCustomerDao();
			
			List<Customer> customers = customerDao.getAll();
            
            List<String> cities = new ArrayList<>();

            for (Customer customer : customers){
                String city = customer.getCity();
                 if (!cities.contains(city)){
                    cities.add(city); 
            }
        }

        dropdownCity.removeAllItems();
        	for (String city : cities) {;
				dropdownCity.addItem(city);
            }

		} catch (SQLException err) {
			err.printStackTrace();			
		}
    }
}


