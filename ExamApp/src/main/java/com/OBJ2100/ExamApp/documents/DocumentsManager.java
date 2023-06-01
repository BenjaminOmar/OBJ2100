package com.OBJ2100.ExamApp.documents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 * This class handles reading and writing to files
 * @author 7162
 */
public class DocumentsManager implements IDocumentsManager {
	
	public static final DateTimeFormatter DEFAULT_TIMESTAMP_FORMATTER = 
			DateTimeFormatter.ofPattern("d_MMM_uuuu-HH_mm_ss");
	
	  /**
     * Writes text to a file on local disk.
     *
     * @param text The text to be written to the file.
     * @param file The file to create or overwrite.
     * @throws IOException If an error occurs while writing to the file.
	 * @author 7162
     */	
	@Override
	public void writeToFile(String text, File file) throws IOException {
		// Declare a variable to hold the BufferedWriter instance
		BufferedWriter writer = null;

		try {
			// Create a BufferedWriter and open the specified file for writing
			writer = new BufferedWriter(new FileWriter(file));
			// Write the provided text to the file
			writer.write(text);
		} finally {
			// Close the writer to ensure that all data is written and resources are released
			if (writer != null) {
				writer.close();
			}
		}
	}

	

	@Override
	public String readFromFile(File file) throws IOException {
				return null;
	}

}
