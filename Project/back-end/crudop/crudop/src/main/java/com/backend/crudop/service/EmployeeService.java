package com.backend.crudop.service;

import java.util.List;

import com.backend.crudop.Entity.Employee;



public interface EmployeeService {

	Employee saveEmployee(Employee emp);
	List<Employee> getAllEmployees();
	Employee findById(Long id);
	
}
