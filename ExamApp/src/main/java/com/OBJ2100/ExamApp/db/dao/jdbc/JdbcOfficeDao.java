package com.OBJ2100.ExamApp.db.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OBJ2100.ExamApp.db.dao.OfficeDao;
import com.OBJ2100.ExamApp.entities.Office;

/**
 * DAO implementation for offices.
 * 
 * @author 7154, 7162
 *
 */
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
}
