package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    // Wiring for creating new customer record
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    // Wiring for updating customer record by ID
    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        repo.save(customer);
    }

    // Wiring for deleting a customer record by id
    @DeleteMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) {
        repo.deleteById(customerId);
    }

    // Find customers by ID
    @GetMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findCustomerById(@PathVariable int customerId) {
        Optional<Customer> returnVal = repo.findById(customerId);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // Find customers by state
    @GetMapping("/customers/state/{state}")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findCustomerByState(@PathVariable String state) {
        return repo.findAllCustomersByState(state);
    }

}
