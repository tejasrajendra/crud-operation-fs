package com.backend.crudop.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.backend.crudop.service.EmployeeService;
import com.backend.crudop.Entity.Employee;

public class employeecontrollertest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllEmployees() {
        // Mocking the service response
        Employee employee1 = new Employee(1, "John", "Cena", "john.cena@example.com");
        Employee employee2 = new Employee(2, "Jane", "Doe", "jane.doe@example.com");
        List<Employee> mockEmployees = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        // Calling the controller method
        List<Employee> result = employeeController.getAllEmployees();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
    }

    @Test
    void createEmployee() {
        // Mocking the service response
        Employee mockEmployee = new Employee(1, "John" , "Cena", "john.cena@gmail.com");

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(mockEmployee);

        // Calling the controller method
        Employee newEmployee = new Employee(0, "John" , "Cena", "john.cena@gmail.com");
        Employee result = employeeController.createEmployee(newEmployee);

        // Assertions
        assertEquals(1, result.getId());
        assertEquals("John Cena", result.getFirstName());
        assertEquals("john.cena@gmail.com", result.getEmailId());
    }

    @Test
    void getEmployeeById() {
        // Mocking the service response
        Employee mockEmployee = new Employee(1, "John" , "Cena", "john.cena@gmail.com");

        when(employeeService.findById((long) 1)).thenReturn(mockEmployee);

        // Calling the controller method
        ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById(1);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("John Cena", responseEntity.getBody().getFirstName());
        assertEquals("john.cena@gmail.com", responseEntity.getBody().getEmailId());
    }

}

