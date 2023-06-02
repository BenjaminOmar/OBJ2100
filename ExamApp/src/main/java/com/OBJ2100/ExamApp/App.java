package com.OBJ2100.ExamApp;

import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatLightLaf;

import com.OBJ2100.ExamApp.gui.MainWindow;

public class App {
	
    public static void main( String[] args ) {
    	FlatLightLaf.setup();   	
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {            	
                new MainWindow();
            }
        });
    }
}
