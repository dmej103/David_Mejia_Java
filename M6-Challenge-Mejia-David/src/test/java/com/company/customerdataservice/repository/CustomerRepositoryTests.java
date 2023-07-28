package com.company.customerdataservice.repository;

import com.company.customerdataservice.controller.CustomerController;
import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    CustomerController customerControllerTest;

    @Test
    void contextLoads() throws Exception {
        assertThat(customerControllerTest).isNotNull();
    }

    @BeforeEach
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    // Test to addTest
    @Test
    public void shouldAddCustomer() throws Exception {

        // Arrange...
        Customer customer = new Customer();

        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("test@netflix.com");
        customer.setCompany("Netflix");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Bowery");
        customer.setAddress2("Apt 2A");
        customer.setCity("New York City");
        customer.setState("NY");
        customer.setPostalCode("11372");
        customer.setCountry("US");

        customerRepo.save(customer);

        // Act...
        Optional<Customer> customer1 = customerRepo.findById(customer.getCustomerId());

        // Assert...
        assertEquals(customer1.get(), customer);

    }

    @Test
    public void shouldUpdateCustomer() {
        // Arrange...
        Customer customer = new Customer();

        customer.setCustomerId(22);
        customer.setFirstName("Peter");
        customer.setLastName("Parker");
        customer.setEmail("spiderman@gmail.com");
        customer.setCompany("Daily Bugle");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Dreary Lane");
        customer.setAddress2("Apt 2B");
        customer.setCity("Charlotte");
        customer.setState("North Carolina");
        customer.setPostalCode("12345");
        customer.setCountry("US");

        customerRepo.save(customer);

        customer.setFirstName("Harry");
        customer.setLastName("Osborne");
        customer.setEmail("harry@gmail.com");
        customer.setCompany("OsCorp");
        customer.setPhone("111-222-3333");
        customer.setAddress1("345 Park Avenue");
        customer.setAddress2("Penthouse");
        customer.setCity("New York City");
        customer.setState("NY");
        customer.setPostalCode("10002");
        customer.setCountry("US");

        customer = customerRepo.save(customer);

        // Act...
        Optional<Customer> customer1 = customerRepo.findById(customer.getCustomerId());

        // Assert...
        assertEquals(customer1.get(), customer);
    }

    // Test to Delete customer by ID
    @Test
    public void shouldDeleteCustomerById() {
        // Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Peter");
        customer.setLastName("Parker");
        customer.setEmail("spiderman@gmail.com");
        customer.setCompany("Daily Bugle");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Dreary Lane");
        customer.setAddress2("Apt 2B");
        customer.setCity("Charlotte");
        customer.setState("North Carolina");
        customer.setPostalCode("12345");
        customer.setCountry("US");

        Customer c1 = customerRepo.save(customer);

        // Act...
        customerRepo.deleteById(c1.getCustomerId());

    }

    //Test to find customer by ID
    @Test
    public void shouldFindCustomerById() {
        // Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Mary");
        customer.setLastName("Jane");
        customer.setEmail("maryjane@gmail.com");
        customer.setCompany("LES Diner");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Mulberry Street");
        customer.setAddress2("Apt 2B");
        customer.setCity("New York City");
        customer.setState("NY");
        customer.setPostalCode("12345");
        customer.setCountry("US");

        Customer c1 = customerRepo.save(customer);

        // Act...
        Optional<Customer> returnCustomer = customerRepo.findById(c1.getCustomerId());

        // Assert...
        assertEquals(returnCustomer.get(), c1);

    }

    @Test
    public void shouldFindAllCustomersByState() {
        // Arrange
        List<Customer> customerList = new ArrayList<>();

        Customer customer = new Customer();
        customer.setFirstName("Elon");
        customer.setLastName("Musk");
        customer.setEmail("elonmusk@tesla.com");
        customer.setCompany("Tesla");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Main street");
        customer.setAddress2("Penthouse");
        customer.setCity("Austin");
        customer.setState("Texas");
        customer.setPostalCode("50001");
        customer.setCountry("US");

        Customer c1 = customerRepo.save(customer);

        Customer customer2 = new Customer();
        customer2.setFirstName("Johnny");
        customer2.setLastName("Bravo");
        customer2.setEmail("jbravo@gmail.com");
        customer2.setCompany("Cartoon Network");
        customer2.setPhone("123-444-5555");
        customer2.setAddress1("100 2nd Street");
        customer2.setAddress2("1st floor");
        customer2.setCity("Dallas");
        customer2.setState("Florida");
        customer2.setPostalCode("50002");
        customer2.setCountry("US");

        Customer c2 = customerRepo.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("David");
        customer3.setLastName("Mejia");
        customer3.setEmail("davidmejia@gmail.com");
        customer3.setCompany("Netflix");
        customer3.setPhone("123-444-5555");
        customer3.setAddress1("250 Main street");
        customer3.setAddress2("Apt 2A");
        customer3.setCity("Paris");
        customer3.setState("Texas");
        customer3.setPostalCode("50005");
        customer3.setCountry("US");

        Customer c3 = customerRepo.save(customer3);


        customerList.add(c1);
        customerList.add(c3);

        // Act
        List<Customer> customersByState = customerRepo.findAllCustomersByState("Texas");


        // Assert
        assertEquals(customerList, customersByState);
    }

}