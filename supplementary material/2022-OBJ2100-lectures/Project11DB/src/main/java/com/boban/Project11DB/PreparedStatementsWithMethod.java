package com.boban.Project11DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//	not finished

public class PreparedStatementsWithMethod {

	static Connection myConn = null;
	static PreparedStatement myStmt = null;
	static ResultSet myRs = null;
	static String dbUrl = "jdbc:mysql://localhost:3306/demo";
	static String user = "student";
	static String pass = "student";
	static String firstName, lastName, eMail, department;
	static double salary;

	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter first name: ");
		firstName = sc.nextLine();
		System.out.println("Please enter last name: ");
		lastName = sc.nextLine();
		System.out.println("Please enter e-mail: ");
		eMail = sc.nextLine();
		System.out.println("Please enter department: ");
		department = sc.nextLine();
		System.out.println("Please enter salary: ");
		salary = sc.nextDouble();

		insertEmployeeIntoDB(firstName, lastName, eMail, salary, department);

		sc.close();

	}

	private static void insertEmployeeIntoDB(String firstName, String lastName, String eMail, double salary,
			String department) throws SQLException {

		try {

			// 1. Get a connection to database
			myConn = DriverManager.getConnection(dbUrl, user, pass);

			// 2. Prepare statement
			String query = "insert into employees " + "(first_name, last_name, email, department, salary) " + "values "
					+ "(?, ?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(query);

			// 3. Set the parameters
			myStmt.setString(1, firstName);
			myStmt.setString(2, lastName);
			myStmt.setString(3, eMail);
			myStmt.setString(4, department);
			myStmt.setDouble(5, salary);

			// 4. Insert a new employee
			myStmt.execute();

			System.out.println("DB updated succesfully!!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}

		}

	}

}
