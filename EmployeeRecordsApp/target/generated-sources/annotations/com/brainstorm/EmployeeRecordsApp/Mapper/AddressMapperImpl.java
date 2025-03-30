package com.brainstorm.EmployeeRecordsApp.Mapper;

import com.brainstorm.EmployeeRecordsApp.Dto.AddressDto;
import com.brainstorm.EmployeeRecordsApp.Model.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-30T23:56:11+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto convertEntityToDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setCity( address.getCity() );
        addressDto.setCountry( address.getCountry() );
        addressDto.setId( address.getId() );
        addressDto.setLine1( address.getLine1() );
        addressDto.setLine2( address.getLine2() );
        addressDto.setPincode( address.getPincode() );
        addressDto.setState( address.getState() );

        return addressDto;
    }

    @Override
    public Address convertDtoToEntity(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setCity( addressDto.getCity() );
        address.setCountry( addressDto.getCountry() );
        address.setId( addressDto.getId() );
        address.setLine1( addressDto.getLine1() );
        address.setLine2( addressDto.getLine2() );
        address.setPincode( addressDto.getPincode() );
        address.setState( addressDto.getState() );

        return address;
    }
}
