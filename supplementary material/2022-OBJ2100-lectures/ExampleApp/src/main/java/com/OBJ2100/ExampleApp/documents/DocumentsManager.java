package com.OBJ2100.ExampleApp.documents;

import java.io.File;
import java.io.IOException;

/**
 * implementation of functionalities required to access the file system
 */
public interface DocumentsManager {
    
	/**
     *  writes string to file
     */
    void writeToFile(String text, File file) throws IOException ;

    /**
     * reads content of the file located on the provided path
     */
    String readFromFile(File file) throws IOException ;
}
