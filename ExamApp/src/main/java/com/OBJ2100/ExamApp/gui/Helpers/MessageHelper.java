package com.OBJ2100.ExamApp.gui.Helpers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This is a helper class for message popups available trough the program
 * created for maximum reusability. The two methods in this class display
 * Messages to the user of the application.
 * @author 7132
 */
public class MessageHelper {
	
	private static Font chosenFont = new Font("Calibri", Font.PLAIN, 15);
	private static JScrollPane scroll;
	private static JTextArea textArea;
	private static JPanel panel;
	private static JOptionPane jOptionPane;
	private static JDialog dialog;
	
	/**
	 * Display a JOptionPane with the information from the parameters
	 * @param message A String message that will be presented in the JOptionPane
	 * @param header A String that will be in the header of the popup 
	 */
	public static void displayMessage(String message, String header) {
		UIManager.put("OptionPane.messageFont", chosenFont);
		UIManager.put("OptionPane.buttonFont", chosenFont);
		jOptionPane = new JOptionPane(message);
		dialog = jOptionPane.createDialog((JFrame)null, header);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	/**
	 * Display a JOptionPane with the error massage from the stackTrace
	 * @param printStackTrace The message stackTrace
	 */
	public static void displayMessageError(Object printStackTrace) {
        textArea = new JTextArea(String.valueOf(printStackTrace));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(null);

        scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(400, 200));

        panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);

        UIManager.put("OptionPane.messageFont", chosenFont);
        UIManager.put("OptionPane.buttonFont", chosenFont);

		jOptionPane = new JOptionPane(scroll);
		dialog = jOptionPane.createDialog((JFrame)null, "error!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
}
