package com.OBJ2100.ExamApp.gui.Listeners;

import java.awt.BorderLayout;
import java.sql.ResultSetMetaData;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.StringJoiner;

import javax.swing.JTextArea;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.documents.DocumentsManager;
import com.OBJ2100.ExamApp.gui.SideMenu;
import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;
import com.mysql.cj.protocol.Resultset;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.sql.DataSource;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Created an ActionListener. This will be used in the sidemenu
 * and the application menu.
 * The actionPerformed method Overrides the method in the ActionListener Interface.
 * @author 7132
 */
public class ExecSqlQueryListener implements ActionListener{
	JLabel headerLabel = new JLabel("Write your SQL statement:");
    JPanel panel = new JPanel(new BorderLayout());
    JTextArea textArea = new JTextArea();
    JDialog dialog = new JDialog();
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton button = new JButton("Execute");
	
    @Override
	public void actionPerformed(ActionEvent e) {
		SQLPopup();	
	}
	
    /**
     * Creates the popup where a user can input their sql query.
     */
	private void SQLPopup() {
		panel.add(headerLabel, BorderLayout.NORTH);
		headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    buttonPanel.add(button);
	    

	    panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
	    panel.add(buttonPanel, BorderLayout.SOUTH);

	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setContentPane(panel);
	    dialog.setSize(400, 200);
	    dialog.setLocationRelativeTo(null);
	    dialog.setVisible(true);

	    button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String query = textArea.getText().trim();
	           
	            executeSQLQuery(query);
	    		
	            dialog.dispose();
	        }
	    });
	}
	/**
	 * This method executes the sql query based on the users input.
	 * If the query is a SELECT statement, the infomation gets extraced and
	 * exported to Csv format.
	 * If the query is any other statement, it gets executed, and the user 
	 * gets a succsess message with how many rows is affected.
	 * @param The inputted query string from the user
	 */
	private void executeSQLQuery(String query) {
  		DataSource source = DataSourceFactory.getMySqlDataSource();
		
  		try (Connection connection = source.getConnection()) {
  			Statement statement = connection.createStatement();
			
			if (query.contains("SELECT")) {
				ResultSet rs = statement.executeQuery(query);	    				
				
				
				String asCsv = extractWithCsvFormat(rs);
				exportResultsToCsvFile(asCsv);
				
				MessageHelper.displayMessage("Sql statement executed successfully! "
						+ "\n\n the result is exported to CSV", "Sql statement");
				
				statement.close();
			} else {
				int totalOfRows =  statement.executeUpdate(query);
				MessageHelper.displayMessage("SQL statement executed successfully! "
						+ "\n\n total rows affected: " + totalOfRows, "Sql statement");
				statement.close();
			}
		} catch (SQLException err) {
			MessageHelper.displayMessageError(err);
			
		}
	}
	
	/**
	 * This method takes in the resultset from a SQL SELECT operation
	 * and transforms it into a String using the Csv format.
	 * @param rs The resultset from a SELECT operation.
	 * @return returns the formatted rs in a Csv format.
	 * @throws SQLException Gets handled where it is used
	 */
	private String extractWithCsvFormat(ResultSet rs) throws SQLException {
		ResultSetMetaData metadata = rs.getMetaData();
		StringJoiner lineJoiner = new StringJoiner(
				System.getProperty("line.separator"));
		while (rs.next()) {
			StringJoiner columnJoiner = new StringJoiner(",");
			for (int i = 1; i < metadata.getColumnCount() + 1; i++) {
				columnJoiner.add(rs.getObject(i).toString());
			}
			lineJoiner.add((String) columnJoiner.toString());
		}
		return lineJoiner.toString();
	}
	
	/**
	 * Creates a new document with a generated filename.
	 * @param text Takes the formatted Cvs String.
	 */
	private void exportResultsToCsvFile(String text) {
		try {
			File csvFile = new File(generateFilename());		
			new DocumentsManager().writeToFile(text, csvFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method generates a filename.
	 * @return Generated filename.
	 */
	private static String generateFilename() {
		return String.format("query-%s", LocalDate.now());
	}

}