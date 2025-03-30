package com.brainstorm.EmployeeRecordsApp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.brainstorm.EmployeeRecordsApp.Dto.AddressDto;
import com.brainstorm.EmployeeRecordsApp.Dto.EmployeeDto;
import com.brainstorm.EmployeeRecordsApp.Mapper.EmployeeMapper;
import com.brainstorm.EmployeeRecordsApp.Model.Address;
import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import com.brainstorm.EmployeeRecordsApp.Repository.EmployeeRepository;
import com.brainstorm.EmployeeRecordsApp.Service.EmployeeService;

class TestEmployeeService {

	    @Mock
	    private EmployeeRepository employeeRepository;

	    @Mock
	    private EmployeeMapper employeeMapper;

	    @InjectMocks
	    private EmployeeService employeeService;

	    private Employee employee;
	    private EmployeeDto employeeDto;
	    private Address address;
	    private AddressDto addressDto;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        
	        // Creating test Address
	        address = new Address();
	        address.setId(1L);
	        address.setLine1("123 Main Street");
	        address.setCity("New York");
	        address.setState("NY");
	        address.setCountry("USA");
	        address.setPincode(10001);

	        addressDto = new AddressDto();
	        addressDto.setId(1L);
	        addressDto.setLine1("123 Main Street");
	        addressDto.setCity("New York");
	        addressDto.setState("NY");
	        addressDto.setCountry("USA");
	        addressDto.setPincode(10001);

	        // Creating test Employee
	        employee = new Employee();
	        employee.setId(1L);
	        employee.setName("John Doe");
	        employee.setEmail("john.doe@example.com");
	        employee.setPosition("Software Engineer");
	        employee.setSalary(75000.0);
	        employee.setAddress(address);

	        employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setName("John Doe");
	        employeeDto.setEmail("john.doe@example.com");
	        employeeDto.setPosition("Software Engineer");
	        employeeDto.setSalary(75000.0);
	        employeeDto.setAddress(addressDto);
	    }

	    @Test
	    void testCreateEmployee() {
	        when(employeeMapper.convertDtoToEntity(any(EmployeeDto.class))).thenReturn(employee);
	        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
	        when(employeeMapper.convertEntityToDto(any(Employee.class))).thenReturn(employeeDto);

	        EmployeeDto result = employeeService.createEmployee(employeeDto);

	        assertNotNull(result);
	        assertEquals("John Doe", result.getName());
	        assertEquals("Software Engineer", result.getPosition());
	        assertEquals(75000.0, result.getSalary());
	        verify(employeeRepository, times(1)).save(any(Employee.class));
	    }

	    @Test
	    void testGetEmployeeById_Found() {
	        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
	        when(employeeMapper.convertEntityToDto(any(Employee.class))).thenReturn(employeeDto);

	        EmployeeDto result = employeeService.getEmployeeById(1L);

	        assertNotNull(result);
	        assertEquals("John Doe", result.getName());
	    }

	    @Test
	    void testGetEmployeeById_NotFound() {
	        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

	        Exception exception = assertThrows(RuntimeException.class, () -> {
	            employeeService.getEmployeeById(1L);
	        });

	        assertEquals("Employee Not Found.", exception.getMessage());
	    }

	    @Test
	    void testGetAllEmployees() {
	        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
	        when(employeeMapper.convertEntityToDto(any(Employee.class))).thenReturn(employeeDto);

	        List<EmployeeDto> employees = employeeService.getAllEmployee();

	        assertFalse(employees.isEmpty());
	        assertEquals(1, employees.size());
	    }

	    @Test
	    void testUpdateEmployeeById() {
	        EmployeeDto updatedEmployeeDto = new EmployeeDto();
	        updatedEmployeeDto.setName("Jane Doe");
	        updatedEmployeeDto.setPosition("Senior Developer");

	        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
	        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
	        when(employeeMapper.convertEntityToDto(any(Employee.class))).thenReturn(updatedEmployeeDto);

	        EmployeeDto result = employeeService.updateEmployeeById(1L, updatedEmployeeDto);

	        assertNotNull(result);
	        assertEquals("Jane Doe", result.getName());
	        assertEquals("Senior Developer", result.getPosition());
	    }

	    @Test
	    void testDeleteEmployeeById() {
	        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
	        doNothing().when(employeeRepository).delete(employee);

	        assertDoesNotThrow(() -> employeeService.deleteEmployeeById(1L));
	        verify(employeeRepository, times(1)).delete(employee);
	    }
	}


