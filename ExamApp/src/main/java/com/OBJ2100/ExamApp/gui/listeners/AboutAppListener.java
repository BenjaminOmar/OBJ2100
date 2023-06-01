package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;

/**
 * Created an ActionListener. This will be used in the side menu
 * and the application menu.
 * It displays a message popup with information about the application
 * @author 7132
 */
public class AboutAppListener implements ActionListener{

	/**
	 * This method creates a new JOptionPane with information about the application
	 * with the help of the displayMessage method in the MessageHelper class.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea helptext = new JTextArea("This is our application asnwer for the OBJ2100 Exam\n\n "
				+ "We have decided to follow the functionality for the proposed solution \n\n"
				+ "Some notable features are: \n"
				+ "- Mini Sql workbench(Executy SQL query) \n"
				+ "- List customers by City or State \n"
				+ "- Bulk import orders \n"
				+ "- Dark mode \n"
				+ "And a multitute of other features!");
		helptext.setEditable(false);
		helptext.setOpaque(false);
		MessageHelper.displayMessage(helptext.getText(), "About the application");
	}

}
