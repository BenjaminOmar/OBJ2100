package com.OBJ2100.ExamApp.gui;

import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.io.File;

import com.OBJ2100.ExamApp.documents.DocumentsManager;
import com.OBJ2100.ExamApp.gui.listeners.ChangeFolderListener;

/**
 * The FileAccessSettingsPanel class extends JPanel and represents a 
 * panel that displays file access settings.
 * 
 * @author 7162
 */

public class FileAccessSettingsPanel extends JPanel {

	private final JButton changeFolderBtn; // Button to change the folder
	private final JLabel folderPathLabel; // Label to display the selected folder path

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

		folderPathLabel = new JLabel("Current folder: " + DocumentsManager.getFolderPath());
		add(folderPathLabel);
	}

	/**
	 * Returns the label component for the folder path.
	 *
	 * @return the folder path label
	 */

	public JLabel getFolderPathLabel() {
		return folderPathLabel;
	}

	/**
	 * Checks if the text in folderPath is longer than the specified number of
	 * characters. If the text is too long, it is truncated and replaced with dots
	 * ("...") at the end to indicate that there is more text that is not displayed.
	 */
	public void checkTextLength() {
		String text = folderPathLabel.getText();
		int maxLength = 50; // Maximum number of characters allowed
		int textLength = text.length();

		if (textLength > maxLength) {
			String truncatedText = text.substring(0, maxLength - 3) + "...";
			folderPathLabel.setText(truncatedText);
		}
	}

	/**
	 * Returns the default folder path based on the user's home directory.
	 *
	 * @return the default folder path
	 */


}