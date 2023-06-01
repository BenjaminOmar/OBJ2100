package com.OBJ2100.ExamApp.gui;

import java.awt.Dimension;

import javax.swing.Box;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.OBJ2100.ExamApp.gui.dialogs.ExecSqlQueryDialog;
import com.OBJ2100.ExamApp.gui.listeners.AboutAppListener;
import com.OBJ2100.ExamApp.gui.listeners.ExitListener;
import com.OBJ2100.ExamApp.gui.listeners.TestDbConnectionListener;

/**
 * This class holds the side menu buttons and methods
 * for different operations of the program.
 * @author 7132
 */
public class SideMenu extends JPanel{
	
	private JButton testDbCon = new JButton("Test database connection");
	private JButton ExecSqlQuery = new JButton("Execute SQL query");
	private JButton about = new JButton("About the app");
	private JButton Exit = new JButton("Exit application");
	
	/**
	 * This menu is instantiated in the MainWindow class.
	 */
	protected SideMenu() {		
		displaySideMenu();
	}
		
	/**
	 * This constructor adds the different buttons, 
	 * and creates uses ActionListeners and Dialog boxes
	 * to do the different operations
	 */
	private void displaySideMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		add(Box.createHorizontalStrut(15));
		
		 // Setting maximum width size for the buttons
	    Dimension buttonSize = new Dimension(Integer.MAX_VALUE, testDbCon.getPreferredSize().height);
	    testDbCon.setMaximumSize(buttonSize);
	    ExecSqlQuery.setMaximumSize(buttonSize);
	    about.setMaximumSize(buttonSize);
	    Exit.setMaximumSize(buttonSize);
		
		add(testDbCon);
		testDbCon.addActionListener(new TestDbConnectionListener());
		add(Box.createVerticalStrut(15));
		
		add(ExecSqlQuery);
		ExecSqlQuery.addActionListener(l -> new ExecSqlQueryDialog());
		add(Box.createVerticalStrut(15));

		add(about);
		about.addActionListener(new AboutAppListener());
		add(Box.createVerticalStrut(220));
		
		add(Exit);
		Exit.addActionListener(new ExitListener());
		add(Box.createVerticalStrut(10));
	}
}
