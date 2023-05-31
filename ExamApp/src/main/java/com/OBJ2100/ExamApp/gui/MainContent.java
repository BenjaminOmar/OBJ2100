package com.OBJ2100.ExamApp.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Employee;
import com.OBJ2100.ExamApp.documents.IDocumentsManager;


public class MainContent extends JPanel implements IDocumentsManager{
	
	private Font bigFont = new Font("Calibri", Font.PLAIN, 40);
	private Font smallFont = new Font("Calibri", Font.PLAIN, 24);
	final JFileChooser fc = new JFileChooser();
	
	
	private DaoFactory daoFactory;
	
	private final static String newline = "\n";
	
	public MainContent() {
		super(new BorderLayout());
		
		// TODO : use a SERVICE instead
		DataSource source = DataSourceFactory.getMySqlDataSource();
		try {
			daoFactory = new JdbcDaoFactory(source.getConnection());			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		SideMenu sideMenu = new SideMenu();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel sideMenuPanel = new JPanel();
        sideMenuPanel.setLayout(new BoxLayout(sideMenuPanel, BoxLayout.Y_AXIS));
        sideMenuPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sideMenuPanel.add(sideMenu);
        
        add(sideMenuPanel, BorderLayout.WEST);
    }
       
	
	// simple method that display option pane with the provided message
	private void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", bigFont);
		UIManager.put("OptionPane.buttonFont", bigFont);
		JOptionPane.showMessageDialog(this, message);
	}
	
	
	// this method overrides the one from the interface
	// it is not used in this version of the software
	@Override
	public String readFromFile(File file) throws IOException {
		// TODO
		return null;
	}

	
	//method that writes text into the file
	// this version does not give warning about overwriting the file content
	@Override
	public void writeToFile(String text, File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(text);    
	    writer.close();
		
	}

}
