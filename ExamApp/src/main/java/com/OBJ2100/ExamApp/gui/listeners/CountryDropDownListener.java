package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JComboBox;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.OfficeDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Office;



/**
 * This class represents a listener for the country dropdown menu.
 * It populates the dropdown menu with country names.
 * 
 * @author 7162
 */
public class CountryDropDownListener implements ActionListener{
	
	private JComboBox<String> dropdownCountry;
	private List<String> countries = null; 
	
	/**
	 * Constructs a new CountryDropDownListener.
	 * 
	 * @param dropdownCountry the dropdown menu for country selection
	 */	
	public CountryDropDownListener(JComboBox<String> dropdownCountry) {
		this.dropdownCountry = dropdownCountry;
	    this.countries = null;
	}
	 
	 /**
	  * Called when an action event occurs.
	  * If the countries list is already populated, do nothing.
	  * Otherwise, populate the country dropdown menu.
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (countries != null) return; // Do nothing if cities are already populated
		populateCountryDropdown();       
	}
	 
	/**
	  * Populates the country dropdown menu with country names.
	  */
	private void populateCountryDropdown() {
		DataSource source = DataSourceFactory.getMySqlDataSource();
		    try (Connection connection = source.getConnection()) {
		        DaoFactory daoFactory = new JdbcDaoFactory(connection);
		        OfficeDao officeDao = daoFactory.getOfficeDao();
		        List<Office> offices = officeDao.getAll();
		        List<String> countries = new ArrayList<>();
		        for (Office office : offices) {
		            String country = office.getCountry();
		            if (!countries.contains(country)) {
		                countries.add(country);
		            }
		        }
		        dropdownCountry.removeAllItems();
		        for (String country : countries) {
		            dropdownCountry.addItem(country);
		        }
		    } catch (SQLException err) {
		        err.printStackTrace();
		    }
		}    
}