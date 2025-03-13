package com.brainstorm.EmployeeRecordsApp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @NotNull(message = "Id is mandatory")
    private Long id;

    @NotEmpty(message = "Address Line1 cannot be empty")
    private String line1;

    @NotEmpty(message = "Address Line 2 cannot be empty")
    private String line2;
    
    @NotEmpty(message = "City is required")
    private String city;
    
    @NotEmpty(message = "State is required")
    private String state;
    
    @NotEmpty(message = "Country is required")
    private String country;
    
    @NotNull(message = "Pincode is mandoory")
    private Long pincode;
    
    @ManyToOne
    @JoinColumn(name="employee_id",nullable=false)
    
    @JsonBackReference
    private Employee employee;
    
    //Getters 
    public Long getId() { 
    	return id; 
    }
   
    public String getLine1() { 
    	return line1; 
    }
    
    public String getLine2() { 
    	return line2; 
    }
    
    public String getCity() { 
    	return city; 
    }
    
    public String getState() { 
    	return state; 
    }
    
    public String getCountry() { 
    	return country; 
    }
    
    public Long getPincode() { 
    	return pincode; 
    }
    public  Employee getEmployee() { 
    	return employee; 
    	}
    
    //Setters
    public void setId(Long id) { 
    	this.id = id;
    }
    
    public void setLine1(String line1) { 
    	this.line1 = line1; 
    }
    
    public void setLine2(String line2) { 
    	 this.line2=line2; 
    }
    
    public void setCity(String city) { 
    	this.city=city; 
    }
    
    public void setState(String state) { 
    	this.state=state; 
    }
    
    public void setCountry(String country) { 
    	this.country=country; 
    }
    
    public void setPincode(Long pincode) { 
    	this.pincode = pincode; 	
    }
    
    public void setEmployee(Employee employee) { 
    	this.employee = employee; 
    }
    
} 
