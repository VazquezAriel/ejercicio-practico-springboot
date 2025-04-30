package com.minegocio.alquimiasoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minegocio.alquimiasoft.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}