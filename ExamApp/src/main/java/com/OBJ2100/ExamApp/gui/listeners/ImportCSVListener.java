package com.OBJ2100.ExamApp.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JFileChooser;

public class ImportCSVListener implements ActionListener {


    public void importCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
			Path filepath = fileChooser.getSelectedFile().toPath();
			String fileExtension = filepath.toString().toLowerCase();
			
            if (fileExtension.endsWith(".csv")) {
                try {
                    List<String> lines = Files.readAllLines(filepath);
                    
                    for (int i = 1; i < lines.size(); i++) {
                        String line = lines.get(i);
                        String[] values = line.split(",");

                        int orderNumber = Integer.parseInt(values[0]);
                        String orderDate = values[1];
                        String requiredDate = values[2];
                        String shippedDate = values[3];
                        String status = values[4];
                        String comments = values[5];
                        int customerNumber = Integer.parseInt(values[6]);
                    }
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e){
        try{
            importCSV();
        }finally{
            
        }
			
    }
    
}
