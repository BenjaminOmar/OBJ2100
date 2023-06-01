package com.OBJ2100.ExamApp.db.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OBJ2100.ExamApp.db.dao.ProductDao;
import com.OBJ2100.ExamApp.entities.Product;

public class JdbcProductDao extends JdbcDao<Product> implements ProductDao {
	
	public JdbcProductDao(Connection connection) {
		super(connection);
	}
	
	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();

		try (Statement s = connection.createStatement()) {
			ResultSet rs = s.executeQuery("SELECT * FROM products;");
			while (rs.next()) {
				products.add(extractEntity(rs));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
	
	@Override
	protected Product extractEntity(ResultSet rs) throws SQLException {
		String productCode = rs.getString("productCode");
		String productName = rs.getString("productName");
		String productLine = rs.getString("productLine");
		String productScale = rs.getString("productScale");
		String productVendor = rs.getString("productVendor");
		String productDescription = rs.getString("productDescription");
		Integer quantityInStock = rs.getInt("quantityInStock");
		Double buyPrice = rs.getDouble("buyPrice");
		Double msrp = rs.getDouble("MSRP");
		return new Product.Builder(productCode).productName(productName).productLine(productLine).productScale(productScale)
				.productVendor(productVendor).productDescription(productDescription).quantityInStock(quantityInStock).buyPrice(buyPrice)
				.msrp(msrp).build();
	}
}
