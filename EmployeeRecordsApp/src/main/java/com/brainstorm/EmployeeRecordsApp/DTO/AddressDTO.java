package com.brainstorm.EmployeeRecordsApp.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {

    @NotNull(message = "Address ID is required")
    private Long id;

    @NotEmpty(message = "Address Line1 cannot be empty")
    private String line1;

    @NotEmpty(message = "Address Line2 cannot be empty")
    private String line2;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "State is required")
    private String state;

    @NotEmpty(message = "Country is required")
    private String country;

    @NotNull(message = "Pincode cannot be empty")
    private Long pincode;

    // Getters 
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
    
    //Setters
    public void setId(Long id) {
    	this.id = id; 
    }

    public void setLine1(String line1) { 
    	this.line1 = line1; 
    }
  
    public void setLine2(String line2) { 
    	this.line2 = line2; 
    }
 
    public void setCity(String city) { 
    	this.city = city; 
    }
 
    public void setState(String state) {
    	this.state = state; 
    }
  
    public void setCountry(String country) { 
    	this.country = country; 
    }
   
    public void setPincode(Long pincode) { 
    	this.pincode = pincode;
    }
}
