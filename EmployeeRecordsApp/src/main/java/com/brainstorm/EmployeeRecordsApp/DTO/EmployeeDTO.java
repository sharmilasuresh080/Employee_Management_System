package com.brainstorm.EmployeeRecordsApp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public class EmployeeDTO {

    @NotNull(message = "Employee ID is required")
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Position cannot be empty")
    private String position;

    @NotNull(message = "Salary is required")
    @Min(value = 1, message = "Salary must be positive")
    private Double salary;

    @NotEmpty(message = "At least one address is required")
    private Set<AddressDTO> addresses;

    // Getters 
    public Long getId() { 
    	return id; 
    }
    
    public String getName() { 
    	return name; 
    }
    
    public String getEmail() { 
    	return email; 
    }
    
    public String getPosition() { 
    	return position; 
    }
    
    public Double getSalary() { 
    	return salary; 
    }
    
    public Set<AddressDTO> getAddresses() { 
    	return addresses;	
    }
    
    //Setters
    public void setId(Long id) { 
    	this.id = id; 
    }

    public void setName(String name) { 
    	this.name = name; 
    }
    
    public void setEmail(String email) { 
    	this.email = email; 
    }

    public void setPosition(String position) { 
    	this.position = position; 
    }

    public void setSalary(Double salary) { 
    	this.salary = salary; 
    }

    public void setAddresses(Set<AddressDTO> addresses) { 
    	this.addresses = addresses; 
    }
}
