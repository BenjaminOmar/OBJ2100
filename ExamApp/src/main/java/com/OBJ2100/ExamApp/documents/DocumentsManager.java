package com.OBJ2100.ExamApp.documents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DocumentsManager implements IDocumentsManager {

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
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	@Override
	public String readFromFile(File file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
