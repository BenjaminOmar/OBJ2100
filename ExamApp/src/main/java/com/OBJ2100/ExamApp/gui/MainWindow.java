package com.OBJ2100.ExamApp.gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The MainWindow class represents the main window of the GUI application.
 * It extends JFrame and contains the application menu, side menu, and main content.
 * 
 * @author 7131
 */
public class MainWindow extends JFrame {

    private SideMenu sideMenu = new SideMenu();
    private FileAccessSettingsPanel fileAccessSettings = new FileAccessSettingsPanel();
    private ApplicationMenu appMenu = new ApplicationMenu(fileAccessSettings);
    private JPanel listCustomersPanel = new ListCustomersPanel();
    private JPanel reportsAndUpdatesPanel = new ReportsAndUpdatesPanel();

    public MainWindow() {
        setTitle("Exam application");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add main menu
        setJMenuBar(appMenu);

        // Add side menu
        add(sideMenu, BorderLayout.WEST);

        // Create a panel for the file access settings, list customers panel and
        // reports and updates
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(10));
        panel.add(fileAccessSettings);
        panel.add(Box.createVerticalStrut(10));
        panel.add(listCustomersPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(reportsAndUpdatesPanel);
        panel.add(Box.createVerticalStrut(10));

        // Add the panel to the main window
        add(panel, BorderLayout.EAST);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
