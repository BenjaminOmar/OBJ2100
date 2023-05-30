package com.OBJ2100.ExamApp.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.OBJ2100.ExamApp.gui.Listeners.AboutAppListener;
import com.OBJ2100.ExamApp.gui.Listeners.ExecSqlQueryListener;
import com.OBJ2100.ExamApp.gui.Listeners.ExitListener;
import com.OBJ2100.ExamApp.gui.Listeners.TestDbConnectionListener; 

public class SideMenu extends JPanel{
	
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
			frame.setLocationRelativeTo(null);
		    frame.pack();
		    frame.setVisible(true);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }
	
	
	private void displaySideMenu() {
		add(testDbCon);
		testDbCon.addActionListener(new TestDbConnectionListener());
		
		add(ExecSqlQuery);
		ExecSqlQuery.addActionListener(new ExecSqlQueryListener());
		
		add(about);
		about.addActionListener(new AboutAppListener());
		
		add(Exit);
		Exit.addActionListener(new ExitListener());
	}
}
