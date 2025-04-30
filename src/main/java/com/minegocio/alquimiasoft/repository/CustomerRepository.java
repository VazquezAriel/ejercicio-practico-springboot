package com.minegocio.alquimiasoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.minegocio.alquimiasoft.model.Customer;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
        SELECT c FROM Customer c 
        LEFT JOIN FETCH c.addresses a
        WHERE a.main = true
          AND (:identification = 'todos' OR c.identificationNumber ILIKE CONCAT('%', :identification, '%'))
          AND (:name = 'todos' OR c.name ILIKE CONCAT('%', :name, '%'))
    """)
    List<Customer> search(
        @Param("identification") String identification,
        @Param("name") String name
    );

    boolean existsByIdentificationNumber(String identificationNumber);
    
}
