package com.OBJ2100.ExamApp.db.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OBJ2100.ExamApp.db.dao.OfficeDao;
import com.OBJ2100.ExamApp.entities.Office;

public class JdbcOfficeDao extends JdbcDao<Office> implements OfficeDao {

	public JdbcOfficeDao(Connection connection) {
		super(connection);
	}

	@Override
	public List<Office> getAll() {
		List<Office> offices = new ArrayList<>();

		try (Statement s = connection.createStatement()) {
			ResultSet rs = s.executeQuery("SELECT * FROM offices");
			while (rs.next()) {
				offices.add(extractEntity(rs));
			}
			
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return offices;
	}

	@Override
	protected Office extractEntity(ResultSet rs) throws SQLException {
		String officeCode = rs.getString("officeCode");
		String city = rs.getString("city");
		String phone = rs.getString("phone");
		String addressLine1 = rs.getString("addressLine1");
		String addressLine2 = rs.getString("addressLine2");
		String state = rs.getString("state");
		String country = rs.getString("country");
		String postalCode = rs.getString("postalCode");
		String territory = rs.getString("territory");
		return new Office.Builder(officeCode).city(city).phone(phone).addressLine1(addressLine1)
				.addressLine2(addressLine2).state(state).country(country).postalCode(postalCode)
				.territory(territory).build();
	}
	
	/**
	 * Retrieves a list of offices based on their country.
	 *
	 * @param country, the country to search for.
	 * @return A list of offices in the specified country.
	 * @author 7162
	 */
	@Override
	public List<Office> getCustomerByCountry(String country){
		// Create a new ArrayList to store the offices
		List<Office> offices = new ArrayList<>();
		try (PreparedStatement s = connection.prepareStatement("SELECT * FROM offices WHERE country = ?")){
			// Set the value of the first parameter in the prepared statement as the provided state
        	s.setString(1, country);
        	// Execute the query and get the result set
        	ResultSet rs = s.executeQuery();
        	// Iterate over each row in the result set
        	while (rs.next()) {
            	//Extract customer entity from the result set and add it to the list
                offices.add(extractEntity(rs));
            }
        	// Close the prepared statement
            s.close();
		}catch (SQLException e) {
        	// Print the stack trace if a SQLException occurs
            e.printStackTrace();
        }
     // Return the list of customers
        return offices;
	}
}
