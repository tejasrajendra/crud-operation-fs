package com.backend.crudop.reposistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.backend.crudop.Entity.Employee;
import com.backend.crudop.reposistory.EmployeeRepository;

@DataJpaTest
@ActiveProfiles("test")
public class employeerepositorytest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindByEmailId() {
        // Given
        Employee employee = new Employee();
        employee.setFirstName("John"); 
        employee.setEmailId("john.cena@gmail.com");
        employeeRepository.save(employee);
        Employee foundEmployee = employeeRepository.findByEmailId("john.cena@example.com");

        assertNotNull(foundEmployee);
        assertEquals("John Cena", foundEmployee.getFirstName());
        assertEquals("john.cena@example.com", foundEmployee.getEmailId());
    }

}
