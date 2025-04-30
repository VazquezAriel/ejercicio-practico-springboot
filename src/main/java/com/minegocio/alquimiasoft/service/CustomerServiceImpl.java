package com.minegocio.alquimiasoft.service;

import com.minegocio.alquimiasoft.dto.AddressResponseDto;
import com.minegocio.alquimiasoft.dto.CreateAdditionalAddressRequestDto;
import com.minegocio.alquimiasoft.dto.CreateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.CustomerResponseDto;
import com.minegocio.alquimiasoft.dto.UpdateCustomerRequestDto;
import com.minegocio.alquimiasoft.exception.CustomerValidationException;
import com.minegocio.alquimiasoft.mapper.AddressMapper;
import com.minegocio.alquimiasoft.mapper.CustomerMapper;
import com.minegocio.alquimiasoft.model.Address;
import com.minegocio.alquimiasoft.model.Customer;
import com.minegocio.alquimiasoft.repository.AddressRepository;
import com.minegocio.alquimiasoft.repository.CustomerRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<CustomerResponseDto> searchCustomers(String identification, String name) {
        return customerRepository.search(
                Optional.ofNullable(identification).orElse("todos"), 
                Optional.ofNullable(name).orElse("todos")
            )
            .stream()
            .map(customer -> {
                log.info(customer.toString());
                return CustomerMapper.mapper.toCustomerResponseDto(customer);})
            .toList();
    }

    @Override
    @Transactional
    public CustomerResponseDto createCustomer(CreateCustomerRequestDto request) {
        if(customerRepository.existsByIdentificationNumber(request.getIdentificationNumber()))
            throw new CustomerValidationException("El número de identificación '" + request.getIdentificationNumber() +"' ya está registrado.");
            
        Customer newCustomer = CustomerMapper.mapper.toCustomer(request);
        Address mainAddress = AddressMapper.mapper.toAddress(request.getMainAddress(), newCustomer);

        newCustomer.setAddresses(Collections.singletonList(mainAddress));

        return CustomerMapper.mapper.toCustomerResponseDto(
            customerRepository.save(newCustomer)
        );
    }

    @Override
    public CustomerResponseDto updateCustomer(UpdateCustomerRequestDto request) {
        return customerRepository
            .findById(request.getId())
            .map(customer -> {
                CustomerMapper.mapper.updateCustomerFromDto(request, customer);
                Customer updated = customerRepository.save(customer);
                return CustomerMapper.mapper.toCustomerResponseDto(updated);
            })
            .orElseThrow(() -> new CustomerValidationException("Cliente no encontrado con id: " + request.getId()));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
            .ifPresentOrElse(
                customerRepository::delete,
                () -> { throw new CustomerValidationException("Cliente no encontrado con id: " + id); }
            );
    }

    @Override
    public AddressResponseDto addAddressToCustomer(CreateAdditionalAddressRequestDto request) {
        return customerRepository
            .findById(request.getCustomerId())
            .map(customer -> {
                Address newAddress = AddressMapper.mapper.toAddress(request, customer);
                customer.getAddresses().add(newAddress);
                Address savedAddress = addressRepository.save(newAddress);
                return AddressMapper.mapper.toAddressResponseDto(savedAddress);
            })
            .orElseThrow(() -> new CustomerValidationException("Cliente no encontrado con id: " + request.getCustomerId()));
    }

    @Override
    public List<AddressResponseDto> getAddressesByCustomerId(Long customerId) {
        return customerRepository.findById(customerId)
            .map(customer -> {
                return customer.getAddresses().stream()
                    .map(AddressMapper.mapper::toAddressResponseDto)
                    .collect(Collectors.toList());
            })
            .orElseThrow(() -> new CustomerValidationException("Cliente no encontrado con id: " + customerId));
    }

    @Override
    public CustomerResponseDto setMainAddress(Long customerId, Long addressId) {
        return customerRepository.findById(customerId)
            .map(customer -> {
                boolean found = customer.getAddresses()
                    .stream()
                    .peek(address -> address.setMain(address.getId().equals(addressId)))
                    .anyMatch(address -> address.getId().equals(addressId));
                if (found) 
                    return CustomerMapper.mapper.toCustomerResponseDto(customerRepository.save(customer));
                throw new CustomerValidationException("Dirección no encontrada con id: " + addressId);
            })
            .orElseThrow(() -> new CustomerValidationException("Cliente no encontrado con id: " + customerId));
    }

}