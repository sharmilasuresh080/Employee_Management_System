package com.brainstorm.EmployeeRecordsApp.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

	private Long id;
    private String name;
    private String email;
    private String position="Trainee";
    private Double salary;
    private AddressDto address; 
    
    public void setAddress(AddressDto address) {
        this.address = address;
        if (address != null) {
        	address.setEmployeeDto(this);
        }
    }
}