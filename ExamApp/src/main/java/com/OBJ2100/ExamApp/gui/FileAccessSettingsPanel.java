package com.OBJ2100.ExamApp.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout; 
import javax.swing.JLabel;

import com.OBJ2100.ExamApp.documents.FolderManager;
public class FileAccessSettingsPanel extends JPanel  {
	
	private final JButton changeFolderBtn; 
	private final JLabel folderPath; 
		
	public FileAccessSettingsPanel() {

		//her må det legges til en metode som viser "..." når filbanen blir lengre enn 
		// bredden på JLabel. Det er det enklest å gjøre der man kaller på komponentet og man har bredden på elementet. 
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setPreferredSize(new Dimension(600, 75));
		setBorder(BorderFactory.createTitledBorder("File Access Settings"));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "File Access Settings"));
	
		int paddingSize = 5;
        setBorder(BorderFactory.createCompoundBorder(
                getBorder(),
                BorderFactory.createEmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)
		));
		changeFolderBtn = new JButton("Change Folder");	
		changeFolderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedFolder = FolderManager.chooseFolder();
				if (selectedFolder != null){
					folderPath.setText("  Current folder: " + selectedFolder);
				}						
			}		
		});
		add(changeFolderBtn);
		
		folderPath = new JLabel("  No folder is selected");	
		add(folderPath); 	
	}

	
	public static void main(String[] args) { 
		
		JFrame frame = new JFrame("FileAccessPanel");
		frame.getContentPane().add( new FileAccessSettingsPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}	