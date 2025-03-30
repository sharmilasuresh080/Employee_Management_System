package com.brainstorm.EmployeeRecordsApp.Mapper;

import com.brainstorm.EmployeeRecordsApp.Dto.EmployeeDto;
import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-30T23:56:11+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public EmployeeDto convertEntityToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setAddress( addressMapper.convertEntityToDto( employee.getAddress() ) );
        employeeDto.setEmail( employee.getEmail() );
        employeeDto.setId( employee.getId() );
        employeeDto.setName( employee.getName() );
        employeeDto.setPosition( employee.getPosition() );
        employeeDto.setSalary( employee.getSalary() );

        return employeeDto;
    }

    @Override
    public Employee convertDtoToEntity(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setAddress( addressMapper.convertDtoToEntity( employeeDto.getAddress() ) );
        employee.setEmail( employeeDto.getEmail() );
        employee.setId( employeeDto.getId() );
        employee.setName( employeeDto.getName() );
        employee.setPosition( employeeDto.getPosition() );
        employee.setSalary( employeeDto.getSalary() );

        return employee;
    }
}
