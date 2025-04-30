package com.minegocio.alquimiasoft.mapper;

import com.minegocio.alquimiasoft.dto.AddressResponseDto;
import com.minegocio.alquimiasoft.dto.CreateAdditionalAddressRequestDto;
import com.minegocio.alquimiasoft.dto.CreateMainAddressRequestDto;
import com.minegocio.alquimiasoft.model.Address;
import com.minegocio.alquimiasoft.model.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "main", constant = "true")
    @Mapping(target = "customer", source = "customer")
    Address toAddress(CreateMainAddressRequestDto addressRequestDto, Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "main", constant = "false")
    @Mapping(target = "customer", source = "customer")
    Address toAddress(CreateAdditionalAddressRequestDto addressRequestDto, Customer customer);

    AddressResponseDto toAddressResponseDto(Address address);

}
