package com.OBJ2100.ExamApp.documents;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * This class handles and holds the folder path configurations used within
 * the project.
 * @author 7132
 */
public class FolderManager {
    
	private static String folderPath = getDefaultFolderPath();
	
	/**
	 * Returns the folder path.
	 * @return returns "folderPath".
	 * @author 7132
	 */
	public static String getFolderPath() {
		return folderPath;
	}
	
	/**
	 * Sets a new folderPath
	 * @param path The folderPath as a string
	 * @author 7132
	 */
	public static void setFolderPath(String path) {
		folderPath = path;
	}
	
	/**
	 * This method sets the default folder path of the "folderPath"
	 * value.
	 * @return The default folder path as a String
	 * @author 7132
	 */
	private static String getDefaultFolderPath() {
		String userHome = System.getProperty("user.home");
		return userHome + File.separator;
	}
	
    public static String chooseFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int result = fileChooser.showOpenDialog(new JFrame());
        
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getPath();
        } else {
            return null; // Returnerer null hvis ingen mappe er valgt
        }
    }
}
