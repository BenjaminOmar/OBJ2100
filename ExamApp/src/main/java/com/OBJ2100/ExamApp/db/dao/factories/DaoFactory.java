package com.OBJ2100.ExamApp.db.dao.factories;

import com.OBJ2100.ExamApp.db.dao.CustomerDao;
import com.OBJ2100.ExamApp.db.dao.EmployeeDao;
import com.OBJ2100.ExamApp.db.dao.OfficeDao;
import com.OBJ2100.ExamApp.db.dao.OrderDao;
import com.OBJ2100.ExamApp.db.dao.ProductDao;

/**
 * A factory for relevant DAOs.
 * 
 * By implementing this interface into abstract 
 * classes (such as JdbcDaoFactory), we are 
 * effectively using the Abstract Factory pattern.
 * 
 * @author 7154
 *
 */
public interface DaoFactory {
	EmployeeDao getEmployeeDao();
	CustomerDao getCustomerDao();
	OfficeDao getOfficeDao();
	OrderDao getOrderDao();
	ProductDao getProductDao();
}
