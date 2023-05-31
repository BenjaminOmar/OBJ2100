package com.OBJ2100.ExamApp.db.dao.factories;

import java.sql.Connection;

import com.OBJ2100.ExamApp.db.dao.*;
import com.OBJ2100.ExamApp.db.dao.jdbc.*;

public class JdbcDaoFactory implements DaoFactory {

	private Connection connection;
	
	private JdbcEmployeeDao employeeDao;
	private JdbcCustomerDao customerDao;
	private JdbcOfficeDao officeDao;
	private JdbcOrderDetailsDao orderDetailsDao;
	private JdbcOrderDao orderDao;
	private JdbcPaymentDao paymentDao;
	private JdbcProductLineDao productLineDao;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailsDao getOrderDetailsDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDao getOrderDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDao getPaymentDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductLineDao getProductLineDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDao getProductDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
