package com.OBJ2100.ExamApp.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.OBJ2100.ExamApp.gui.dialogs.AddOrModifyEmployeeDialog;
import com.OBJ2100.ExamApp.gui.dialogs.ListAllOfficesTableDialog;
import com.OBJ2100.ExamApp.gui.dialogs.ListAllProductsTableDialog;

public class ReportsAndUpdatesPanel extends JPanel {
	
	private JButton addOrModifyEmployee;
	private JButton listAllProducts;
	private JButton listAllOffices;
	private JButton bulkImportOrders;
	
	public ReportsAndUpdatesPanel() {
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createTitledBorder("Reports and updates"));
		
		addOrModifyEmployee = new JButton("Add or modify employee");
		addOrModifyEmployee.addActionListener(l -> new AddOrModifyEmployeeDialog());
		add(addOrModifyEmployee);
		
		listAllProducts = new JButton("List all products");
		listAllProducts.addActionListener(l -> new ListAllProductsTableDialog());
		add(listAllProducts);
		
		listAllOffices = new JButton("List all offices");
		listAllOffices.addActionListener(l -> new ListAllOfficesTableDialog());
		add(listAllOffices);
		
		bulkImportOrders = new JButton("Bulk import of orders");
		bulkImportOrders.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent evt) {
				
			}
		});
		add(bulkImportOrders);
	}
	
	public static void main(String[] args) {
		com.formdev.flatlaf.FlatLightLaf.setup();
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ReportsAndUpdatesPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
