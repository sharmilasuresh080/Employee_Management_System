package com.brainstorm.EmployeeRecordsApp.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @NotNull(message = "Id is mandatory")
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid Email Format")
    private String email;
   
    @NotEmpty(message = "Position cannot be empty")
    private String position;
    
    @NotNull(message = "Salary is required")
    @Min(value = 1, message = "Salary must be positive")
    private Double salary;
    
    @OneToMany(
    		mappedBy="employee",
    		cascade=CascadeType.ALL,
    		orphanRemoval=true
    		)
    
    @JsonManagedReference
    private Set<Address> addresses = new HashSet<Address>(); 
    
    //Getters 
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
    
    public Set<Address> getAddresses() { 
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
    	 this.email=email; 
    }
    
    public void setPosition(String position) { 
    	this.position=position; 
    }

    public void setSalary(Double salary) { 
    	this.salary = salary; 	
    }
    
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
        for (Address address : addresses) {
            address.setEmployee(this);  
        }
    }
    
} 
