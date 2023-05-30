package com.OBJ2100.ExamApp.gui;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystem;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout; 


public class FileAccessSettingsPanel extends JPanel  {
	
	private final JButton changeFolderBtn; 
	private final JTextField folderPath; 
	
	
	
	public FileAccessSettingsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		changeFolderBtn = new JButton("Change Folder");	
		changeFolderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
			}		
		});
		add(changeFolderBtn);
		
		folderPath = new JTextField("C://Temp");  		
		folderPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
			}						
		});
		add(folderPath); 	
	}
	
	public static void main(String[] args) { 
		
		JFrame frame = new JFrame("FileAccessPanel");
		frame.getContentPane().add( new FileAccessSettingsPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//JPanel panel = new FileAccessSettingsPanel();
		
		
		//frame.add(panel); 
		//frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//frame.setBounds(100, 100, 100, 100);
		//frame.setLayout(new BorderLayout(100,100));
		
	}
}
	
	
	

	
	
	
	
	
	
	
	
	
	//JButton ChangeFolder = new JButton();  
	
	
	
	//JLabel fileLabel = new JLabel();  
	
	
	
	
	

}
