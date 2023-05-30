package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;

public class AboutAppListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea helptext = new JTextArea("This is our application asnwer for the OBJ2100 Exam\n\n- We have decided to follow the proposed functionality\n");
		helptext.setEditable(false);
		helptext.setOpaque(false);
		MessageHelper.displayMessage(helptext.getText());
	}

}
