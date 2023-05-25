package com.OBJ2100.ExamApp.db;

/**
Assuming that you have proper MySQL JDBC driver:
How to use this class?
1. Create new object
  DBHelper db = new DBHelper();
2. Open connection
  db.open();
3. Call corresponding method
  db.test();
4. Close connection
  db.close;
  
+ jdbc driver can be found at: http://www.java2s.com/Code/Jar/c/Downloadcommysqljdbc515jar.htm
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper implements DatabaseInterface {

	
	// JDBC driver name and database URL
    private final String DB_URL = "jdbc:mysql://localhost/classicmodels";
    
    //  Database credentials
    private static final String USER = "student";
    private static final String PASS = "student";
    
    
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rSet = null;
    
	    
	    @Override
	    public void open() throws SQLException{
	        try {
	            //Establish connection
	            conn = DriverManager.getConnection(DB_URL, USER,PASS);
	            //Create statement that will be used for executing SQL queries
	            stmt = conn.createStatement();
	        } catch (SQLException ex) {
	            ex.printStackTrace();// More elegant solutions for catching errors exist but they are out of the scope for this course
	        }
	    }
	    
	    @Override
	    public void close() throws SQLException{
	        try {
	            stmt.close();
	            conn.close();
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        }
	    }
	    
	    
	    public List<Employee> getEmployees(String inputtedOfficeCode) throws SQLException{
	    	ArrayList<Employee> employees = new ArrayList<Employee>();
	    	this.open();
	    	try {
		    	prepStmt = conn.prepareStatement("select * from employees where officeCode = ?");
		    	prepStmt.setString(1, inputtedOfficeCode);

		    	rSet = prepStmt.executeQuery();
		    	while (rSet.next()) {
		    		int employeeNumber = rSet.getInt("employeeNumber");
					String lastName = rSet.getString("lastName");
					String firstName = rSet.getString("firstName");
					String extension = rSet.getString("extension");
					String email = rSet.getString("email");
					String officeCode  = rSet.getString("officeCode");
					int reportsTo = rSet.getInt("reportsTo");
					String jobTitle = rSet.getString("jobTitle");
					
					Employee newEmployee = new Employee(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
					employees.add(newEmployee);
				}
		    	return employees;
			} catch (Exception exc) {
				exc.printStackTrace();
			}
	    	return null;
	    }
	   
	    @Override
	    public List<Employee> getEmployees() throws SQLException{
	    	ArrayList<Employee> employees = new ArrayList<Employee>();
	    	this.open();
	    	try {
		    	prepStmt = conn.prepareStatement("select * from employees");
		    	rSet = prepStmt.executeQuery();

		    	while (rSet.next()) {
		    		int employeeNumber = rSet.getInt("employeeNumber");
					String lastName = rSet.getString("lastName");
					String firstName = rSet.getString("firstName");
					String extension = rSet.getString("extension");
					String email = rSet.getString("email");
					String officeCode  = rSet.getString("officeCode");
					int reportsTo = rSet.getInt("reportsTo");
					String jobTitle = rSet.getString("jobTitle");
					
					Employee newEmployee = new Employee(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
					employees.add(newEmployee);
				}
		    	return employees;
			} catch (Exception exc) {
				exc.printStackTrace();
			}
	    	return null;
	    }
	    
	    @Override
	    public void addEmployee(String firtsName, String LastName, String department, String email, double salary) throws SQLException{
	    	try {
				open();
	    		prepStmt = conn.prepareStatement(
						"insert into employees " +
						"(last_name, first_name, department, email, salary) " + 
						"values " + 
						"(?, ?, ?, ?, ?)");
	    		prepStmt.setString(1, firtsName);
	    		prepStmt.setString(2, LastName);
	    		prepStmt.setString(4, department);
	    		prepStmt.setString(3, email);
	    		prepStmt.setDouble(5, salary);
	    		
	    		prepStmt.executeUpdate();
	    		
	    		close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    };
	    
	    @Override
	    public void test() throws SQLException{
	        try {
	            String sql;
	            sql = "SELECT * FROM employees";
	            ResultSet rs = stmt.executeQuery(sql); // DML
	            // stmt.executeUpdate(sql); // DDL
	            
	            //STEP 5: Extract data from result set
	            
	            while(rs.next()){
	                //Display values
	            	System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
	            }
	            //STEP 6: Clean-up environment
	            
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        }
	    }

		@Override
		public void addEmployee(String firtsName, String LastName) throws SQLException {
			// TODO Auto-generated method stub
			
		}
}
