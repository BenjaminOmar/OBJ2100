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
import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Customer;

/**
 * ActionListener implementation that handles the event when the state dropdown is selected.
 * It populates the state dropdown with distinct states from the database.
 * This listener is used to fetch the available states for selection.
 * 
 * @author 7162
 */
public class StateDropdownListener implements ActionListener {
	private JComboBox<String> dropdownState;
	private List<String> states;
	
	  /**
     * Constructs a StateDropdownListener with the specified dropdownState.
     *
     * @param dropdownState The JComboBox representing the state dropdown.
     */
	public StateDropdownListener(JComboBox<String> dropdownSate) {
        this.dropdownState = dropdownSate;
        this.states = null;
    };
    
    
    /**
     * Handles the action event triggered when the state dropdown is selected.
     * It populates the state dropdown with distinct states fetched from the database.
     *
     * @param e The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (states != null) return;// Do nothing if states are already populated
    	populateStateDropdown();
    }
    
    /**
     * Populates the state dropdown with states fetched from the database.
     */
    private void populateStateDropdown() {
    	DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			DaoFactory daoFactory = new JdbcDaoFactory(connection);
			CustomerDao customerDao = daoFactory.getCustomerDao();
			
			List<Customer> customers = customerDao.getAll();
            
            List<String> states = new ArrayList<>();

            for (Customer customer : customers){
                String state = customer.getState();
                 if (!states.contains(state)){
                    states.add(state); 
                }
             }

            dropdownState.removeAllItems();
            for (String state : states) {;
				dropdownState.addItem(state);
            }

		} catch (SQLException err) {
			err.printStackTrace();			
		}
    }
}
