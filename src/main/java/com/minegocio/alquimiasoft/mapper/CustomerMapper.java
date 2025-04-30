package com.minegocio.alquimiasoft.mapper;

import com.minegocio.alquimiasoft.dto.AddressResponseDto;
import com.minegocio.alquimiasoft.dto.CreateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.UpdateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.CustomerResponseDto;
import com.minegocio.alquimiasoft.model.Address;
import com.minegocio.alquimiasoft.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class CustomerMapper {

    @Autowired
    protected AddressMapper addressMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    public abstract Customer toCustomer(CreateCustomerRequestDto customerRequestDto);

    @Mapping(target = "addresses", ignore = true)
    public abstract void updateCustomerFromDto(UpdateCustomerRequestDto dto, @MappingTarget Customer customer);

    @Mapping(target = "mainAddress", expression = "java(extractMainAddress(customer))")
    public abstract CustomerResponseDto toCustomerResponseDto(Customer customer);

    protected AddressResponseDto extractMainAddress(Customer customer) {
        if (customer.getAddresses() == null) return null;
        return customer.getAddresses()
            .stream()
            .filter(Address::isMain)
            .findFirst()
            .map(addressMapper::toAddressResponseDto)
            .orElse(null);
    }
}
