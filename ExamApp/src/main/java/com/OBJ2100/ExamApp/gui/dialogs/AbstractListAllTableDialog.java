package com.OBJ2100.ExamApp.gui.dialogs;

import java.awt.Dimension;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;

/**
 * Represents a dialog with a table listing all values for a specific entity. 
 * 
 * @author 7154
 *
 * @param <T> the specific entity
 */
public abstract class AbstractListAllTableDialog<T> extends JDialog {
	protected String[] columns;
	protected List<T> entities;
	
	protected JTable table;
	
	public AbstractListAllTableDialog() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				DataSource source = DataSourceFactory.getMySqlDataSource();
				try (Connection connection = source.getConnection()) {
					DaoFactory daoFactory = new JdbcDaoFactory(connection);
					
					entities = fetchEntities(daoFactory);
					if (entities.size() > 0) {
						Field[] fields = entities.get(0).getClass().getDeclaredFields();
						columns = Arrays.asList(fields).stream()
								.map(f -> f.getName())
								.toArray(String[]::new);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
			@Override
			protected void done() {
				initComponents();
			}
		}.execute();
	}
	
	private void initComponents() {
		Dimension dimension = new Dimension(840, 600);
		if (entities != null && entities.size() > 0) {
			Object[][] data = toTableData();
			table = new JTable(data, columns);
			table.setFillsViewportHeight(true);
			table.setPreferredScrollableViewportSize(dimension);
			
			add(new JScrollPane(table));
		}
		
		pack();
		
		setMinimumSize(dimension);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Returns entities data as a 2D array of objects.
	 * 
	 * @return the 2D array of objects
	 * @author 7154
	 */
	protected Object[][] toTableData() {
		Object[][] data = entities.stream()
				.map(this::extractOne)
				.toArray(Object[][]::new);
		return data;
	}
	
	/**
	 * Fetches and returns values for a specific entity type.
	 * 
	 * @param daoFactory the DAO factory used to fetch entities
	 * @return the entities fetched
	 * @throws SQLException
	 * @author 7154
	 */
	abstract protected List<T> fetchEntities(DaoFactory daoFactory) throws SQLException;
	
	/**
	 * Extract values from an instance of one specific entity.
	 * 
	 * @param entity the entity to extract values from
	 * @return the values as an object array
	 * @author 7154
	 */
	abstract protected Object[] extractOne(T entity);
}
