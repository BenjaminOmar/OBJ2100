package com.OBJ2100.ExampleApp;

import javax.swing.SwingUtilities;

import com.OBJ2100.ExampleApp.gui.MainWindow;

/**
 * This is the demo application for the OBJ2100 course! It contains all elements
 * necessary for the final exam: DB access, File IO and OO concepts
 * 
 * Documentation can be created by: 1. using the following command in the
 * terminal/command line
 * 
 * javadoc -d C:\javadoc\test com.test
 * 
 * 2. Select Generate Javadoc … from the Project menu in Eclipse.
 * 
 * @author Boban Vesin
 */
public class App {

	/**
	 * main method that presents the starting point of the application it displays
	 * the Main window
	 * 
	 * @param args are not used
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});
	}

}
