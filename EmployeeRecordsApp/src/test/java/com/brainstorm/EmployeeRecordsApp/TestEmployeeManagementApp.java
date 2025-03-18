package com.brainstorm.EmployeeRecordsApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.brainstorm.EmployeeRecordsApp.Model.Address;
import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import com.brainstorm.EmployeeRecordsApp.Repository.EmployeeRepository;

@SpringBootTest
class TestEmployeeManagementApp {
 
	 @Autowired
	    private EmployeeRepository employeeRepository;
	    private Employee employee;
        private Address address;
        
	    @BeforeEach
	    void setup() {
	        employee =new Employee();
	        employee.setName("Malini");
	        employee.setEmail("Malini123@gmail.com");
	        employee.setPosition("Cloud Engineer");
	        employee.setSalary(80000.0);

	        address= new Address();
	        address.setLine1("87G Albert Colony");
	        address.setLine2("MJ Talent Park");;
	        address.setCity("Washington DC");
	        address.setState("RG");
	        address.setCountry("United States");
	        address.setPincode(10001);   
	        employee.setAddress(address); 

	        employee = employeeRepository.save(employee);
	    }

	    @Test
	    void testCreateEmployee() 
	    {
	        Employee savedEmployee = employeeRepository.findById(employee.getId()).orElse(null);
	        assertNotNull(savedEmployee);
	        assertEquals("Malini", savedEmployee.getName());
	    }

	    
	    @Test
	    void testReadEmployee() 
	    {
	        Optional<Employee> result = employeeRepository.findById(employee.getId());
	        assertTrue(result.isPresent());
	        assertEquals("Malini", result.get().getName());
	    }

	   
	    @Test
	    void testUpdateEmployee() 
	    {
	        employee.setName("Malini");
	        employee.setSalary(70000.0);
	        Employee updatedEmployee = employeeRepository.save(employee);

	        assertNotNull(updatedEmployee);
	        assertEquals("Malini", updatedEmployee.getName());
	        assertEquals(70000.0, updatedEmployee.getSalary());
	    }

	    
	    @Test
	    void testDeleteEmployee() 
	    {
	        employeeRepository.deleteById(employee.getId());
	        Optional<Employee> deletedEmployee = employeeRepository.findById(employee.getId());
	        assertFalse(deletedEmployee.isPresent());
	    }

	

}
