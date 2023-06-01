package com.OBJ2100.ExamApp.gui.Listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;

/**
 * Created an ActionListener. This will be used in the sidemenu
 * and the application menu.
 * The actionPerformed method Overrides the method in the ActionListener Interface.
 * @author 7132
 */
public class TestDbConnectionListener implements ActionListener{
	
	/**
	 * This method checks if there is a connection to the database.
	 * If there is a valid connection, the user gets a Success message
	 * with the help of the displayMessage method. If there is an error,
	 * the user gets notified with an error popup message. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
  		DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			Statement statement = connection.createStatement();
			statement.executeQuery("SELECT * FROM customers");
			
			MessageHelper.displayMessage("Succesfull connection to database!", "Database");
		} catch (SQLException err) {
			err.printStackTrace();
			MessageHelper.displayMessage("Error connecting to database", "Database");
		}	
	}
}