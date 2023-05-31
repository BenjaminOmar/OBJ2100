package com.OBJ2100.ExamApp.gui;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.OBJ2100.ExamApp.gui.Listeners.AboutAppListener;
import com.OBJ2100.ExamApp.gui.Listeners.ExitListener;
import com.OBJ2100.ExamApp.gui.Listeners.TestDbConnectionListener;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class ApplicationMenu extends JMenuBar implements ActionListener {
	
	private JMenu menuFile = new JMenu("File");
	private JMenuItem selectfolderItem = new JMenuItem("Select folder");
	private JMenuItem writecustomerItem = new JMenuItem("Write customers into file");
	private JMenuItem bulkimportItem = new JMenuItem("Bulk import of orders");
	private JMenuItem Exit = new JMenuItem("Exit application");
	private JMenu menuDatabase = new JMenu("Database");
	private JMenuItem executeItem = new JMenuItem("Execute SQL Query");
	private JMenuItem employeeItem = new JMenuItem("Add or modify employee");
	private JMenuItem listallItem = new JMenuItem("List all products");
	private JMenuItem officeItem = new JMenuItem("Filter and present offices from a country");
	private JMenuItem testDbCon = new JMenuItem("Test database connection");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem about = new JMenuItem("About");
	private JMenu menuOptions = new JMenu("Options");
	private static JMenuItem darkMode = new JMenuItem("Darkmode");
	private static boolean darkModeOption = false;
	
	private Font bigFont = new Font("Calibri", Font.PLAIN, 28);
	private Font smallFont = new Font("Calibri", Font.PLAIN, 14);
	
	protected ApplicationMenu() {
		displayMenuBar();
	}
	
	/**
	 * Added a options menu to the menu bar. Added the dark mode button to the options menu
	 * @author 7132
	 */
	protected void displayMenuBar() {
		UIManager.put("Menu.font", bigFont);
		UIManager.put("MenuItem.font", smallFont);
		
		
		
		selectfolderItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		
		writecustomerItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		
		bulkimportItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		
		add(Exit);
		Exit.addActionListener(new ExitListener());
		 
		 add(testDbCon);
			testDbCon.addActionListener(new TestDbConnectionListener());
		 
		 executeItem.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    }
			});
		 
		 employeeItem.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    }
			});
		 
		 listallItem.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    }
			});
		 
		 officeItem.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    }
			});
		 
		 add(about);
			about.addActionListener(new AboutAppListener());
			
		 
		 darkMode.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        darkModeOption = !darkModeOption;
			        updateTheme();
			    }
			});

		menuFile.add(selectfolderItem);
		menuFile.add(writecustomerItem);
		menuFile.add(bulkimportItem);
		menuFile.add(Exit);
		
		menuDatabase.add(testDbCon);
		menuDatabase.add(executeItem);
		menuDatabase.add(employeeItem);
		menuDatabase.add(listallItem);
		menuDatabase.add(officeItem);
		
		menuOptions.add(darkMode);
		menuHelp.add(about);

		this.add(menuFile);
		this.add(menuDatabase);
		this.add(menuOptions);
		this.add(menuHelp);
	}
	
	/**
	 * Actions that are performed upon the clicks on the main menu by the user.
	 * Added the action of switching between light/dark mode on click event.
	 * @author 7132
	 */
	public void actionPerformed(ActionEvent event) {
	  
	}


	// simple method that display option pane with the provided message
	private void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", smallFont);
		UIManager.put("OptionPane.buttonFont", smallFont);
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * this method updates the background of the windows to dark/light mode
	 * @author 7132
	 */
    private static void updateTheme() {
        try {
            if (darkModeOption) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                darkMode.setText("Light mode");
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
                darkMode.setText("Dark mode");
            }
            
            for (Window window : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
}








