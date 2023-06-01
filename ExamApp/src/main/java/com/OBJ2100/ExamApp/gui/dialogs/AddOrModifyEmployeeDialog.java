package com.OBJ2100.ExamApp.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;

import com.OBJ2100.ExamApp.db.DataSourceFactory;
import com.OBJ2100.ExamApp.db.dao.factories.DaoFactory;
import com.OBJ2100.ExamApp.db.dao.factories.JdbcDaoFactory;
import com.OBJ2100.ExamApp.entities.Employee;
import com.OBJ2100.ExamApp.gui.Helpers.MessageHelper;
import com.OBJ2100.ExamApp.gui.utilities.SpringUtilities;

public class AddOrModifyEmployeeDialog extends JDialog {
	
	private enum EmployeeOperation {
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
	
	private List<Employee> employees;
	private Map<EmployeeDetail, JTextField> fieldByEmployeeDetail = new HashMap<>();
	
	private JComboBox<Integer> employeeNumberComboBox;
	private JCheckBox isNewCheckbox;
	private JComboBox<Integer> reportsToComboBox;
	private JCheckBox notReportingCheckbox;
	private JButton saveButton;
	
	public AddOrModifyEmployeeDialog() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				fetchEmployees();
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
	
	private void fetchEmployees() {
		DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			DaoFactory daoFactory = new JdbcDaoFactory(connection);
			employees = daoFactory.getEmployeeDao().getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void populateFieldsWithSelected() {
		if (employeeNumberComboBox == null) return;
		
		Integer employeeNumber = (Integer) employeeNumberComboBox.getSelectedItem();
		if (employeeNumber == null) return;
		
		Employee employee = employees.stream()
				.filter(emp -> employeeNumber.equals(emp.getEmployeeNumber()))
				.findFirst().orElse(null);
		if (employee == null) return;
		
		fieldByEmployeeDetail.get(EmployeeDetail.LAST_NAME).setText(employee.getLastName());
		fieldByEmployeeDetail.get(EmployeeDetail.FIRST_NAME).setText(employee.getFirstName());
		fieldByEmployeeDetail.get(EmployeeDetail.EXTENSION).setText(employee.getExtension());
		fieldByEmployeeDetail.get(EmployeeDetail.EMAIL).setText(employee.getEmail());
		fieldByEmployeeDetail.get(EmployeeDetail.OFFICE_CODE).setText(employee.getOfficeCode());
		fieldByEmployeeDetail.get(EmployeeDetail.JOB_TITLE).setText(employee.getJobTitle());
		
		Integer reportsTo = employee.getReportsTo();
		if (reportsTo == null) {
			notReportingCheckbox.setSelected(true);
			reportsToComboBox.setSelectedItem(employeeNumber);
		} else {
			if (notReportingCheckbox.isSelected())
				notReportingCheckbox.setSelected(false);
			reportsToComboBox.setSelectedItem(employee.getReportsTo());
		}
	}
	
	public void initComponents() {		
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel formPanel = new JPanel(new SpringLayout());
		for (EmployeeDetail detail : EmployeeDetail.values()) {
			JPanel fieldPane = new JPanel();
			
			if (detail.equals(EmployeeDetail.NUMBER)) {
				JPanel whenUpdatingEmployee = new JPanel();
				Integer[] allEmployeeNumbers = employees.stream()
						.map(e -> e.getEmployeeNumber())
						.toArray(Integer[]::new);
				employeeNumberComboBox = new JComboBox<>(allEmployeeNumbers);
				employeeNumberComboBox.addActionListener(l -> populateFieldsWithSelected());
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
						EmployeeOperation operation = getCurrentOperation();
						CardLayout layout = (CardLayout) employeeNumberPane.getLayout();
						layout.show(employeeNumberPane, operation.name());
						
						if (operation.equals(EmployeeOperation.UPDATING)) {
							populateFieldsWithSelected();
						} else {
							fieldByEmployeeDetail.values().forEach(f -> f.setText(null));
							
							int highestEmployeeNumber = employees.stream()
									.mapToInt(Employee::getEmployeeNumber)
									.max().orElseThrow(NoSuchElementException::new);
							fieldByEmployeeDetail.get(EmployeeDetail.NUMBER).setText(
									String.valueOf(highestEmployeeNumber + 1));
						}
					}
				});
				
				fieldPane.add(employeeNumberPane);
				fieldPane.add(isNewCheckbox);
				
				fieldByEmployeeDetail.put(detail, employeeNumberField);
			} else if (detail.equals(EmployeeDetail.REPORTS_TO)) {
				Integer[] employeeNumbers = employees.stream()
						.map(Employee::getEmployeeNumber)
						.toArray(Integer[]::new);
				reportsToComboBox = new JComboBox<>(employeeNumbers);
				
				notReportingCheckbox = new JCheckBox("Nobody");
				notReportingCheckbox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (notReportingCheckbox.isSelected()) {
							reportsToComboBox.setEnabled(false);
						} else {
							reportsToComboBox.setEnabled(true);
						}
					}
				});
				
				JPanel reportsToPane = new JPanel();
				reportsToPane.add(reportsToComboBox);
				reportsToPane.add(notReportingCheckbox);
				
				fieldPane.add(reportsToPane);
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
		SpringUtilities.makeCompactGrid(formPanel, EmployeeDetail.values().length, 2, 6, 6, 6, 6);
		panel.add(formPanel, BorderLayout.CENTER);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(l -> saveCurrentEmployee());
		panel.add(saveButton, BorderLayout.PAGE_END);
		
		add(panel);
	
		pack();
		
		setMinimumSize(new Dimension(300, 350));
		setSize(getPreferredSize());
		setResizable(false);
		
		setVisible(true);
	}
	
	private EmployeeOperation getCurrentOperation() {
		return isNewCheckbox.isSelected() ?
				EmployeeOperation.ADDING : EmployeeOperation.UPDATING;
	}
	
	private void saveCurrentEmployee() {
		boolean successful = false; 
		
		DataSource source = DataSourceFactory.getMySqlDataSource();
		try (Connection connection = source.getConnection()) {
			Map<EmployeeDetail, String> byDetail = fieldByEmployeeDetail.entrySet()
					.stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getText()));
			
			EmployeeOperation operation = getCurrentOperation();
			Integer employeeNumber = (operation == EmployeeOperation.UPDATING) ?
				(Integer) employeeNumberComboBox.getSelectedItem() : Integer.valueOf(byDetail.get(EmployeeDetail.NUMBER));
			
			Integer reportsTo = notReportingCheckbox.isSelected() ? 
					null : (int) reportsToComboBox.getSelectedItem();
			
			Employee savedEmployee = new Employee.Builder()
					.employeeNumber(employeeNumber)
					.lastName(byDetail.get(EmployeeDetail.LAST_NAME))
					.firstName(byDetail.get(EmployeeDetail.FIRST_NAME))
					.extension(byDetail.get(EmployeeDetail.EXTENSION))
					.email(byDetail.get(EmployeeDetail.EMAIL))
					.officeCode(byDetail.get(EmployeeDetail.OFFICE_CODE))
					.reportsTo(reportsTo)
					.jobTitle(byDetail.get(EmployeeDetail.JOB_TITLE))
					.build();
			
			DaoFactory daoFactory = new JdbcDaoFactory(connection);
			if (isNewCheckbox.isSelected()) {
				daoFactory.getEmployeeDao().create(savedEmployee);		
			} else {
				daoFactory.getEmployeeDao().updateByEmployeeNumber(
						employeeNumber, savedEmployee);
			}
			
			successful = true;
			
			// TODO : update in text field with saved employee
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (successful) {
			MessageHelper.displayMessage("Employee updated successfully!", "Success");
		} else {
			MessageHelper.displayMessage("Could not save employee. Please try again.", "Error");
		}
	}
}
