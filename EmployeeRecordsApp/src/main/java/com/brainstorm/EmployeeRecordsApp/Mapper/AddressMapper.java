package com.brainstorm.EmployeeRecordsApp.Mapper;

import com.brainstorm.EmployeeRecordsApp.Dto.AddressDto;
import com.brainstorm.EmployeeRecordsApp.Model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "employeeDto", ignore = true) 
    AddressDto convertEntityToDto(Address address);

    @Mapping(target = "employee", ignore = true) 
    Address convertDtoToEntity(AddressDto addressDto);
}
