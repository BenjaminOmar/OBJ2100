package com.OBJ2100.ExamApp.gui.Listeners;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcCustomerDao;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcDao;
import com.OBJ2100.ExamApp.entities.Customer;
import javax.sql.DataSource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CityDropDownListener implements ActionListener {
    private JComboBox<String> dropdownCity;

    public CityDropDownListener(JComboBox<String> dropdownCity) {
        this.dropdownCity = dropdownCity;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			 JdbcCustomerDao customerDao = new JdbcCustomerDao(connection);
             List<Customer> customers = customerDao.getAll();

            
            List<String> cities = new ArrayList<>();

            for (Customer customer : customers){
                String city = customer.getCity();
                 if (!cities.contains(city)){
                    cities.add(city); 
                }
             }

            //dropdownCity.removeAllItems();
            
            for (String city : cities){
                 dropdownCity.addItem(city);
            }

		} catch (SQLException err) {
			err.printStackTrace();			
		}	        
    }   

     public static void main(String[] args) {
        // Opprett en JComboBox for testing
        JComboBox<String> dropdownCity = new JComboBox<>();

        // Opprett en CityDropDownListener og koble den til JComboBox
        CityDropDownListener listener = new CityDropDownListener(dropdownCity);
        dropdownCity.addActionListener(listener);

        // Opprett en JFrame og legg til JComboBox i innholdspanelet
        JFrame frame = new JFrame("City Drop Down Listener Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(dropdownCity);
        frame.pack();
        frame.setVisible(true);

        DataSource source = DataSourceFactory.getMySqlDataSource();
    try (Connection connection = source.getConnection()) {
        List<Customer> customers = new JdbcCustomerDao(connection).getAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    } catch (SQLException err) {
        err.printStackTrace();
    }
    }
}

