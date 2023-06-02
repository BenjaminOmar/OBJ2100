package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Order;

/**
 * A listener for importing orders from a CSV file.
 * 
 * Relies on Apache's "commons-csv" package for
 * reading and parsing CSV files.
 * 
 * @author 7162, 7154
 *
 */
public class ImportCsvListener implements ActionListener {
	
	@Override
    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(
        		new FileNameExtensionFilter("CSV files", "csv"));
        chooser.setAcceptAllFileFilterUsed(true);
		
		Integer answer = chooser.showOpenDialog(null);
		if (answer.equals(JFileChooser.APPROVE_OPTION)) {
			Path filepath = chooser.getSelectedFile().toPath();
			importOrdersFromCsv(filepath);
		}
    }
	
	private void importOrdersFromCsv(Path filepath) {
		List<Order> orders = new ArrayList<>();
		try (Reader reader = new FileReader(filepath.toString())) {
			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
					.setHeader(new String[] {
							"orderNumber",
							"orderDate",
							"requiredDate",
							"shippedDate",
							"status",
							"comments",
							"customerNumber"
					})
					.setSkipHeaderRecord(true)
					.build();
			for (CSVRecord record : csvFormat.parse(reader)) {
				String asStr = record.get("shippedDate");
				Date shippedDate = null;
				if (!asStr.isEmpty()) shippedDate = Date.valueOf(asStr);
				
				Order order = new Order.Builder(Integer.valueOf(record.get("orderNumber")))
						.orderDate(Date.valueOf(record.get("orderDate")))
						.requiredDate(Date.valueOf(record.get("requiredDate")))
						.shippedDate(shippedDate)
						.status(record.get("status"))
						.comments(record.get("comments"))
						.customerNumber(Integer.valueOf(record.get("customerNumber")))
						.build();
				orders.add(order);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			DaoFactory daoFactory = new JdbcDaoFactory(connection);
			daoFactory.getOrderDao().createMany(orders);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}    
}
