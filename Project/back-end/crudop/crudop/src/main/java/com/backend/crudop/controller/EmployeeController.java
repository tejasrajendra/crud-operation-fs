package com.backend.crudop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crudop.Entity.Employee;
import com.backend.crudop.service.EmployeeService;


@CrossOrigin("")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	

	@GetMapping
	    public List<Employee> getAllEmployees(){
	        return employeeService.getAllEmployees();
	    }

	    @PostMapping
	    public Employee createEmployee(@RequestBody Employee employee) {
	        return employeeService.saveEmployee(employee);
	    }
	    
	    @GetMapping("{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id){
	        Employee employee = employeeService.findById(id);
	        return ResponseEntity.ok(employee);
	    }
	    
	    @PutMapping("{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
	        Employee employee = employeeService.updateById(id , employeeDetails);
	        return ResponseEntity.ok(employee);
	    }
	    
	    
	    @DeleteMapping("{id}")
	    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

	        employeeService.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	    }
	    

}
