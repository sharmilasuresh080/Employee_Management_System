package com.brainstorm.EmployeeRecordsApp.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	    private Long id;
	    private String line1;
	    private String line2;
	    private String city;
	    private String state;
	    private String country;
	    private int pincode;
	    
	    @JsonIgnore
	    private EmployeeDto employeeDto;	
	    
}
