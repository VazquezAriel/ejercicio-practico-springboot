package com.minegocio.alquimiasoft.service;

import com.minegocio.alquimiasoft.dto.AddressResponseDto;
import com.minegocio.alquimiasoft.dto.CreateAdditionalAddressRequestDto;
import com.minegocio.alquimiasoft.dto.CreateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.UpdateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.CustomerResponseDto;

import java.util.List;


public interface CustomerService {

    List<CustomerResponseDto> searchCustomers(String identification, String name);

    CustomerResponseDto createCustomer(CreateCustomerRequestDto request);

    CustomerResponseDto updateCustomer(UpdateCustomerRequestDto request);

    void deleteCustomer(Long id);

    AddressResponseDto addAddressToCustomer(CreateAdditionalAddressRequestDto request);

    List<AddressResponseDto> getAddressesByCustomerId(Long customerId);

    CustomerResponseDto setMainAddress(Long customerId, Long addressId);
}
