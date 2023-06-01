package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.OBJ2100.ExamApp.documents.DocumentsManager;
import com.OBJ2100.ExamApp.documents.FolderManager;
import com.OBJ2100.ExamApp.gui.FileAccessSettingsPanel;

/**
 * ActionListener for the "Change Folder" button in the FileAccessSettingsPanel.
 * 
 * @author 7162
 */
public class ChangeFolderListener implements ActionListener {

    private FileAccessSettingsPanel folderPanel; // Reference to the FileAccessSettingsPanel

    /**
     * Constructs a ChangeFolderListener with the specified FileAccessSettingsPanel.
     *
     * @param folderPanel the FileAccessSettingsPanel to associate with the listener
     */
    public ChangeFolderListener(FileAccessSettingsPanel folderPanel) {
        this.folderPanel = folderPanel;
    }

    /**
     * Called when the "Change Folder" button is clicked.
     *
     * @param e the ActionEvent associated with the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedFolder = FolderManager.chooseFolder();
        if (selectedFolder != null) {
            // Update the folder path label with the selected folder
        	DocumentsManager.setFolderPath(selectedFolder);
            folderPanel.getFolderPath().setText("  Current folder: " + selectedFolder); //??
            // Check if the text needs to be truncated
            folderPanel.checkTextLength();
        }

    }

    /**
     * Returns the selected folder.
     *
     * @return the selected folder as a String
     */

    public String getSelectedFolder() {
        return folderPanel.getFolderPath().getText();
    }
}
