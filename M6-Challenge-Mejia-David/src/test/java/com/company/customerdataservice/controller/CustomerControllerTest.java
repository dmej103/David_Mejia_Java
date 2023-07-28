package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepository;

    private ObjectMapper mapper = new ObjectMapper();
    private List<Customer> customerList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void shouldAddCustomer() throws Exception {
        Customer inputCustomer = new Customer();
        inputCustomer.setCustomerId(80);
        inputCustomer.setFirstName("John");
        inputCustomer.setLastName("Doe");
        inputCustomer.setEmail("test@netflix.com");
        inputCustomer.setCompany("Netflix");
        inputCustomer.setPhone("123-456-7890");
        inputCustomer.setAddress1("123 Bowery");
        inputCustomer.setAddress2("Apt 2A");
        inputCustomer.setCity("Los Gatos");
        inputCustomer.setState("CA");
        inputCustomer.setPostalCode("11372");
        inputCustomer.setCountry("US");

        String inputJson = mapper.writeValueAsString(inputCustomer);

        mockMvc.perform(
                        post("/customers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());   // Created Status should be 201;
    }

    @Test
    public void shouldUpdateCustomerByIdAndReturnStatusCode204() throws Exception {
        Customer inputCustomer = new Customer();

        inputCustomer.setCustomerId(1);
        inputCustomer.setFirstName("Peter");
        inputCustomer.setLastName("Parker");
        inputCustomer.setEmail("spiderman@gmail.com");
        inputCustomer.setCompany("Daily Bugle");
        inputCustomer.setPhone("123-456-7890");
        inputCustomer.setAddress1("123 Continental Avenue");
        inputCustomer.setAddress2("Apt 2B");
        inputCustomer.setCity("Queens");
        inputCustomer.setState("New York");
        inputCustomer.setPostalCode("11375");
        inputCustomer.setCountry("US");

        String inputJson = mapper.writeValueAsString(inputCustomer);
        mockMvc.perform(
                        put("/customers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteCustomerByIdAndReturnStatusCode204() throws Exception {
        mockMvc.perform(
                        delete("/customers/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFindCustomerByIdAndStatusCode200() throws Exception {
        Customer outputCustomer = new Customer();
        outputCustomer.setFirstName("Harry");
        outputCustomer.setLastName("Osborne");
        outputCustomer.setEmail("harry@gmail.com");
        outputCustomer.setCompany("OsCorp");
        outputCustomer.setPhone("111-222-3333");
        outputCustomer.setAddress1("345 Park Avenue");
        outputCustomer.setAddress2("Penthouse");
        outputCustomer.setCity("New York City");
        outputCustomer.setState("NY");
        outputCustomer.setPostalCode("10002");
        outputCustomer.setCountry("US");

        Customer c1 = customerRepository.save(outputCustomer);

        String outputJson = mapper.writeValueAsString(outputCustomer);

        mockMvc.perform(get("/customers/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Custom test
    @Test
    public void shouldFindCustomerByState() throws Exception {
        Customer outputCustomer = new Customer();
        outputCustomer.setFirstName("Mary");
        outputCustomer.setLastName("Jane");
        outputCustomer.setEmail("maryjane@gmail.com");
        outputCustomer.setCompany("LES Diner");
        outputCustomer.setPhone("123-456-7890");
        outputCustomer.setAddress1("123 Mulberry Street");
        outputCustomer.setAddress2("Apt 2B");
        outputCustomer.setCity("New York City");
        outputCustomer.setState("FL");
        outputCustomer.setPostalCode("12345");
        outputCustomer.setCountry("US");

        customerList.add(outputCustomer);

        String outputJson = mapper.writeValueAsString(customerList);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/state/FL"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}