package com.minegocio.alquimiasoft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minegocio.alquimiasoft.model.Address;
import com.minegocio.alquimiasoft.model.Customer;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomerAndMainFalse(Customer customer);

    Optional<Address> findByCustomerAndMainTrue(Customer customer);
}