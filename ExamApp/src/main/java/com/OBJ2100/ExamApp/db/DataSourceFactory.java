package com.OBJ2100.ExamApp.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceFactory {
	private static final String propertiesFilepath = "db.properties";
	private static final Properties properties = readProperties();
	
	private static Properties readProperties() {
		Properties properties = new Properties();
		
		try {			
			properties.load(new FileInputStream(propertiesFilepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
	
	public static DataSource getDataSource(DatabaseType type) {
		switch (type) {
			case MYSQL:
				return getMySqlDataSource(properties);
			default:
				throw new UnsupportedOperationException(String.format("No implementation for type '%s'.", type));
		}
	}
	
	private static BasicDataSource getMySqlDataSource(Properties properties) {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(properties.getProperty("MYSQL_DB_DRIVER_CLASS"));
		source.setUrl(properties.getProperty("MYSQL_DB_URL"));
		source.setUsername(properties.getProperty("MYSQL_DB_USERNAME"));
		source.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
		return source;
	}
}