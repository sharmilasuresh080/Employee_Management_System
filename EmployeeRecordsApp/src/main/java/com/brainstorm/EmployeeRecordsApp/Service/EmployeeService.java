package com.brainstorm.EmployeeRecordsApp.Service;

import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import com.brainstorm.EmployeeRecordsApp.Model.Address;
import com.brainstorm.EmployeeRecordsApp.Repository.EmployeeRepository;
import com.brainstorm.EmployeeRecordsApp.DTO.EmployeeDTO;
import com.brainstorm.EmployeeRecordsApp.DTO.AddressDTO;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee createEmployee(Employee employee)
	{
		for (Address address : employee.getAddresses()) {
	        address.setEmployee(employee);  
	    }
		return employeeRepository.save(employee);
	}
	public Employee getEmployeeById(Long id)
	{
	  Optional<Employee> result =employeeRepository.findById(id);
	  if(result.isPresent()){
		  return result.get();
	  }
	  else
	  {
		throw new RuntimeException("Employee Not Found.");  
	  }
	}
	public List<Employee> getAllEmployee()
	{
	  return employeeRepository.findAll();	
	}
	
	public Employee updateEmployeeById(Employee newEmployee) {
	    Optional<Employee> result = employeeRepository.findById(newEmployee.getId());

	    if (result.isPresent()) {
	        Employee employee = result.get();
	        employee.setName(newEmployee.getName());
	        employee.setEmail(newEmployee.getEmail());
	        employee.setPosition(newEmployee.getPosition());
	        employee.setSalary(newEmployee.getSalary());

	        // ✅ Get existing addresses from the database
	        Set<Address> existingAddresses = employee.getAddresses();
	        Set<Address> updatedAddresses = new HashSet<>();

	        for (Address newAddress : newEmployee.getAddresses()) {
	            Address address;

	            // ✅ Check if the address already exists in the employee's records
	            Optional<Address> optionalAddress = existingAddresses.stream()
	                .filter(a -> a.getId().equals(newAddress.getId()))
	                .findFirst();

	            if (optionalAddress.isPresent()) {
	                // ✅ Update existing address
	                address = optionalAddress.get();
	            } else {
	                // ✅ Create a new address if it doesn’t exist
	                address = new Address();
	                address.setEmployee(employee);  // Maintain relationship
	            }

	            // ✅ Update address fields
	            address.setId(newAddress.getId());
	            address.setLine1(newAddress.getLine1());
	            address.setLine2(newAddress.getLine2());
	            address.setCity(newAddress.getCity());
	            address.setState(newAddress.getState());
	            address.setCountry(newAddress.getCountry());
	            address.setPincode(newAddress.getPincode());

	            updatedAddresses.add(address);
	        }

	        // ✅ Instead of replacing, update the existing collection
	        employee.getAddresses().clear();
	        employee.getAddresses().addAll(updatedAddresses);

	        return employeeRepository.save(employee);
	    } else {
	        throw new RuntimeException("Employee record is missing.");
	    }
	}

	public List<Employee> deleteEmployeeById(Long Id)
	{ 
		Optional<Employee> result=employeeRepository.findById(Id);
		if(result.isPresent())
		{  Employee employee=result.get();
		   employeeRepository.delete(employee);
		   return employeeRepository.findAll();
		}
		else
		{
			throw new RuntimeException("Already Employee Record is not present .");
		}
	}
}
