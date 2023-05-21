package com.OBJ2100.ExampleApp.gui;

/**
 * Main application window
 * The class initiate the main window and adds:
 * 	- menu bar (appMenu)
 *  - main window content (content)
 *  @author Boban Vesin
 * */

import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private ApplicationMenu appMenu = new ApplicationMenu();
	private MainContent content = new MainContent();

	/**
	 * Method constructor that initialize main frame
	 */
	public MainWindow() {

		setTitle("Example application");
		setSize(1030, 1000);
		setLocationRelativeTo(null);
		setLayout(new GridLayout());

		// add main menu
		setJMenuBar(appMenu);

		// add content
		add(content);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

}
