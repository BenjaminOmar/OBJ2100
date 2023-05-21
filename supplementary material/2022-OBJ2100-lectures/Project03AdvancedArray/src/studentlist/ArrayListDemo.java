package studentlist;

import java.util.ArrayList;

public class ArrayListDemo {
	
	//creating arraylist  
	private static ArrayList<Student> studentList = new ArrayList<Student>(); 

	public static void main(String[] args) {
		//Creating user-defined class objects  
		Student s1=new Student(101,"Sonoo",23);  
		Student s3=new Student(103,"Hanumat",25);  
		
		 
		studentList.add(s1);//adding Student class object  
		studentList.add(new Student(102,"Ravi",21));  
		studentList.add(s3); 

		printStudents();


	}

    public static void printStudents(){
    	for(Student s : studentList)
            System.out.println(s.getNumber() + ", " + s.getName() + ", " + s.getAge() + " year(s) old");
    }
	
}
