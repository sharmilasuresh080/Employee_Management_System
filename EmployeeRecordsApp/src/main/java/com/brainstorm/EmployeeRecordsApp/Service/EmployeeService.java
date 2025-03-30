package com.brainstorm.EmployeeRecordsApp.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brainstorm.EmployeeRecordsApp.Dto.AddressDto;
import com.brainstorm.EmployeeRecordsApp.Dto.EmployeeDto;
import com.brainstorm.EmployeeRecordsApp.Mapper.EmployeeMapper;
import com.brainstorm.EmployeeRecordsApp.Model.Address;
import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import com.brainstorm.EmployeeRecordsApp.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public EmployeeDto createEmployee(EmployeeDto employeeDto)
	{    
		Employee employee = employeeMapper.convertDtoToEntity(employeeDto); 
		
	     if (employee.getAddress() != null) 
			employee.getAddress().setEmployee(employee); 
	    
	    Employee createdEmployee =employeeRepository.save(employee);
	    return employeeMapper.convertEntityToDto(createdEmployee);
	    
	}
	public EmployeeDto getEmployeeById(Long id)
	{
	  Optional<Employee> result =employeeRepository.findById(id);
	  if(result.isPresent())
		  return employeeMapper.convertEntityToDto(result.get());
	  else
		  throw new RuntimeException("Employee Not Found.");  
	}
	public List<EmployeeDto> getAllEmployee()
	{
		List<Employee> employees =employeeRepository.findAll();	
		return employees.stream().map(employeeMapper::convertEntityToDto).toList();
	}
	
	public EmployeeDto updateEmployeeById(Long newId, EmployeeDto updatedEmployeeDto)
	{
	    Optional<Employee> newResult = employeeRepository.findById(newId);
	    Employee result = newResult.get();
        
	    if(newResult.isPresent())
	    {  	
	       if (updatedEmployeeDto.getName() != null) 
	       {
	    	result.setName(updatedEmployeeDto.getName());
           }
           if (updatedEmployeeDto.getEmail() != null) 
           {
        	result.setEmail(updatedEmployeeDto.getEmail());
           }
        if (updatedEmployeeDto.getPosition() != null) 
        {
        	result.setPosition(updatedEmployeeDto.getPosition());
        }
        if (updatedEmployeeDto.getSalary() != null) 
        {
        	result.setSalary(updatedEmployeeDto.getSalary());
        }

        if (updatedEmployeeDto.getAddress() != null) 
        {
            if (result.getAddress() == null) 
            {
               result.setAddress(new Address());
            }

            Address existingAddress = result.getAddress();
            AddressDto updatedAddress = updatedEmployeeDto.getAddress();

             if (updatedAddress.getLine1() != null) 
             {
                existingAddress.setLine1(updatedAddress.getLine1());
              }
            if (updatedAddress.getLine2() != null) 
            {
                existingAddress.setLine2(updatedAddress.getLine2());
            }
            if (updatedAddress.getCity() != null) 
            {
                existingAddress.setCity(updatedAddress.getCity());
            }
            if (updatedAddress.getState() != null) 
            {
                existingAddress.setState(updatedAddress.getState());
            }
            if (updatedAddress.getCountry() != null) 
            {
                existingAddress.setCountry(updatedAddress.getCountry());
            }
            if (updatedAddress.getPincode() != 0) 
            { 
                existingAddress.setPincode(updatedAddress.getPincode());
            }
          
             result.setAddress(existingAddress);
             
        }
        Employee updatedEmployee = employeeRepository.save(result);
        return employeeMapper.convertEntityToDto(updatedEmployee); 
	   }
	    else 
	    {
	        throw new RuntimeException("Employee record is missing.");
	    }
	}

	public void deleteEmployeeById(Long Id)
	{ 
		Optional<Employee> result=employeeRepository.findById(Id);
		if(result.isPresent())
		{  
			Employee employee=result.get();
		    employeeRepository.delete(employee);
		}
		else
			throw new RuntimeException("Already Employee Record is not present .");
	}
}
