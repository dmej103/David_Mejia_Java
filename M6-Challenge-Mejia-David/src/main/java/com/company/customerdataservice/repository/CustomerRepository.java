package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    /* All methods that come in pre-loaded
     count()
     delete()
     deleteAll()
     deleteById()
     existsById()
     findAll()
     findAllById()
     save()
     saveAll()
    */


    List<Customer> findAllCustomersByState(String state);
}
