package com.OBJ2100.ExamApp.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.OBJ2100.ExamApp.gui.dialogs.AddOrModifyEmployeeDialog;
import com.OBJ2100.ExamApp.gui.dialogs.ExecSqlQueryDialog;
import com.OBJ2100.ExamApp.gui.dialogs.ListAllProductsTableDialog;
import com.OBJ2100.ExamApp.gui.listeners.AboutAppListener;
import com.OBJ2100.ExamApp.gui.listeners.ChangeFolderListener;
import com.OBJ2100.ExamApp.gui.listeners.ExitListener;
import com.OBJ2100.ExamApp.gui.listeners.TestDbConnectionListener;
import com.OBJ2100.ExamApp.gui.listeners.WriteToFileListener;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * The ApplicationMenu class contains the menu bar for a GUI application.
 * It contains various menus and menu items with their associated actions.
 * @author 7131
 */

public class ApplicationMenu extends JMenuBar implements ActionListener {
	
	private JMenu menuFile = new JMenu("File");
	private JMenuItem selectfolderItem = new JMenuItem("Select folder");
	private JMenuItem writecustomerItem = new JMenuItem("Write customers into file");
	private JMenuItem bulkImportOrders = new JMenuItem("Bulk import of orders");
	private JMenuItem Exit = new JMenuItem("Exit application");
	private JMenu menuDatabase = new JMenu("Database");
	private JMenuItem ExecSqlQuery = new JMenuItem("Execute SQL Query");
	private JMenuItem addOrModifyEmployee = new JMenuItem("Add or modify employee");
	private JMenuItem listAllProducts = new JMenuItem("List all products");
	private JMenuItem officeItem = new JMenuItem("Filter and present offices from a country");
	private JMenuItem testDbCon = new JMenuItem("Test database connection");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem about = new JMenuItem("About");
	private JMenu menuOptions = new JMenu("Options");
	private static JMenuItem darkMode = new JMenuItem("Darkmode");
	private static boolean darkModeOption = false;
		
	protected ApplicationMenu() {
		displayMenuBar();
	}
	
	
	/**
	 * Displays the menu bar with its menus and menu items.
	 */
	protected void displayMenuBar() {
				
	    add(selectfolderItem);
		FileAccessSettingsPanel folderPanel = new FileAccessSettingsPanel();
		selectfolderItem.addActionListener(new ChangeFolderListener(folderPanel));

		ListCustomersPanel panel = new ListCustomersPanel();  // Create an instance of ListCustomersPanel
	    add(writecustomerItem);
	    writecustomerItem.addActionListener(new WriteToFileListener(panel));

		add(bulkImportOrders);
		bulkImportOrders.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent evt) {
			}
		});

		
		add(Exit);
		Exit.addActionListener(new ExitListener());
		 
		add(testDbCon);
			testDbCon.addActionListener(new TestDbConnectionListener());
		 
		add(ExecSqlQuery);
			ExecSqlQuery.addActionListener(l -> new ExecSqlQueryDialog());
		
		add(addOrModifyEmployee);
		    addOrModifyEmployee.addActionListener(l -> new AddOrModifyEmployeeDialog());
		    
		add(listAllProducts);
		    listAllProducts.addActionListener(l -> new ListAllProductsTableDialog());
			
		 officeItem.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    }
			});
		 
		 add(about);
			about.addActionListener(new AboutAppListener());
			
		/**
		 * Added the action of switching between light/dark mode on click event.
		 * @author 7132
		 */
		 darkMode.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        darkModeOption = !darkModeOption;
			        updateTheme();
			    }
			});

		menuFile.add(selectfolderItem);
		menuFile.add(writecustomerItem);
		menuFile.add(bulkImportOrders);
		menuFile.add(Exit);
		
		menuDatabase.add(testDbCon);
		menuDatabase.add(ExecSqlQuery);
		menuDatabase.add(addOrModifyEmployee);
		menuDatabase.add(listAllProducts);
		menuDatabase.add(officeItem);
		
		menuOptions.add(darkMode);
		menuHelp.add(about);

		this.add(menuFile);
		this.add(menuDatabase);
		this.add(menuOptions);
		this.add(menuHelp);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
}