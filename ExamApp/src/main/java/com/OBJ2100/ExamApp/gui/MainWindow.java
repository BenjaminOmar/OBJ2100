package com.OBJ2100.ExamApp.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private ApplicationMenu appMenu = new ApplicationMenu();
	private SideMenu sideMenu = new SideMenu();
	private MainContent content = new MainContent();

	public MainWindow() {
		
		setTitle("Exam application");
		setSize(1030, 450);
		setLocationRelativeTo(null);
		setLayout(new GridLayout());
		
		
		// add main menu
		setJMenuBar(appMenu);

		//add side menu
		add(sideMenu);
		// add content
		add(content);
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
