package employee;

import java.io.*;

public class ReadEmployeeFile {

	public static void main(String[] args) {
		String s = "";
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("/Users/bobanvesin/temp/Employees2.txt"));

			s = reader.readLine();
			while (s != null) {
				System.out.println(s);
				s = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Message: " + e);
		}
	}
}
