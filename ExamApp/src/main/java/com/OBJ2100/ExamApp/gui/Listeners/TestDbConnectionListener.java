package com.OBJ2100.ExamApp.gui.Listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;

public class TestDbConnectionListener implements ActionListener{
		
	@Override
	public void actionPerformed(ActionEvent e) {
  		DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			MessageHelper.displayMessage("Succesfull connection to database!");
		} catch (SQLException err) {
			err.printStackTrace();
			MessageHelper.displayMessage("Error connecting to database");
		}	
	}
}
