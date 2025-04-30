package com.minegocio.alquimiasoft.mapper;

import com.minegocio.alquimiasoft.dto.AddressResponseDto;
import com.minegocio.alquimiasoft.dto.CreateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.UpdateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.CustomerResponseDto;
import com.minegocio.alquimiasoft.model.Address;
import com.minegocio.alquimiasoft.model.Customer;
import org.mapstruct.factory.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    Customer toCustomer(CreateCustomerRequestDto customerRequestDto);

    @Mapping(target = "addresses", ignore = true)
    void updateCustomerFromDto(UpdateCustomerRequestDto dto, @MappingTarget Customer customer);

    @Mapping(target = "mainAddress", expression = "java(extractMainAddress(customer))")
    CustomerResponseDto toCustomerResponseDto(Customer customer);

    default AddressResponseDto extractMainAddress(Customer customer) {
        if (customer.getAddresses() == null) return null;
        return customer.getAddresses()
            .stream()
            .filter(Address::isMain)
            .findFirst()
            .map(AddressMapper.mapper::toAddressResponseDto)
            .orElse(null);
    }

}
