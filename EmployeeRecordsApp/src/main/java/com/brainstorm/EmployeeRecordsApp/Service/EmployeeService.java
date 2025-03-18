package com.brainstorm.EmployeeRecordsApp.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import com.brainstorm.EmployeeRecordsApp.Model.Address;
import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import com.brainstorm.EmployeeRecordsApp.Repository.EmployeeRepository;


@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee createEmployee(Employee employee)
	{
		if (employee.getAddress() != null) 
			employee.getAddress().setEmployee(employee); 
	    
		return employeeRepository.save(employee);
	}
	public Employee getEmployeeById(Long id)
	{
	  Optional<Employee> result =employeeRepository.findById(id);
	  if(result.isPresent())
		  return result.get();
	  else
		  throw new RuntimeException("Employee Not Found.");  
	}
	public List<Employee> getAllEmployee()
	{
	  return employeeRepository.findAll();	
	}
	
	public Employee updateEmployeeById(Long newId, Map<String, Object> newFieldValues)
	{
	    Optional<Employee> result = employeeRepository.findById(newId);

	    if (result.isPresent()) {
	        newFieldValues.forEach((key, value)->{
	        	Field field=ReflectionUtils.findField(Employee.class,key);
	        	if (field != null) 
	        	{   
	        		field.setAccessible(true);
	                ReflectionUtils.setField(field, result.get(), value);
	            } 
	        	else 
	            {
	                Address address = result.get().getAddress();
	                
	                Field addressField = ReflectionUtils.findField(Address.class, key);
	                if (addressField != null) 
	                {
	                	addressField.setAccessible(true);
	                    ReflectionUtils.setField(addressField, address, value);
	                }
	              }
	        });
	        return employeeRepository.save(result.get());
	    } 
	    else 
	    {
	        throw new RuntimeException("Employee record is missing.");
	    }
	}

	public List<Employee> deleteEmployeeById(Long Id)
	{ 
		Optional<Employee> result=employeeRepository.findById(Id);
		if(result.isPresent())
		{  
			Employee employee=result.get();
		    employeeRepository.delete(employee);
		   return employeeRepository.findAll();
		}
		else
			throw new RuntimeException("Already Employee Record is not present .");
	}
}
