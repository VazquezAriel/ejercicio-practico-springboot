package com.minegocio.alquimiasoft.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.alquimiasoft.dto.AddressResponseDto;
import com.minegocio.alquimiasoft.dto.CreateAdditionalAddressRequestDto;
import com.minegocio.alquimiasoft.dto.CreateCustomerRequestDto;
import com.minegocio.alquimiasoft.dto.CustomerResponseDto;
import com.minegocio.alquimiasoft.dto.UpdateCustomerRequestDto;
import com.minegocio.alquimiasoft.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDto>> searchCustomers(@RequestParam(required = false) String identification, @RequestParam(required = false) String name) {
        return ResponseEntity.ok(customerService.searchCustomers(identification, name));
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid CreateCustomerRequestDto request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@RequestBody @Valid UpdateCustomerRequestDto request) {
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addresses/create")
    public ResponseEntity<AddressResponseDto> addAddressToCustomer(@RequestBody @Valid CreateAdditionalAddressRequestDto request) {
        return ResponseEntity.ok(customerService.addAddressToCustomer(request));
    }

    @GetMapping("/addresses/{customerId}")
    public ResponseEntity<List<AddressResponseDto>> getAddressesByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getAddressesByCustomerId(customerId));
    }

    @PatchMapping("/addresses/set-main/{customerId}/{addressId}")
    public ResponseEntity<CustomerResponseDto> setMainAddress( @PathVariable Long customerId, @PathVariable Long addressId) {
        return ResponseEntity.ok(customerService.setMainAddress(customerId, addressId));
    }
}