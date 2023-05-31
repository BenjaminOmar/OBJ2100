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

public class StateDropdownListener implements ActionListener {
	private JComboBox<String> dropdownState;
	private List<String> states;
	
	public StateDropdownListener(JComboBox<String> dropdownSate) {
        this.dropdownState = dropdownSate;
        this.states = null;
    };

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (states != null) return;
    	populateStateDropdown();
    }
    
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
