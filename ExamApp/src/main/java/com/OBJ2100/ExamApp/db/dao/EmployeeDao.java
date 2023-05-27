package com.OBJ2100.ExamApp.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.OBJ2100.ExamApp.db.entities.Employee;

public class EmployeeDao extends BaseDao<Employee> {
	
	public EmployeeDao(DataSource source) {
		super(source);
	}

	@Override
	public Employee get(Number employeeId) throws SQLException {
		Employee employee = null;
		
		try {
			Connection c = source.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"SELECT * FROM employees WHERE employeeNumber = ?");
			ps.setInt(1, (int) employeeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employee = extractEntity(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}
	
	@Override
	public List<Employee> getAll() throws SQLException {
		List<Employee> employees = new ArrayList<>();
		
		try {
			Connection c = source.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM employees");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employees.add(extractEntity(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}
	
	@Override
	public void add(Employee employee) throws SQLException {
		try {
			Connection c = source.getConnection();
			
			PreparedStatement ps = c.prepareStatement(
					"SELECT MAX(employeeNumber) AS lastEmployeeNumber FROM employees;");
			ResultSet rs = ps.executeQuery();
			
			int lastEmployeeNumber = 0;
			if (rs.next()) {
				lastEmployeeNumber = rs.getInt("lastEmployeeNumber");
			}
			
			ps = c.prepareStatement(
					"INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle)"
					+ "VALUES (?, ?, ?, ?; ?; ?, ?, ?)");
			ps.setInt(1, lastEmployeeNumber);
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getExtension());
			ps.setString(5, employee.getEmail());
			ps.setString(6, employee.getOfficeCode());
			ps.setInt(7, employee.getReportsTo());
			ps.setString(8, employee.getJobTitle());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Employee employee, Object[] params) throws SQLException {
		try {
			Connection c = source.getConnection();
			
			employee = new Employee.Builder(employee.getEmployeeNumber())
					.lastName((String) params[0])
					.firstName((String) params[1])
					.extension((String) params[2])
					.email((String) params[3])
					.officeCode((String) params[4])
					.reportsTo((int) params[5])
					.jobTitle((String) params[6])
					.build();
			
			PreparedStatement ps = c.prepareStatement(
					"UPDATE employees SET"
					+ "employeeNumber = ?, "
					+ "lastName = ?, "
					+ "firstName = ?, "
					+ "extension = ?, "
					+ "email = ?, "
					+ "officeCode = ?, "
					+ "reportsTo = ?, "
					+ "jobTitle = ?;");
			ps.setInt(1, employee.getEmployeeNumber());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getExtension());
			ps.setString(5, employee.getEmail());
			ps.setString(6, employee.getOfficeCode());
			ps.setInt(7, employee.getReportsTo());
			ps.setString(8, employee.getJobTitle());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Employee employee) throws SQLException {
		try {
			Connection c = source.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"DELETE FROM employees WHERE employeeNumber = ?");
			ps.setInt(1, employee.getEmployeeNumber());
			ps.executeUpdate();
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
		return new Employee.Builder(employeeNumber)
				.lastName(lastName)
				.firstName(firstName)
				.extension(extension)
				.email(email)
				.officeCode(officeCode)
				.reportsTo(reportsTo)
				.jobTitle(jobTitle)
				.build();
	}
}
