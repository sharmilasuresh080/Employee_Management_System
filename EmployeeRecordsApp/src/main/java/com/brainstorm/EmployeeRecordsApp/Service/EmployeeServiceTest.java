import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.brainstorm.EmployeeRecordsApp.Model.Address;
import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import com.brainstorm.EmployeeRecordsApp.Repository.EmployeeRepository;

@SpringBootTest 
@Transactional
public class EmployeeServiceTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setup() {
        
        employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setPosition("Software Engineer");
        employee.setSalary(75000.0);

        
        Address address = new Address();
        address.setLine1("123 Main Street");
        address.setCity("New York");
        address.setState("NY");
        address.setCountry("USA");
        address.setPincode(10001L);
        address.setEmployee(employee);

        Set<Address> addresses = new HashSet<>();
        addresses.add(address);
        employee.setAddresses(addresses);

        
        employee = employeeRepository.save(employee);
    }

   
    @Test
    void testCreateEmployee() {
        Employee savedEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getName());
    }

    
    @Test
    void testReadEmployee() {
        Optional<Employee> result = employeeRepository.findById(employee.getId());
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

   
    @Test
    void testUpdateEmployee() {
        employee.setName("Jane Doe");
        employee.setSalary(80000.0);
        Employee updatedEmployee = employeeRepository.save(employee);

        assertNotNull(updatedEmployee);
        assertEquals("Jane Doe", updatedEmployee.getName());
        assertEquals(80000.0, updatedEmployee.getSalary());
    }

    
    @Test
    void testDeleteEmployee() {
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> deletedEmployee = employeeRepository.findById(employee.getId());
        assertFalse(deletedEmployee.isPresent());
    }
}
