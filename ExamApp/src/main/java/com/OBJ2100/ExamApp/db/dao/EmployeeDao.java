package com.OBJ2100.ExamApp.db.dao;

import java.util.List;
import java.util.Optional;

import com.OBJ2100.ExamApp.db.entities.Employee;

public interface EmployeeDao {
	Optional<Employee> getByEmployeeNumber(int employeeNumber);
	List<Employee> getAll();
	void create(Employee employee);
	void updateByEmployeeNumber(int employeeNumber, Employee employee);
	void deleteByEmployeeNumber(int employeeNumber);
}
