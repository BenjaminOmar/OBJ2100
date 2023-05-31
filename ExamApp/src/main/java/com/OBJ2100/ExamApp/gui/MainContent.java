package com.OBJ2100.ExamApp.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Employee;
import com.OBJ2100.ExamApp.documents.IDocumentsManager;


public class MainContent extends JPanel implements IDocumentsManager{
	
	private Font bigFont = new Font("Calibri", Font.PLAIN, 40);
	private Font smallFont = new Font("Calibri", Font.PLAIN, 24);
	final JFileChooser fc = new JFileChooser();
	
	private JLabel nameLabel = new JLabel("First name:");
	private JTextField nameTextField = new JTextField(20);
	private JLabel lastnameLabel = new JLabel("Last name:");
	private JTextField lastnameTextField = new JTextField(20);
	private JButton addEmployeeButton = new JButton("Save employee");
	private JButton exitButton = new JButton("Exit");
	private JButton displayEmployees = new JButton("Display employees");
	private JButton clear = new JButton("Clear results");
	private JButton storeInFile = new JButton("Store results");
	private JTextArea results = new JTextArea();
	private JScrollPane scroll;
	
	private DaoFactory daoFactory;
	
	private final static String newline = "\n";
	
	public MainContent() {
		super();
		setLayout(null);
		
		// TODO : use a SERVICE instead
		DataSource source = DataSourceFactory.getMySqlDataSource();
		try {
			daoFactory = new JdbcDaoFactory(source.getConnection());			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        // Creating JLabel
        
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are coordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        nameLabel.setBounds(10,20,200,50);
        nameLabel.setFont(smallFont);
        add(nameLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        nameTextField.setBounds(200,20,400,50);
        nameTextField.setFont(smallFont);
        add(nameTextField);

        // Same process for password label and text field.
        
        lastnameLabel.setBounds(10,100,200,50);
        lastnameLabel.setFont(smallFont);
        add(lastnameLabel);

        lastnameTextField.setBounds(200,100,400,50);
        lastnameTextField.setFont(smallFont);
        add(lastnameTextField);

        // Creating login button
        addEmployeeButton.setBounds(10, 200, 300, 50);
        addEmployeeButton.setFont(smallFont);
        add(addEmployeeButton);
        
        addEmployeeButton.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		    Employee employee = new Employee.Builder()
  		    			.lastName(getLastName())
  		    			.firstName(getFirstName())
  		    			.build();
  		    	daoFactory.getEmployeeDao().create(employee);
      	  }
        });
        
        displayEmployees.setBounds(330, 200, 360, 50);
        displayEmployees.setFont(smallFont);
        add(displayEmployees);
        displayEmployees.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) { 
        		List<Employee> employees = daoFactory.getEmployeeDao().getAll();
                for (Employee employee : employees) {
                    results.append(employee.getFirstName() + ", " + employee.getLastName() + newline);
                }
        	}
        });
        
        clear.setBounds(700, 200, 300, 50);
        clear.setFont(smallFont);
        add(clear);
        clear.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) { 
        		results.setText(null);

        	} 
        });
    	
        results.setFont(smallFont);
        results.setEditable(false);
        
        scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 300, 1000, 400);
        scroll.getViewport().add(results);
        
        add(scroll);
        
        storeInFile.setBounds(470, 800, 300, 50);
        storeInFile.setFont(bigFont);
        add(storeInFile);
        storeInFile.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) { 
        		
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setDialogTitle("Specify a file to save");
                
                //set default folder
                fc.setCurrentDirectory(new File("c:\\temp"));
                
                // adding filter to only accept .txt
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
                fc.setFileFilter(filter);
                
                int returnVal = fc.showSaveDialog(null);
                
        		if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fc.getSelectedFile();

                    try {
						writeToFile(results.getText(), fileToSave);
						displayMessage("Succesfull update!");
					} catch (IOException e1) {
						displayMessage("Error writing into file");
					}

                }
        	} 
        });
        
        exitButton.setBounds(780, 800, 200, 50);
        exitButton.setFont(bigFont);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) { 
        	    System.exit(0);
        	} 
        });
	}
	
	public String getFirstName() {
		return nameTextField.getText();
	}
	
	public String getLastName() {
		return lastnameTextField.getText();
	}
	
	// simple method that display option pane with the provided message
	private void displayMessage(String message) {
		UIManager.put("OptionPane.messageFont", bigFont);
		UIManager.put("OptionPane.buttonFont", bigFont);
		JOptionPane.showMessageDialog(this, message);
	}
	
	
	// this method overrides the one from the interface
	// it is not used in this version of the software
	@Override
	public String readFromFile(File file) throws IOException {
		// TODO
		return null;
	}

	
	//method that writes text into the file
	// this version does not give warning about overwriting the file content
	@Override
	public void writeToFile(String text, File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(text);    
	    writer.close();
		
	}

}
