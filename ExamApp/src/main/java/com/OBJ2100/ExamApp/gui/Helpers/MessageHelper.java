package com.OBJ2100.ExamApp.gui.Helpers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MessageHelper {
	
	private static Font chosenFont = new Font("Calibri", Font.PLAIN, 15);
	private static JScrollPane scroll;
	private static JTextArea textArea;
	private static JPanel panel;
	
	
	public static void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", chosenFont);
		UIManager.put("OptionPane.buttonFont", chosenFont);
		JOptionPane.showMessageDialog( null, message, "Database connection", JOptionPane.INFORMATION_MESSAGE);
	}

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

        JOptionPane.showMessageDialog(null, panel, "Error", JOptionPane.PLAIN_MESSAGE);
	}
}
