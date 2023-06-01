package com.OBJ2100.ExamApp.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.io.File;

import com.OBJ2100.ExamApp.gui.Listeners.ChangeFolderListener;

/**
 * The FileAccessSettingsPanel class extends JPanel and represents a 
 * panel that displays file access settings.
 * 
 * @author 7162
 */

public class FileAccessSettingsPanel extends JPanel {

	private final JButton changeFolderBtn; // Button to change the folder
	private final JLabel folderPath; // Label to display the selected folder path

	/**
	 * Constructs a FileAccessSettingsPanel.
	 * 	 
	 * This constructor initializes the FileAccessSettingsPanel by setting its preferred size, 
	 * creating a titled border, and setting its layout.
	 * It also creates and adds the "Change Folder" button and the folderPath label to the panel.
	 */
	public FileAccessSettingsPanel() {

		setPreferredSize(new Dimension(600, 60));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "File Access Settings"));

		int paddingSize = 5;
		setBorder(BorderFactory.createCompoundBorder(
				getBorder(),
				BorderFactory.createEmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)));
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		changeFolderBtn = new JButton("Change Folder");
		changeFolderBtn.addActionListener(new ChangeFolderListener(this));
		add(changeFolderBtn);

		folderPath = new JLabel("Current folder: " + getDefaultFolderPath());
		add(folderPath);
	}

	/**
	 * Returns the label component for the folder path.
	 *
	 * @return the folder path label
	 */

	public JLabel getFolderPath() {
		return folderPath;
	}

	/**
	 * Checks if the text in folderPath is longer than the specified number of
	 * characters. If the text is too long, it is truncated and replaced with dots
	 * ("...") at the end to indicate that there is more text that is not displayed.
	 */
	public void checkTextLength() {
		String text = folderPath.getText();
		int maxLength = 50; // Maximum number of characters allowed
		int textLength = text.length();

		if (textLength > maxLength) {
			String truncatedText = text.substring(0, maxLength - 3) + "...";
			folderPath.setText(truncatedText);
		}
	}

	/**
	 * Returns the default folder path based on the user's home directory.
	 *
	 * @return the default folder path
	 */
	public String getDefaultFolderPath() {
		String userHome = System.getProperty("user.home");
		String defaultFolderPath = userHome + File.separator + "Temp";
		return defaultFolderPath;
	}

	public static void main(String[] args) {
		com.formdev.flatlaf.FlatLightLaf.setup();
		JFrame frame = new JFrame();
		frame.getContentPane().add(new FileAccessSettingsPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}