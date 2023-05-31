package com.OBJ2100.ExamApp.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.EmployeeDao;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.db.dao.jdbc.JdbcEmployeeDao;
import com.OBJ2100.ExamApp.entities.Employee;
import com.OBJ2100.ExamApp.gui.listeners.AddOrModifyEmployeeListener;
import com.OBJ2100.ExamApp.gui.utilities.SpringUtilities;

public class AddOrModifyEmployeeDialog extends JDialog {
	
	private static enum EmployeeOperation {
		ADDING,
		UPDATING
	}
	
	private enum EmployeeDetail {
		NUMBER("Employee #"),
		LAST_NAME("Last Name"),
		FIRST_NAME("First Name"),
		EXTENSION("Extension"),
		EMAIL("Email"),
		OFFICE_CODE("Office Code"),
		REPORTS_TO("Reports To"),
		JOB_TITLE("Job Title");
		
		public final String detail;
		
		private EmployeeDetail(String detail) {
			this.detail = detail;
		}
	};
	
	private static final String[] DETAILS = {
		"Employee #",
		"Last Name",
		"First Name",
		"Extension",
		"Email",
		"Office Code",
		"Reports To",
		"Job Title"
	};
	
	private List<Employee> employees;
	private Map<EmployeeDetail, JTextField> fieldByEmployeeDetail = new HashMap<>();
	
	private JComboBox<String> employeeNumberComboBox;
	private JTextField[] fields = new JTextField[DETAILS.length];
	private JCheckBox isNewCheckbox;
	private JButton saveButton;
	
	public AddOrModifyEmployeeDialog() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws SQLException {
				DataSource source = DataSourceFactory.getMySqlDataSource();
				try (Connection connection = source.getConnection()) {
					DaoFactory daoFactory = new JdbcDaoFactory(connection);
					employees = daoFactory.getEmployeeDao().getAll();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			protected void done() {
				if (employees.size() == 0) return;
				initComponents();
				populateFieldsWithSelected();
			}
		}.execute();
	}
	
	private void populateFieldsWithSelected() {
		if (employeeNumberComboBox == null) return;
		
		String employeeNumber = (String) employeeNumberComboBox.getSelectedItem();
		if (employeeNumber.equals("")) return;
		
		Employee employee = employees.stream()
				.filter(emp -> emp.getEmployeeNumber() == Integer.parseInt(employeeNumber))
				.findFirst().orElse(null);
		if (employee == null) return;
		
		fieldByEmployeeDetail.get(EmployeeDetail.LAST_NAME).setText(employee.getLastName());
		fieldByEmployeeDetail.get(EmployeeDetail.FIRST_NAME).setText(employee.getFirstName());
		fieldByEmployeeDetail.get(EmployeeDetail.EXTENSION).setText(employee.getExtension());
		fieldByEmployeeDetail.get(EmployeeDetail.EMAIL).setText(employee.getEmail());
		fieldByEmployeeDetail.get(EmployeeDetail.OFFICE_CODE).setText(employee.getOfficeCode());
		fieldByEmployeeDetail.get(EmployeeDetail.REPORTS_TO).setText(String.valueOf(employee.getReportsTo()));
		fieldByEmployeeDetail.get(EmployeeDetail.JOB_TITLE).setText(employee.getJobTitle());
	}
	
	public void initComponents() {		
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel formPanel = new JPanel(new SpringLayout());
		for (EmployeeDetail detail : EmployeeDetail.values()) {
			JPanel fieldPane = new JPanel();
			
			if (detail.equals(EmployeeDetail.NUMBER)) {
				JPanel whenUpdatingEmployee = new JPanel();
				String[] allEmployeeNumbers = employees.stream()
						.map(e -> String.valueOf(e.getEmployeeNumber()))
						.toArray(String[]::new);
				employeeNumberComboBox = new JComboBox<>(allEmployeeNumbers);
				employeeNumberComboBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						populateFieldsWithSelected();
					}
				});
				whenUpdatingEmployee.add(employeeNumberComboBox);
				
				JPanel whenAddingNewEmployee = new JPanel();
				JTextField employeeNumberField = new JTextField(10);
				whenAddingNewEmployee.add(employeeNumberField);
				
				JPanel employeeNumberPane = new JPanel(new CardLayout());
				employeeNumberPane.add(whenUpdatingEmployee, EmployeeOperation.UPDATING.name());
				employeeNumberPane.add(whenAddingNewEmployee, EmployeeOperation.ADDING.name());
				
				isNewCheckbox = new JCheckBox("New");
				isNewCheckbox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						EmployeeOperation operation = isNewCheckbox.isSelected() ?
								EmployeeOperation.ADDING : EmployeeOperation.UPDATING;
						CardLayout layout = (CardLayout) employeeNumberPane.getLayout();
						layout.show(employeeNumberPane, operation.name());
						
						for (JTextField field : fields) {
							if (field != null)
								field.setText(null);
						}
					}
				});
				
				fieldPane.add(employeeNumberPane);
				fieldPane.add(isNewCheckbox);
				
				fieldByEmployeeDetail.put(detail, employeeNumberField);
			} else {
				JTextField field = new JTextField(20);
				fieldPane.add(field);
				
				fieldByEmployeeDetail.put(detail, field);
			}

			fieldPane.setSize(fieldPane.getPreferredSize());
			
			String formatedName = Arrays.asList(detail.name().split("_")).stream()
					.map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
					.collect(Collectors.joining(" "));
			JLabel label = new JLabel(formatedName, JLabel.TRAILING);
			label.setLabelFor(fieldPane);
			
			formPanel.add(label);
			formPanel.add(fieldPane);
		}
		SpringUtilities.makeCompactGrid(formPanel, DETAILS.length, 2, 6, 6, 6, 6);
		panel.add(formPanel, BorderLayout.CENTER);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				DataSource source = DataSourceFactory.getMySqlDataSource();
				try (Connection connection = source.getConnection()) {
					Map<EmployeeDetail, String> byDetail = fieldByEmployeeDetail.entrySet()
							.stream()
							.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getText()));
					
					Employee savedEmployee = new Employee.Builder()
							.employeeNumber(Integer.valueOf(byDetail.get(EmployeeDetail.NUMBER)))
							.lastName(byDetail.get(EmployeeDetail.LAST_NAME))
							.firstName(byDetail.get(EmployeeDetail.FIRST_NAME))
							.extension(byDetail.get(EmployeeDetail.EXTENSION))
							.email(byDetail.get(EmployeeDetail.EMAIL))
							.officeCode(byDetail.get(EmployeeDetail.OFFICE_CODE))
							.reportsTo(Integer.valueOf(byDetail.get(EmployeeDetail.REPORTS_TO)))
							.jobTitle(byDetail.get(EmployeeDetail.JOB_TITLE))
							.build();
					
					DaoFactory daoFactory = new JdbcDaoFactory(connection);
					daoFactory.getEmployeeDao().create(savedEmployee);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(saveButton, BorderLayout.PAGE_END);
		
		add(panel);
	
		pack();
		
		setMinimumSize(new Dimension(300, 350));
		setSize(getPreferredSize());
		setResizable(false);
		
		setVisible(true);
	}
}
