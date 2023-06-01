package com.OBJ2100.ExamApp.gui;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.OBJ2100.ExamApp.gui.dialogs.AddOrModifyEmployeeDialog;
import com.OBJ2100.ExamApp.gui.dialogs.ExecSqlQueryDialog;
import com.OBJ2100.ExamApp.gui.dialogs.ListAllOfficesTableDialog;
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
 * 
 */

public class ApplicationMenu extends JMenuBar implements ActionListener {
	
	private JMenu menuFile = new JMenu("File");
	private JMenuItem selectFolder = new JMenuItem("Select folder");
	private JMenuItem bulkImportOrders = new JMenuItem("Bulk import of orders");
	private JMenuItem exit = new JMenuItem("Exit application");
	private JMenu menuDatabase = new JMenu("Database");
	private JMenuItem ExecSqlQuery = new JMenuItem("Execute SQL Query");
	private JMenuItem addOrModifyEmployee = new JMenuItem("Add or modify employee");
	private JMenuItem listAllProducts = new JMenuItem("List all products");
	private JMenuItem testDbCon = new JMenuItem("Test database connection");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem about = new JMenuItem("About");
	private JMenu menuOptions = new JMenu("Options");
	private JMenuItem darkMode = new JMenuItem("Darkmode");
	private boolean darkModeOption = false;
	private JMenuItem ListCustomers = new JMenuItem("Write customers into file");
	private JDialog dialog = new JDialog();
	private JPanel panel = new JPanel(new BorderLayout());
	private JPanel listCustomersPanel = new ListCustomersPanel();
	
	private JMenuItem ListOffices = new JMenuItem("List and present all offices");
	private JDialog dialogOffices = new JDialog();
	private JPanel panelOffices = new JPanel(new BorderLayout());
	private JPanel FilterAndPresentOfficesPanel = new FilterAndPresentOfficesPanel();
		
	protected ApplicationMenu() {
		displayMenuBar();
	}
	
	
	/**
	 * Displays the menu bar with its menus and menu items.
	 */
	protected void displayMenuBar() {
				
	    add(selectFolder);
		FileAccessSettingsPanel folderPanel = new FileAccessSettingsPanel();
		selectFolder.addActionListener(new ChangeFolderListener(folderPanel));

		add(bulkImportOrders);

		
		add(exit);
		exit.addActionListener(new ExitListener());
		 
		add(testDbCon);
			testDbCon.addActionListener(new TestDbConnectionListener());
		 
		add(ExecSqlQuery);
			ExecSqlQuery.addActionListener(l -> new ExecSqlQueryDialog());
		
		add(addOrModifyEmployee);
		    addOrModifyEmployee.addActionListener(l -> new AddOrModifyEmployeeDialog());
		    
		add(listAllProducts);
		    listAllProducts.addActionListener(l -> new ListAllProductsTableDialog());
				
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
		
		/**
		 * this actionListener creates a new popup with the ListCustomerPanel
		 * @author 7132
		 */
		ListCustomers.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
				panel.add(listCustomersPanel);
			    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			    dialog.setSize(400, 200);
			    dialog.setContentPane(panel);
			    dialog.setLocationRelativeTo(null);
			    dialog.setVisible(true);
			    dialog.setAlwaysOnTop(false);
		    }
		});
		 
		menuFile.add(selectFolder);
		menuFile.add(ListCustomers);
		menuFile.add(bulkImportOrders);
		menuFile.add(exit);
		
		menuDatabase.add(testDbCon);
		menuDatabase.add(ExecSqlQuery);
		menuDatabase.add(addOrModifyEmployee);
		menuDatabase.add(listAllProducts);
		menuDatabase.add(ListOffices);
		ListOffices.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		    	panelOffices.add(FilterAndPresentOfficesPanel);
				dialogOffices.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogOffices.setSize(400, 200);
				dialogOffices.setContentPane(panel);
				dialogOffices.setLocationRelativeTo(null);
				dialogOffices.setVisible(true);
				dialogOffices.setAlwaysOnTop(false);
		    }
		});
		
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
    private void updateTheme() {
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