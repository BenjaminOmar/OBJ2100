package com.OBJ2100.ExamApp.gui;

import javax.swing.Box;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.OBJ2100.ExamApp.gui.listeners.AboutAppListener;
import com.OBJ2100.ExamApp.gui.listeners.ExecSqlQueryListener;
import com.OBJ2100.ExamApp.gui.listeners.ExitListener;
import com.OBJ2100.ExamApp.gui.listeners.TestDbConnectionListener; 

/**
 * This class holds the side menu for different operations for
 * the program.
 * @author 7132
 *
 */
public class SideMenu extends JPanel{
	
	private JButton testDbCon = new JButton("Test database connection");
	private JButton ExecSqlQuery = new JButton("Execute SQL query");
	private JButton about = new JButton("About the app");
	private JButton Exit = new JButton("Exit application");
	
	/**
	 * This menu is instanciated in the MainWindow class.
	 */
	protected SideMenu() {		
		displaySideMenu();
	}
		
	/**
	 * This method adds the different buttons, and creates custom actionListeners
	 * for each.
	 */
	private void displaySideMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		add(Box.createHorizontalStrut(15));
		
		add(testDbCon);
		testDbCon.addActionListener(new TestDbConnectionListener());
		add(Box.createVerticalStrut(15));
		
		add(ExecSqlQuery);
		ExecSqlQuery.addActionListener(new ExecSqlQueryListener());
		add(Box.createVerticalStrut(15));

		add(about);
		about.addActionListener(new AboutAppListener());
		add(Box.createVerticalStrut(220));
		
		add(Exit);
		Exit.addActionListener(new ExitListener());
		add(Box.createVerticalStrut(10));
	}
}
