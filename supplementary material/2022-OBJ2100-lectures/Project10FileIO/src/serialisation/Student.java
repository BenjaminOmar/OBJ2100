package serialisation;

import java.io.Serializable;

public class Student implements Serializable {
	int id;
	String name, lastName;

	public Student(int id, String name, String lastName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
	}
}
