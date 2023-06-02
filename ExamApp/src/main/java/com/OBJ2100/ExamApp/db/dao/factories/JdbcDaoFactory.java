package com.OBJ2100.ExamApp.db.dao.factories;

import java.sql.Connection;

import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.db.dao.EmployeeDao;
import com.OBJ2100.ExamApp.db.dao.OfficeDao;
import com.OBJ2100.ExamApp.db.dao.OrderDao;
import com.OBJ2100.ExamApp.db.dao.ProductDao;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcCustomerDao;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcEmployeeDao;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcOfficeDao;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcOrderDao;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcProductDao;

/**
 * A JDBC-compatible implementation of the DAO factory.
 * 
 * By implementing the DAO factory, we allow using
 * DAOs through other media than a database.
 * 
 * In this project, only fetching from a database is
 * needed, meaning we do not need more implementations
 * of the DaoFactory than the JdbcDaoFactory.
 * 
 * @author 7154
 *
 */
public class JdbcDaoFactory implements DaoFactory {

	private Connection connection;
	
	private JdbcEmployeeDao employeeDao;
	private JdbcCustomerDao customerDao;
	private JdbcOfficeDao officeDao;
	private JdbcOrderDao orderDao;
	private JdbcProductDao productDao;
	
	public JdbcDaoFactory(Connection connection) {
		this.connection = connection; 
	}
	
	@Override
	public EmployeeDao getEmployeeDao() {
		if (employeeDao == null) {
			employeeDao = new JdbcEmployeeDao(connection);
		}
		return employeeDao;
	}

	@Override
	public CustomerDao getCustomerDao() {
		if (customerDao == null) {
			customerDao = new JdbcCustomerDao(connection);
		}
		return customerDao;
	}

	@Override
	public OfficeDao getOfficeDao() {
		if (officeDao == null) {
			officeDao = new JdbcOfficeDao(connection);
		}
		return officeDao;
	}

	@Override
	public OrderDao getOrderDao() {
		if (orderDao == null) {
			orderDao = new JdbcOrderDao(connection);
		}
		return orderDao;
	}
	
	@Override
	public ProductDao getProductDao() {
		if (productDao == null) {
			productDao = new JdbcProductDao(connection);
		}
		return productDao;
	}
	
}
