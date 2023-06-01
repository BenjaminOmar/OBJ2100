package com.OBJ2100.ExamApp.db.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.OBJ2100.ExamApp.db.dao.OrderDao;
import com.OBJ2100.ExamApp.entities.Order;

public class JdbcOrderDao extends JdbcDao<Order> implements OrderDao {

	public JdbcOrderDao(Connection connection) {
		super(connection);
	}

	@Override
	public void createMany(List<Order> orders) {
		String query = "INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			int counter = 0;
			for (Order o : orders) {
				ps.setInt(1, o.getOrderNumber());
				ps.setDate(2, (Date) o.getOrderDate());
				ps.setDate(3, (Date) o.getRequiredDate());
				ps.setDate(4, (Date) o.getShippedDate());
				ps.setString(5, o.getStatus());
				ps.setString(6, o.getComments());
				ps.setInt(7, o.getCustomerNumber());
				ps.addBatch();
				
				if (counter % 100 == 0)
					ps.executeBatch();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Order extractEntity(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
