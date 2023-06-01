package com.OBJ2100.ExamApp.db.helpers;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.OBJ2100.ExamApp.db.DataSourceFactory;

public class MySqlCsvImportHelper {
	
	public enum Table {
		ORDERS("orders");
		
		public final String name;
		
		private Table(String name) {
			this.name = name;
		}
	}
	
	private static void importFromCsv(Path filepath, Table table, String[] columns) {
		DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			String query = buildLoadDataQuery(filepath, table.name(), columns);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void importOrdersFromCsv(Path filepath) {
		String[] orderColumns = {
				"orderNumber",
				"orderDate",
				"requiredDate",
				"shippedDate",
				"status",
				"comments",
				"customerNumber"
		};
		importFromCsv(filepath, Table.ORDERS, orderColumns);
	}
	
	/**
	 * 
	 * @see https://stackoverflow.com/a/6605783
	 * @param filepath
	 * @param table
	 * @return
	 */
	private static String buildLoadDataQuery(Path filepath, String table, String[] columns) {
		String joinedColumns = Arrays.asList(columns).stream()
				.collect(Collectors.joining(", "));
		return String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s "
				+ "FIELDS TERMINATED BY ',' "
				+ "ENCLOSED BY '\"' "
				+ "LINES TERMINATED BY '\n' "
				+ "(%s)", filepath.toString(), table, joinedColumns);
	}
}
