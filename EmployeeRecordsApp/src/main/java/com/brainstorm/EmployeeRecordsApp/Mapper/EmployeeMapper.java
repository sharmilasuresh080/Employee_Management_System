package com.brainstorm.EmployeeRecordsApp.Mapper;

import com.brainstorm.EmployeeRecordsApp.Dto.EmployeeDto;
import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface EmployeeMapper {

    @Mapping(target = "address", source = "address") 
    EmployeeDto convertEntityToDto(Employee employee);

    @Mapping(target = "address", source = "address")
    Employee convertDtoToEntity(EmployeeDto employeeDto);
}
