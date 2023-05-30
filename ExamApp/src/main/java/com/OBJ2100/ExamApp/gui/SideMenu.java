package com.OBJ2100.ExamApp.gui;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.DisplayMode;

import com.OBJ2100.ExamApp.db.DataSourceFactory; 

public class SideMenu extends JPanel implements ActionListener{
	
	private Font chosenFont = new Font("Calibri", Font.PLAIN, 15);
	private JButton testDbCon = new JButton("Test database connection");
	private JButton ExecSqlQuery = new JButton("Execute SQL query");
	private JButton about = new JButton("About the app");
	private JButton Exit = new JButton("Exit application");
	
	protected SideMenu() {		
		displaySideMenu();
	}
	
	  public static void main(String[] args) {
		    JFrame frame = new JFrame("test");
		    frame.getContentPane().add(new SideMenu());
		    frame.pack();
		    frame.setVisible(true);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }
	
	
	protected void displaySideMenu() {
		add(testDbCon);
		testDbCon.addActionListener(new ActionListener() { 
	      	  public void actionPerformed(ActionEvent e) { 
	      		DataSource source = DataSourceFactory.getMySqlDataSource();
	    		try (Connection connection = source.getConnection()) {
	    			displayMessage("Succesfull connection to database!");
	    		} catch (SQLException err) {
	    			err.printStackTrace();
	    			displayMessage("Error connecting to database");
	    		}
	      	  } 
	        });
		
		add(ExecSqlQuery);
		
		add(about);
		about.addActionListener(new ActionListener() { 
	      	  public void actionPerformed(ActionEvent e) { 
	  			JTextArea helptext = new JTextArea("This is our application asnwer for the OBJ2100 Exam\n\n- We have decided to follow the proposed functionality\n");
				helptext.setEditable(false);
				helptext.setOpaque(false);
				helptext.setFont(chosenFont);
				displayMessage(helptext.getText());
	      	  } 
	        });
		
		add(Exit);
		Exit.addActionListener((new ActionListener() { 
	      	  public void actionPerformed(ActionEvent e) { 
	      		System.exit(0);	
	      	  } 
	        }));
	}
	
	
	
	
	
	
	
	
	/*
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
            rs.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }
    */
    
	public static void testDbCon() {
		DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
	
    
	private void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", chosenFont);
		UIManager.put("OptionPane.buttonFont", chosenFont);
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
