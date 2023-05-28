package com.OBJ2100.ExamApp.gui;
/*
 * Creates main menu of the application
 * */

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFileChooser;
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
	
	private JMenu menu_file = null;
	private JMenuItem dBconnectionItem = null;
	private JMenuItem exitItem = null;
	private JMenu menu_help = null;
	private JMenuItem option_tip = null;
	private JMenu menu_options = null;
	private static JMenuItem darkMode = null;
	private static boolean darkModeOption = false;
	
	private Font bigFont = new Font("Calibri", Font.PLAIN, 28);
	private Font smallFont = new Font("Calibri", Font.PLAIN, 24);
	
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

		menu_file = new JMenu("File");
		
		dBconnectionItem = new JMenuItem("Test database connection");
		dBconnectionItem.addActionListener(this);
		
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		
		menu_file.add(dBconnectionItem);
		menu_file.add(exitItem);

		menu_help = new JMenu("Help");
		
		option_tip = new JMenuItem("About the application");
		option_tip.addActionListener(this);
		menu_help.add(option_tip);
		
		menu_options = new JMenu("Options");
		darkMode = new JMenuItem("Dark mode");
        darkMode.addActionListener(this);
		menu_options.add(darkMode);

		this.add(menu_file);
		this.add(menu_help);
		this.add(menu_options);
	}
	
	/**
	 * Actions that are performed upon the clicks on the main menu by the user.
	 * Added the action of switching between light/dark mode on click event.
	 * @author 7132
	 */
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
		} else if (arg.equals("Dark mode")) {
            darkModeOption = !darkModeOption;
            updateTheme();
		} else if (arg.equals("Light mode")) {
            darkModeOption = !darkModeOption;
            updateTheme();
		}
	}

	// simple method that display option pane with the provided message
	private void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", bigFont);
		UIManager.put("OptionPane.buttonFont", bigFont);
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








