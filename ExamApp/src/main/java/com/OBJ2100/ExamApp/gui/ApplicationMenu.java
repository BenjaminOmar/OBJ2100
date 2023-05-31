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

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class ApplicationMenu extends JMenuBar implements ActionListener {
	
	private JMenu menuFile = new JMenu("File");
	private JMenuItem selectfolderItem = new JMenuItem("Select folder");
	private JMenuItem writecustomerItem = new JMenuItem("Write customers into file");
	private JMenuItem bulkimportItem = new JMenuItem("Bulk import of orders");
	private JMenuItem exitItem = new JMenuItem("Exit application");
	private JMenu menuDatabase = new JMenu("Database");
	private JMenuItem executeItem = new JMenuItem("Execute SQL Query");
	private JMenuItem employeeItem = new JMenuItem("Add or modify employee");
	private JMenuItem listallItem = new JMenuItem("List all products");
	private JMenuItem officeItem = new JMenuItem("Filter and present offices from a country");
	private JMenuItem dBconnectionItem = new JMenuItem("Test database connection");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem optionTip = new JMenuItem("About");
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

		exitItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		
		 dBconnectionItem.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            try {
		                displayMessage("Connection tested successfully!");
		            } catch (Exception ex) {
		                displayMessage("Error with the connection!");
		            }
		        }
		    });
		 
		 optionTip.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JTextArea helptext = new JTextArea("This is our application for the exam!\n\n- It provides the display of basic functionality\n- :)\n-¯\\_(ツ)_/¯");
			        helptext.setEditable(false);
			        helptext.setOpaque(false);
			        helptext.setFont(smallFont);
			        JOptionPane.showMessageDialog(ApplicationMenu.this, helptext, "About the application", JOptionPane.INFORMATION_MESSAGE);
			    }
			});
		 
		 darkMode.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        darkModeOption = !darkModeOption;
			        updateTheme();
			    }
			});

		menuFile.add(selectfolderItem);
		menuFile.add(writecustomerItem);
		menuFile.add(bulkimportItem);
		menuFile.add(exitItem);
		
		menuDatabase.add(dBconnectionItem);
		menuDatabase.add(executeItem);
		menuDatabase.add(employeeItem);
		menuDatabase.add(listallItem);
		menuDatabase.add(officeItem);
		
		
		menuOptions.add(darkMode);
		menuHelp.add(optionTip);

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
	    String arg = event.getActionCommand();
	    if (arg.equals("Exit application")) {
	        System.exit(0);		
	    }
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








