package com.OBJ2100.ExamApp;

import javax.swing.SwingUtilities;

import com.OBJ2100.ExamApp.gui.MainWindow;

/**
 * This is the demo application for the OBJ2100 course!
 *
 */
public class App {
	
    public static void main( String[] args ){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });
    }
    
}
