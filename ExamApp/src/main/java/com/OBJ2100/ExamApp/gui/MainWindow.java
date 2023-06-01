package com.OBJ2100.ExamApp.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

    private ApplicationMenu appMenu = new ApplicationMenu();
    private SideMenu sideMenu = new SideMenu();
    private MainContent content = new MainContent();
    private FileAccessSettingsPanel fileAccessSettings = new FileAccessSettingsPanel();
    private ListCustomersPanel listCustomersPanel = new ListCustomersPanel();

    public MainWindow() {
        setTitle("Exam application");
        setSize(1030, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add main menu
        setJMenuBar(appMenu);

        // Add side menu
        add(sideMenu, BorderLayout.WEST);

        // Add content
        add(content, BorderLayout.CENTER);

        // Create a panel for the file access settings and list customers panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(fileAccessSettings);
        panel.add(listCustomersPanel);

        // Add the panel to the main window
        add(panel, BorderLayout.EAST);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
