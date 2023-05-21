package io;

import java.nio.file.*;
import java.io.*;

public class ReadFile {

	public static void main(String[] args) {

		Path file = Paths.get("/Users/bobanvesin/temp/Data.txt");
		InputStream input = null;
		try {
			input = Files.newInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String s = null;
			s = reader.readLine();
			System.out.println(s);
			input.close();
		} catch (Exception e) {
			System.out.println("IO exception");
		}
	}
}
