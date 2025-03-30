package com.brainstorm.EmployeeRecordsApp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.brainstorm.EmployeeRecordsApp.Dto.AddressDto;
import com.brainstorm.EmployeeRecordsApp.Dto.EmployeeDto;
import com.brainstorm.EmployeeRecordsApp.Service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeeRecordsAppApplication.class) 
@AutoConfigureMockMvc 
@ComponentScan(basePackages = "com.brainstorm.EmployeeRecordsApp") 
class TestEmployeeController {

	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private EmployeeService employeeService;  
	    @Autowired
	    private ObjectMapper objectMapper;

	    private EmployeeDto employeeDto;
	    
	    @BeforeEach
	    void setUp() {
	    	employeeDto = new EmployeeDto();
	        employeeDto.setId(1L);
	        employeeDto.setName("John Doe");
	        employeeDto.setEmail("john.doe@example.com");
	        employeeDto.setPosition("Software Engineer");
	        employeeDto.setSalary(75000.0);
	    	
	    	AddressDto addressDto = new AddressDto();
	        addressDto.setLine1("123 Main Street");
	        addressDto.setLine2("Apt 4B");
	        addressDto.setCity("New York");
	        addressDto.setState("NY");
	        addressDto.setCountry("USA");
	        addressDto.setPincode(10001);
            
	        employeeDto.setAddress(addressDto);
	        
	    }

	    @Test
	    void testSaveEmployee() throws Exception
	    {
	        Mockito.when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

	        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees/addEmployee")
	        		.with(httpBasic("admin", "SuccEss@45")) 
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(employeeDto)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.name").value("John Doe"))
	                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
	                .andExpect(jsonPath("$.address.city").value("New York"));
	    }
	    @ParameterizedTest
	    @CsvSource({
	        "admin, SuccEss@45",
	        "customer, TechsAvy@86"
	    })

	    void testGetAllEmployees(String username,String password) throws Exception 
	    {
	        List<EmployeeDto> employees = Arrays.asList(employeeDto);
	        Mockito.when(employeeService.getAllEmployee()).thenReturn(employees);

	        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/employeeCatalog")
	        		.with(httpBasic(username, password)) 
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()").value(1))
	                .andExpect(jsonPath("$[0].name").value("John Doe"));
	    }
        
	    @ParameterizedTest
	    @CsvSource({
	        "admin, SuccEss@45",
	        "customer, TechsAvy@86"
	    })
	    void testGetEmployeeById(String username,String password) throws Exception 
	    {
	        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employeeDto);

	        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/getEmployeeRecord/1")
	        		.with(httpBasic(username, password))       
	        		.contentType(MediaType.APPLICATION_JSON)) 
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.name").value("John Doe"))
	                .andExpect(jsonPath("$.position").value("Software Engineer"));
	    }

	    @Test
	    void testUpdateEmployee() throws Exception 
	    {
	        EmployeeDto updatedEmployee = new EmployeeDto();
	        updatedEmployee.setName("Alice Johnson");

	        Mockito.when(employeeService.updateEmployeeById(anyLong(), any(EmployeeDto.class)))
	                .thenReturn(updatedEmployee);

	        mockMvc.perform(MockMvcRequestBuilders.patch("/api/employees/modifyEmployee/1")
	        		.with(httpBasic("admin", "SuccEss@45")) 
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(updatedEmployee)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.name").value("Alice Johnson"));
	    }

	    @Test
	    void testDeleteEmployee() throws Exception 
	    {
	    	 Mockito.doNothing().when(employeeService).deleteEmployeeById(1L);

	    	    mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/deleteEmployee/1")
	    	        .with(httpBasic("admin", "SuccEss@45"))  
	    	        .contentType(MediaType.APPLICATION_JSON))  
	    	        .andExpect(status().isOk());  
	    }
	    
}
