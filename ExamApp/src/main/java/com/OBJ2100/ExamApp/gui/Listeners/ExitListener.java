package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created an ActionListener. This will be used in the sidemenu
 * and the application menu.
 * The actionPerformed method Overrides the method in the ActionListener Interface.
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
