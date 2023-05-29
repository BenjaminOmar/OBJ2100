package com.OBJ2100.ExamApp.db.dao.factories;

import java.sql.Connection;

import com.OBJ2100.ExamApp.db.dao.implementations.*;
import com.OBJ2100.ExamApp.db.dao.*;

public class JdbcDaoFactory implements DaoFactory {

	private Connection connection;
	
	private EmployeeDaoImpl employeeDao;
	private CustomerDaoImpl customerDao;
	private OfficeDaoImpl officeDao;
	private OrderDetailsDaoImpl orderDetailsDao;
	private OrderDaoImpl orderDao;
	private PaymentDaoImpl paymentDao;
	private ProductLineDaoImpl productLineDao;
	private ProductDaoImpl productDao;
	
	public JdbcDaoFactory(Connection connection) {
		this.connection = connection; 
	}
	
	@Override
	public EmployeeDao getEmployeeDao() {
		if (employeeDao == null) {
			employeeDao = new EmployeeDaoImpl(connection);
		}
		return employeeDao;
	}

	@Override
	public CustomerDao getCustomerDao() {
		// TODO Auto-generated method stub
		return null;
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
