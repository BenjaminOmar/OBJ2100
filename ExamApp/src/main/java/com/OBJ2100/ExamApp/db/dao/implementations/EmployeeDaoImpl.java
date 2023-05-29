package com.OBJ2100.ExamApp.db.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.OBJ2100.ExamApp.db.dao.BaseDao;
import com.OBJ2100.ExamApp.db.dao.EmployeeDao;
import com.OBJ2100.ExamApp.db.entities.Employee;

public class EmployeeDaoImpl extends DaoImpl implements EmployeeDao {

	public EmployeeDaoImpl(Connection connection) {
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
		String query = "SELECT MAX(employeeNumber) AS lastEmployeeNumber FROM employees;";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();

			int lastEmployeeNumber = 0;
			if (rs.next()) {
				lastEmployeeNumber = rs.getInt("lastEmployeeNumber");
			}

			ps = c.prepareStatement(
					"INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, lastEmployeeNumber);
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getExtension());
			ps.setString(5, employee.getEmail());
			ps.setString(6, employee.getOfficeCode());
			ps.setInt(7, employee.getReportsTo());
			ps.setString(8, employee.getJobTitle());
			ps.executeUpdate();
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Employee employee, Object[] params) {
		try (Connection c = source.getConnection()) {
			employee = new Employee.Builder(employee.getEmployeeNumber()).lastName((String) params[0])
					.firstName((String) params[1]).extension((String) params[2]).email((String) params[3])
					.officeCode((String) params[4]).reportsTo((int) params[5]).jobTitle((String) params[6]).build();

			PreparedStatement ps = c.prepareStatement("UPDATE employees SET" + "employeeNumber = ?, " + "lastName = ?, "
					+ "firstName = ?, " + "extension = ?, " + "email = ?, " + "officeCode = ?, " + "reportsTo = ?, "
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
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Employee employee) throws SQLException {
		try (Connection c = source.getConnection()){
			PreparedStatement ps = c.prepareStatement("DELETE FROM employees WHERE employeeNumber = ?");
			ps.setInt(1, employee.getEmployeeNumber());
			ps.executeUpdate();
			ps.close();
			c.close();
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
