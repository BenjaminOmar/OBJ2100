package com.OBJ2100.ExamApp.gui.dialogs;

import java.sql.SQLException;
import java.util.List;

import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.entities.Office;

/**
 * An implementation of the table dialog for offices.
 * 
 * @author 7154
 *
 */
public class ListAllOfficesTableDialog extends AbstractListAllTableDialog<Office> {

	@Override
	protected List<Office> fetchEntities(DaoFactory daoFactory) throws SQLException {
		return daoFactory.getOfficeDao().getAll();
	}
	
	@Override
	protected Object[] extractOne(Office o) {
		return new Object[] {
				o.getOfficeCode(),
				o.getCity(),
				o.getPhone(),
				o.getAddressLine1(),
				o.getAddressLine2(),
				o.getState(),
				o.getCountry(),
				o.getPostalCode(),
				o.getTerritory()
		};
	}
}
