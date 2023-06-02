package com.OBJ2100.ExamApp.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.StringJoiner;

import javax.sql.DataSource;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.documents.DocumentsManager;
import com.OBJ2100.ExamApp.documents.FolderManager;
import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;

/**
 * This class creates a dialog box where a user can input SQL queries.
 * If the user does a SELECT statement, a csv file with the result will be 
 * created and stored in the application-chosen folder. If the SQL statement
 * does any other operation, it will be executed and the user will get a
 * response from a message popup.
 * @author 7132
 */
public class ExecSqlQueryDialog extends JDialog {
	private JLabel headerLabel = new JLabel("Write your SQL statement:");
	private JPanel panel = new JPanel(new BorderLayout());
	private JTextArea textArea = new JTextArea();
	private JDialog dialog = new JDialog();
	private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JButton button = new JButton("Execute");
	
    /**
     * This constructor creates the dialog window and calls for the
     * executeSQLQuery method on button click.
     */
	public ExecSqlQueryDialog() {
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
	 * @param query The inputted query string from the user
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
	    StringJoiner lineJoiner = new StringJoiner(System.getProperty("line.separator"));
	    while (rs.next()) {
	        StringJoiner columnJoiner = new StringJoiner(";");
	        for (int i = 1; i < metadata.getColumnCount() + 1; i++) {
	            Object object = rs.getObject(i);
	            if (object != null && object.toString().contains(",")) {
	                object = object.toString().replace(',', '.');
	            }
	            if (object == null) {
	                columnJoiner.add("null");
	            } else {
	                columnJoiner.add(object.toString());
	            }
	        }
	        lineJoiner.add(columnJoiner.toString());
	    }
	    return lineJoiner.toString();
	}
	
	/**
	 * Creates a new document with a generated filename.
	 * @param text Takes in the formatted Csv String.
	 */
	private void exportResultsToCsvFile(String text) {
		try {
			File csvFile = new File(FolderManager.getFolderPath(), generateFilename());		
			new DocumentsManager().writeToFile(text, csvFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method generates a filename.
	 * @return Generated filename with a timestamp at the end.
	 */
	private static String generateFilename() {
		String timestamp = LocalDateTime.now()
				.format(DocumentsManager.DEFAULT_TIMESTAMP_FORMATTER);
		return String.format("query-%s.csv", timestamp);
	}

}
