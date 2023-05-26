package Methods; 

import java.nio.ByteBuffer;
import java.nio.file.*;
import java.io.*; 
import java.util.Scanner;
import static java.nio.file.AccessMode.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.attribute.*;
import static java.nio.file.StandardOpenOption.*;
import java.io.BufferedWriter;
import java.io.BufferedInputStream;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.nio.ByteBuffer; 








public class FileHandeling {

	public static void main(String[] args) {
		
		//pathInformation();
		//fromRelativeToAbsoulutePath();
		//checkFileAccessibility();
		//deletePath();
		//fileAttributes();
		//writeToFile(); 
		//readFromFile(); 
		//writeToEmployeeFile();
		//readEmployeeFile(); 
		//randomAccessTest(); 
		createEmptyEmployeesFile(); 
	}
	
	public static void pathInformation() {
		
		FileSystem fs = FileSystems.getDefault();		
		String fileSystemName = fs.toString();
		System.out.println("Standard filsystem: " + fileSystemName);
		System.out.println("------------------------------------");
		
		//Retrieving information about a path
		Path path = fs.getPath("C:\\Temp\\Test.txt");
		
		int count = path.getNameCount();
		System.out.println("Path is " + path.toString());
		System.out.println("Filename is " + path.getFileName());
		System.out.println("There are " + count + " elements in the filepath");
		
		for ( int i = 0; i < count; ++i) {
			System.out.println("Element " + i + " is " + path.getName(i));
		}		
		
		System.out.println("------------------------------------");
	}
	
	public static void fromRelativeToAbsoulutePath() {
		
		String name; 
		Scanner keyboard = new Scanner(System.in); 
		System.out.print("Enter a file name: ");
		name = keyboard.nextLine();
		keyboard.close();
		Path inputPath = Paths.get(name);
		Path fullPath = inputPath.toAbsolutePath();
		System.out.println("Full path is " + fullPath.toString()); 
		System.out.println("------------------------------------");
		
	}
	
	public static void checkFileAccessibility() {
		
		Path filePath = Paths.get("C:\\Temp\\Test.txt");
		System.out.println("Path is " + filePath.toString());
		try {
			filePath.getFileSystem().provider().checkAccess(filePath, java.nio.file.AccessMode.READ, EXECUTE); 
			System.out.println("File can be read and executed");
		}
		catch(IOException e)
		{
			System.out.println("File can not be used for this application."); 
		}	
		System.out.println("------------------------------------");
	}
	
	public static void deletePath() {
		
		Path filePath = Paths.get("C:\\Temp\\Test.txt");
		
		try {
			Files.delete(filePath);
			System.out.println("File or directory is deleted");
		} catch(NoSuchFileException e){
			System.out.println("No such file or directoroy");
		} catch(DirectoryNotEmptyException e) {
			System.out.println();
		} catch(SecurityException e) {
			System.out.println("No persmission to delete");
		} catch(IOException e) {
			System.out.println("IO exception: " + e); 
		}
		System.out.println("------------------------------------");
	}
	
	public static void fileAttributes() {
		
		Path filePath = Paths.get("C:\\Temp\\Test2.txt");
		
		try {
			BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
			System.out.println("Creation time " + attr.creationTime());
			System.out.println("Last modified time " + attr.lastModifiedTime());
			System.out.println("Size " + attr.size());			
		}catch(IOException e) {
			System.out.println("IO Exception: " + e);
		}	
		System.out.println("------------------------------------");
	}
	
	public static void writeToFile() {
		
		Path file = Paths.get("C:\\Temp\\Test.txt");
		String s = "Dette er en test";
		byte[] data = s.getBytes();
		OutputStream output = null; 
		
		try {
			// A BufferedOutputStrem object is assigned to the OutoutStream reference
			output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
			output.write(data);
			output.flush();
			output.close();
			
			System.out.println("\"" + s + "\""  + ": have beed written to file: " + file);
		}catch (Exception e) {
			System.out.println("Message:" + e );
		}	
		System.out.println("------------------------------------");
	}
	
	
	public static void readFromFile() {
		
		Path file = Paths.get("C:\\Temp\\Test.txt");
		InputStream input = null;
		
		try {
			input = Files.newInputStream(file); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
			String s = null;
			s = reader.readLine();
			System.out.println("\"" + s + "\""+ " Har blitt skrevet til filen: " + file);
			input.close();
		}catch(IOException e) {
			System.out.println(e);
		}		
		System.out.println("------------------------------------");
	}
	
	public static void writeToEmployeeFile() {
		
		Scanner input = new Scanner(System.in);
		Path file = Paths.get("C:\\Temp\\Test3.txt");
		String s = ""; 
		String delimiter = ","; 
		int id; 
		String name;
		double payRate; 
		final int QUIT = 999; 
		
		try {
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
			//BufferedWriter object is declared
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output)); 
			System.out.println("Enter employee ID number: ");
			id = input.nextInt(); 
			
			while(id!=QUIT) {
				System.out.println("Enter name for employee #" + id +" : ");
				input.nextLine();
				name = input.nextLine(); 
				System.out.println("Enter pay rate: ");
				payRate = input.nextDouble();
				s = id + delimiter + name + delimiter + payRate;
				//BufferedWriter object uses write() method. 
				writer.write(s, 0, s.length());
				writer.newLine();
				
				// her burde det vært en tilbakemelding til brukeren om at data er lagret til fil... 
				
				System.out.println("Enter next ID number or " + QUIT + " to quit: ");
				id = input.nextInt(); 				
			}
			input.close();
			writer.close();
		}catch(Exception e) {
			System.out.println("Message: " + e);
		}
		System.out.println("------------------------------------");
			
	}
	
	public static void readEmployeeFile() {
		
		// OBS denne overskrider tidligere lagret data, dersom det finnes noe i filen fra før av. 
		Path file = Paths.get("C:\\Temp\\Test3.txt");
		String s = ""; 
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file)); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
			s = reader.readLine();
			
			while(s != null)
			{
				System.out.println(s);
				s = reader.readLine(); 
			}
			reader.close(); 
		}catch(Exception e) {
			System.out.println("Message: " + e);
		}
	}
	
	public static void randomAccessTest() {
		
		Path file = Paths.get("C:\\Temp\\Test3.txt");
		String s = "XYZ";
		byte[] data = s.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data); 
		FileChannel fc = null; 
		try {
			fc = (FileChannel) Files.newByteChannel(file, StandardOpenOption.READ, StandardOpenOption.WRITE);
			fc.position(0); 
			while(out.hasRemaining())
				fc.write(out); 
			out.rewind();
			fc.position(22); 
			while(out.hasRemaining())
				fc.write(out); 
			out.rewind();
			fc.position(12); 
			while(out.hasRemaining())
				fc.write(out);
			fc.close();
			System.out.println("Random access operations completed successfully.");
		}catch(Exception e) {
			System.out.println("Error message: " + e);
		}
	}
	
	public static void createEmptyEmployeesFile() {
		
		Path file = Paths.get("C:\\Temp\\Test3.txt");
		//string that represents a default record.
		String s = "000,       ,00.00" + System.getProperty("line.separator"); 
		byte[] data = s.getBytes(); 
		ByteBuffer buffer = ByteBuffer.wrap(data); 
		final int NUMRECS = 1000;
		
		try {
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE)); 
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output)); 
			
			for(int count = 0; count < NUMRECS; ++count)
				writer.write(s, 0, s.length());
			writer.close();
		}catch(Exception e) {
			System.out.println("Error message: " + e);
		}
	}
}






















