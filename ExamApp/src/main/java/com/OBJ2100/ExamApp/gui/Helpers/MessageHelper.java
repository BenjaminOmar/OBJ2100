package com.OBJ2100.ExamApp.gui.Helpers;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MessageHelper {
	
	private static Font chosenFont = new Font("Calibri", Font.PLAIN, 15);
	
	public static void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", chosenFont);
		UIManager.put("OptionPane.buttonFont", chosenFont);
		JOptionPane.showMessageDialog( null, message, "Database connection", JOptionPane.INFORMATION_MESSAGE);
	}
}
