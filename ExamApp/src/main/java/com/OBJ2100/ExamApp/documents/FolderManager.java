package com.OBJ2100.ExamApp.documents;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FolderManager {
    
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
