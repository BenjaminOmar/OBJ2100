package com.OBJ2100.ExamApp.gui;
/*
 * Creates main menu of the application
 * */

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import com.OBJ2100.ExamApp.db.DatabaseHelper;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

public class ApplicationMenu extends JMenuBar implements ActionListener {
	
	private JMenu menuFile = null;
	private JMenuItem dBconnectionItem = null;
	private JMenuItem exitItem = null;
	private JMenu menuHelp = null;
	private JMenuItem optionTip = null;
	private JMenu menuOptions = null;
	private static JMenuItem darkMode = null;
	private static boolean darkModeOption = false;
	
	private Font bigFont = new Font("Calibri", Font.PLAIN, 28);
	private Font smallFont = new Font("Calibri", Font.PLAIN, 24);
	
	protected ApplicationMenu() {
		displayMenuBar();
	}
	
	/**
     * Display of the menu bar:
     * 		Menu
     *			Test database connection
     * 			Exit
     *`		Help
     *			About the application
     */
	
	protected void displayMenuBar() {
		UIManager.put("Menu.font", bigFont);
		UIManager.put("MenuItem.font", smallFont);

		menuFile = new JMenu("File");
		
		dBconnectionItem = new JMenuItem("Test database connection");
		dBconnectionItem.addActionListener(this);
		
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		
		menuFile.add(dBconnectionItem);
		menuFile.add(exitItem);

		menuHelp = new JMenu("Help");
		
		optionTip = new JMenuItem("About the application");
		optionTip.addActionListener(this);
		menuHelp.add(optionTip);
		
		menuOptions = new JMenu("Options");
		darkMode = new JMenuItem("Dark mode");
        darkMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darkModeOption = !darkModeOption;
                updateTheme();
            }
        });
		menuOptions.add(darkMode);

		this.add(menuFile);
		this.add(menuHelp);
		this.add(menuOptions);
	}
	
	
	// Actions that are performed upon the clicks on the main menu by the user
	public void actionPerformed(ActionEvent event) {
		String arg = event.getActionCommand();
		if (arg.equals("Test database connection")) {
			try {
				DatabaseHelper db = new DatabaseHelper();
				db.open();
				db.test();
				db.close();
				displayMessage("Connection tested succesfully!");
			} catch (Exception e) {
				displayMessage("Error with the connection!");
			}	
		}else if (arg.equals("Exit")) {
			System.exit(0);		
		}else if (arg.equals("About the application")) {
			JTextArea helptext = new JTextArea("This is the small application example\n\n- it provides the display of basic functionality\n- You are allowed to use its structure\n- and upgrade it for a higher grade");
			helptext.setEditable(false);
			helptext.setOpaque(false);
			helptext.setFont(bigFont);
			JOptionPane.showMessageDialog(this, helptext, "About the application", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// simple method that display option pane with the provided message
	private void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", bigFont);
		UIManager.put("OptionPane.buttonFont", bigFont);
		JOptionPane.showMessageDialog(this, message);
	}
	
	/*
	private void setDarkMode() {
		darkMode.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            darkMode = !darkMode;
	            updateTheme(frame);
	        }
	    });
	}
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








