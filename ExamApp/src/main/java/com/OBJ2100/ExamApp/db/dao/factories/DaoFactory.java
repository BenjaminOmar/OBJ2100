package com.OBJ2100.ExamApp.db.dao.factories;

import com.OBJ2100.ExamApp.db.dao.*;

public interface DaoFactory {
	EmployeeDao getEmployeeDao();
	CustomerDao getCustomerDao();
	OfficeDao getOfficeDao();
	OrderDetailsDao getOrderDetailsDao();
	OrderDao getOrderDao();
	PaymentDao getPaymentDao();
	ProductLineDao getProductLineDao();
	ProductDao getProductDao();
}
