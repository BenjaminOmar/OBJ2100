package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This actionListener is used to exit the application from assigned buttons
 * @author 7132
 */
public class ExitListener implements ActionListener {
	
	/**
	 * This method closes the application
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);	
	}
}
