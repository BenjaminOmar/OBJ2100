package com.OBJ2100.ExampleApp.gui;
/*
 * Main application window
 * 
 * */


import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private ApplicationMenu appMenu = new ApplicationMenu();
	private MainContent content = new MainContent();
	


	public MainWindow() {
		
		setTitle("Example application");
		setSize(1030, 1000);
		setLocationRelativeTo(null);
		setLayout(new GridLayout());
		
		// add main menu
		setJMenuBar(appMenu);

		// add content
		add(content);
		
		setResizable(false);
		setVisible(true);
	}


}
