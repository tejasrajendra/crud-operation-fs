package com.backend.crudop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.crudop.Entity.Employee;
import com.backend.crudop.Exception.ResourceNotFoundException;
import com.backend.crudop.reposistory.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(Long id) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
		return employee;
	}

	@Override
	public Employee updateById(Long id , Employee employeeDetails) {
		// TODO Auto-generated method stub
		Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
		
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);
		
		return updateEmployee;
	}

	@Override
	public void  deleteById(Long id) {
		// TODO Auto-generated method stub
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.orElseThrow(()-> new ResourceNotFoundException("employee  not found"));

        employeeRepository.deleteById(id);		
	}


}
