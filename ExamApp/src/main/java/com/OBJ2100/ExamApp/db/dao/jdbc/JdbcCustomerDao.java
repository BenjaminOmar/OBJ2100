package com.OBJ2100.ExamApp.db.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.entities.Customer;

public class JdbcCustomerDao extends JdbcDao<Customer> implements CustomerDao {
	
	public JdbcCustomerDao(Connection connection) {
		super(connection);
	}
	
	@Override
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<>();

		try (Statement s = connection.createStatement()) {
			ResultSet rs = s.executeQuery("SELECT * FROM customers");
			while (rs.next()) {
				customers.add(extractEntity(rs));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
	}
	
	@Override
	protected Customer extractEntity(ResultSet rs) throws SQLException {
		int customerNumber = rs.getInt("customerNumber");
		String customerName = rs.getString("customerName");
		String contactLastName = rs.getString("contactLastName");
		String contactFirstName = rs.getString("contactFirstName");
		String phone = rs.getString("phone");
		String addressLine1 = rs.getString("addressLine1");
		String addressLine2 = rs.getString("addressLine2");
		String city = rs.getString("city");
		String state = rs.getString("state");
		String postalCode = rs.getString("postalCode");
		String country = rs.getString("country");
		int salesRepEmployeeNumber = rs.getInt("salesRepEmployeeNumber");
		double creditLimit = rs.getDouble("creditLimit");
		return new Customer.Builder(customerNumber)
				.customerName(customerName)
				.contactLastName(contactLastName)
				.contactFirstName(contactFirstName)
				.phone(phone)
				.addressLine1(addressLine1)
				.addressLine2(addressLine2)
				.city(city)
				.state(state)
				.postalCode(postalCode)
				.country(country)
				.salesRepEmployeeNumber(salesRepEmployeeNumber)
				.creditLimit(creditLimit)
				.build();
	}
	
	/**
	 * Retrieves a list of customers based on their city.
	 *
	 * @param city, the city to search for.
	 * @return A list of customers in the specified city.
	 * @author 7162
	 */

	@Override
	public List<Customer> getByCity(String city){
		// Create a new ArrayList to store the customers
		List<Customer> customers = new ArrayList<>();

		try(PreparedStatement s = connection.prepareStatement("SELECT * FROM customers WHERE city = ?")){
			// Set the value of the first parameter in the prepared statement as the provided city
			s.setString(1, city);
			 // Execute the query and get the result set
			ResultSet rs = s.executeQuery();
			// Iterate over each row in the result set
			while (rs.next()){
				// Extract customer entity from the result set and add it to the list
				customers.add(extractEntity(rs));
			}
			// Close the prepared statement
			s.close();
		}catch (SQLException e){
			// Print the stack trace if a SQLException occurs
			e.printStackTrace();
		}
		// Return the list of customers
		return customers; 
	}
	
	/**
	 * Retrieves a list of customers based on their state.
	 *
	 * @param state, the state to search for.
	 * @return A list of customers in the specified state.
	 * @author 7162
	 */

	@Override  
	public List<Customer> getByState(String state) {
		// Create a new ArrayList to store the customers
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement s = connection.prepareStatement("SELECT * FROM customers WHERE state = ?")) {
        	// Set the value of the first parameter in the prepared statement as the provided state
        	s.setString(1, state);
        	// Execute the query and get the result set
            ResultSet rs = s.executeQuery();
         // Iterate over each row in the result set
            while (rs.next()) {
            	//Extract customer entity from the result set and add it to the list
                customers.add(extractEntity(rs));
            }
         // Close the prepared statement
            s.close();
        } catch (SQLException e) {
        	// Print the stack trace if a SQLException occurs
            e.printStackTrace();
        }
     // Return the list of customers
        return customers;
    }
}

