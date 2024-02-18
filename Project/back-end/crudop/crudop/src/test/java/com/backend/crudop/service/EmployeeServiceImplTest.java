package com.backend.crudop.service;

import com.backend.crudop.Entity.Employee;
import com.backend.crudop.Exception.ResourceNotFoundException;
import com.backend.crudop.reposistory.EmployeeRepository;
import com.backend.crudop.service.EmployeeService;
import com.backend.crudop.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    void saveEmployee() {
        Employee mockEmployee = new Employee(1L, "John", "Cena", "john.Cena@gmail.com");
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

        Employee savedEmployee = employeeService.saveEmployee(mockEmployee);

        assertNotNull(savedEmployee);
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Cena", savedEmployee.getLastName());
        assertEquals("john.Cena@gmail.com", savedEmployee.getEmailId());
    }

    @Test
    void getAllEmployees() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1L, "John", "Cena", "john.Cena@gmail.com"));
        mockEmployees.add(new Employee(2L, "Jason", "Smith", "jason.smith@gmail.com"));
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        List<Employee> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertEquals(2, employees.size());
    }

    @Test
    void findById() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee(employeeId, "John", "Cena", "john.Cena@gmail.com");
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));

        Employee foundEmployee = employeeService.findById(employeeId);

        assertNotNull(foundEmployee);
        assertEquals("John", foundEmployee.getFirstName());
        assertEquals("Cena", foundEmployee.getLastName());
        assertEquals("john.Cena@gmail.com", foundEmployee.getEmailId());
    }

    @Test
    void findByIdNotFound() {
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.findById(employeeId));
    }

    @Test
    void updateById() {
        Long employeeId = 1L;
        Employee originalEmployee = new Employee(employeeId, "John", "Cena", "john.Cena@gmail.com");
        Employee updatedEmployeeDetails = new Employee((Long) null, "UpdatedJohn", "UpdatedCena", "updated.john.Cena@example.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(originalEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(originalEmployee);

        Employee resultEmployee = employeeService.updateById(employeeId, updatedEmployeeDetails);

        assertNotNull(resultEmployee);
        assertEquals("UpdatedJohn", resultEmployee.getFirstName());
        assertEquals("UpdatedCena", resultEmployee.getLastName());
        assertEquals("updated.john.Cena@example.com", resultEmployee.getEmailId());
    }

    @Test
    void deleteById() {
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(new Employee(employeeId, null, null, null)));

        employeeService.deleteById(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void deleteByIdNotFound() {
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteById(employeeId));
    }
}
