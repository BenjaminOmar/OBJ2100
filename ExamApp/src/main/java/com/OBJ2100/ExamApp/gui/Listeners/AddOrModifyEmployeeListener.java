package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import com.OBJ2100.ExamApp.gui.dialogs.AddOrModifyEmployeeDialog;

public class AddOrModifyEmployeeListener implements ActionListener {
	private AddOrModifyEmployeeDialog frame;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (frame != null) frame.dispose();
		frame = new AddOrModifyEmployeeDialog();
	}
}
