package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    Customer customer; //Autowiring, passes null if not instantiated;
    Customer customer2;
    Customer customer3;
    Customer customer4;
    AccountRecord accountRecord;
    AccountRecord accountRecord2;
    AccountRecord accountRecord3;
    AccountRecord accountRecord4;



    @BeforeEach
    public void setUp() {
        customer = new Customer();  //Tests adding strictly positive integers
        customer2 = new Customer(); //Tests adding strictly negative integers
        customer3 = new Customer(); //Tests adding values that add up to 0
        customer4 = new Customer(); //Tests adding positive and negative integers`

        customer.setId(1);
        customer.setName("Customer1");


        customer2.setId(2);
        customer2.setName("Customer2");

        customer3.setId(3);
        customer3.setName("Customer3");

        customer4.setId(4);
        customer4.setName("Customer4");


        //Customer1
        for(int i =0; i < 5; i++) {
            accountRecord = new AccountRecord();
            accountRecord.setCharge(1000);
            customer.getCharges().add(accountRecord);
        }

        //Customer2
        for(int i = 0; i < 3; i++){
            accountRecord = new AccountRecord();
            accountRecord.setCharge(-1000);
            customer2.getCharges().add(accountRecord);
        }

        //Customer3
        for(int i = 0; i < 4; i++){
            accountRecord = new AccountRecord();
            accountRecord.setCharge(0);
            customer3.getCharges().add(accountRecord);
        }

        //Customer4
        for(int i = 0; i < 3; i++){
            accountRecord = new AccountRecord();
            accountRecord2 = new AccountRecord();
            accountRecord.setCharge(200);
            accountRecord2.setCharge(-100);
            customer4.getCharges().add(accountRecord);
            customer4.getCharges().add(accountRecord2);
        }
    }

    @Test
    void shouldGetBalance() {
        assertEquals(5000, customer.getBalance());
        assertEquals(-3000, customer2.getBalance());
        assertEquals(0, customer3.getBalance());
        assertEquals(300, customer4.getBalance());
    }

    @Test
    void shouldToString() {
        assertEquals(
                ((String.valueOf(customer.getId()) + " " + String.valueOf(customer.getName()) + " " + String.valueOf(customer.getBalance()))),
                customer.toString()
        );
    }
}
