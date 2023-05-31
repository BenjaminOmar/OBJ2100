package com.OBJ2100.ExamApp.db.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.OBJ2100.ExamApp.db.dao.EmployeeDao;
import com.OBJ2100.ExamApp.entities.Employee;

public class JdbcEmployeeDao extends JdbcDao<Employee> implements EmployeeDao {

	public JdbcEmployeeDao(Connection connection) {
		super(connection);
	}

	@Override
	public Optional<Employee> getByEmployeeNumber(int employeeNumber) {
		Employee employee = null;

		String query = "SELECT * FROM employees WHERE employeeNumber = ?"; 
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, employeeNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employee = extractEntity(rs);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(employee);
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<>();

		try (Statement s = connection.createStatement()) {
			ResultSet rs = s.executeQuery("SELECT * FROM employees");
			while (rs.next()) {
				employees.add(extractEntity(rs));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public void create(Employee employee) {
		String query = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)){
			ps.setInt(1, employee.getEmployeeNumber());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getExtension());
			ps.setString(5, employee.getEmail());
			ps.setString(6, employee.getOfficeCode());
			ps.setInt(7, employee.getReportsTo());
			ps.setString(8, employee.getJobTitle());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateByEmployeeNumber(int employeeNumber, Employee employee) {
		String query = "UPDATE employees"
				+ "SET employeeNumber = ?, " 
					+ "lastName = ?, "
					+ "firstName = ?, " 
					+ "extension = ?, " 
					+ "email = ?, " 
					+ "officeCode = ?, "
					+ "reportsTo = ?, "
					+ "jobTitle = ?"
				+ "WHERE employeeNumber = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, employee.getLastName());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getExtension());
			ps.setString(4, employee.getEmail());
			ps.setString(5, employee.getOfficeCode());
			ps.setInt(6, employee.getReportsTo());
			ps.setString(7, employee.getJobTitle());
			ps.setInt(8, employeeNumber);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByEmployeeNumber(int employeeNumber) {
		String query = "DELETE FROM employees WHERE employeeNumber = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, employeeNumber);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Employee extractEntity(ResultSet rs) throws SQLException {
		int employeeNumber = rs.getInt("employeeNumber");
		String lastName = rs.getString("lastName");
		String firstName = rs.getString("firstName");
		String extension = rs.getString("extension");
		String email = rs.getString("email");
		String officeCode = rs.getString("officeCode");
		int reportsTo = rs.getInt("reportsTo");
		String jobTitle = rs.getString("jobTitle");
		return new Employee.Builder(employeeNumber).lastName(lastName).firstName(firstName).extension(extension)
				.email(email).officeCode(officeCode).reportsTo(reportsTo).jobTitle(jobTitle).build();
	}
}
