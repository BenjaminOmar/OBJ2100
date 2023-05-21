package serialisation;

import java.io.*;

class Depersist {
	public static void main(String args[]) {
		try {
			// Creating stream to read the object
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("f.txt"));
			Student s = (Student) in.readObject();
			// printing the data of the serialized object
			System.out.println(s.id + ", " + s.name + ", " + s.lastName);
			// closing the stream
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
