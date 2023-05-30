package com.OBJ2100.ExamApp.gui.Listeners;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;


public class ExecSqlQueryListener implements ActionListener{
    JPanel panel = new JPanel(new BorderLayout());
    JTextArea textArea = new JTextArea();
    JDialog dialog = new JDialog();
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton button = new JButton("Execute");
	
    @Override
	public void actionPerformed(ActionEvent e) {
		performSqlQuery();	
	}
	
	private void performSqlQuery() {


	    buttonPanel.add(button);

	    panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
	    panel.add(buttonPanel, BorderLayout.SOUTH);

	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setContentPane(panel);
	    dialog.setSize(400, 200);
	    dialog.setLocationRelativeTo(null);
	    dialog.setVisible(true);

	    button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            System.out.println(textArea.getText().trim());
	            dialog.dispose(); // Close the dialog
	        }
	    });
	}

}
