package com.OBJ2100.ExamApp.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * A pseudo-factory for DataSource implementations.
 * 
 *  By abstracting the source data is fetched from,
 *  we allow for easily switching out sources as needed
 *  (provided a configuration file exists for that source).
 * 
 * @author 7154 
 *
 */
public class DataSourceFactory {
	private static DataSource source;

	/**
	 * Retrieves a data source configured using a configuration file whose path is given.  
	 * 
	 * @param	configFilepath path of the configuration file 
	 * @return 	a data source configured through the specified file
	 */
	private static DataSource getDataSource(String configFilepath) {
		Properties properties = new Properties();
		
		try {
			FileInputStream stream = new FileInputStream(configFilepath);
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fromProperties(properties);
	}

	/**
	 * Configures and returns a data source from specific properties
	 * 
	 * @param	properties
	 * @return 	the configured data source
	 */
	private static DataSource fromProperties(Properties properties) {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(properties.getProperty("DRIVER"));
		source.setUrl(properties.getProperty("URL"));
		source.setUsername(properties.getProperty("USERNAME"));
		source.setPassword(properties.getProperty("PASSWORD"));
		return source;
	}
	
	/**
	 * Returns a data source configured for MySQL
	 * 
	 * @return the configured MySQL data source
	 */
	public static DataSource getMySqlDataSource() {
		if (source == null) {
			synchronized (DataSourceFactory.class) {
				source = getDataSource("db.mysql.properties");
			}
		}
		return source;
	}
}