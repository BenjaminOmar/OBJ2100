package com.OBJ2100.ExamApp.gui.dialogs;

import java.sql.SQLException;
import java.util.List;

import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.entities.Product;

/**
 * An implementation of the table dialog for products.
 * 
 * @author 7154
 *
 */
public class ListAllProductsTableDialog extends AbstractListAllTableDialog<Product> {

	@Override
	protected List<Product> fetchEntities(DaoFactory daoFactory) throws SQLException {
		return daoFactory.getProductDao().getAll();
	}
	
	@Override
	protected Object[] extractOne(Product p) {
		return new Object[] {
				p.getProductCode(),
				p.getProductName(),
				p.getProductLine(),
				p.getProductScale(),
				p.getProductVendor(),
				p.getProductDescription(),
				p.getQuantityInStock(),
				p.getBuyPrice(),
				p.getMsrp()
		};
	}
}
