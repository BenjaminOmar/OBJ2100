package com.OBJ2100.ExamApp.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.OBJ2100.ExamApp.gui.Listeners.AboutAppListener;
import com.OBJ2100.ExamApp.gui.Listeners.ExecSqlQueryListener;
import com.OBJ2100.ExamApp.gui.Listeners.ExitListener;
import com.OBJ2100.ExamApp.gui.Listeners.TestDbConnectionListener; 

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
