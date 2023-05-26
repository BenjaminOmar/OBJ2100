package com.OBJ2100.ExamApp;

import javax.swing.SwingUtilities;

import com.OBJ2100.ExamApp.gui.MainWindow;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * This is the demo application for the OBJ2100 course!
 *
 */
public class App {
	
    public static void main( String[] args ){
    	FlatLightLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });
    }
    
}
