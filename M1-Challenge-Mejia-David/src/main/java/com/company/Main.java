package com.company;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );


    public static void main(String[] args) {
        //Create Customer accounts for all clients;

        List<Customer> customers = new ArrayList<>();
        List<String> IDs = new ArrayList<>();
        List<String[]> customerTransactionDetails = new ArrayList<>();
        AccountRecord accountRecord;

        //Iterate through the customerData list to create list of customers.
        //Check IDs list to check if customer with that specific ID already exists.
        //If ID does not exist in list, new customer is created and added to Customer object list.
        for (String[] data : customerData){
            if (!IDs.contains(data[0])){
                IDs.add(data[0]);
                Customer customer = new Customer();
                customer.setName(data[1]);
                customer.setId(Integer.parseInt(data[0]));
                customers.add(customer);
            }
        }

        //Iterate through the customerData list and add charges to each existing
        //customer via their accountRecord.
        for (Customer customer : customers){
            customerTransactionDetails = customerData.stream()
                    .filter(data -> data[0].equals(String.valueOf(customer.getId())))
                    .collect(Collectors.toList());

            for (String[] data : customerTransactionDetails) {
                accountRecord = new AccountRecord();
                accountRecord.setCharge(Integer.parseInt(data[2]));
                accountRecord.setChargeDate(data[3]);
                customer.getCharges().add(accountRecord);
            }
        }

        //Create and populate the positiveAccounts list and negativeAccounts list;
        List<Customer> positiveAccounts = customers.stream()
                .filter(customer -> customer.getBalance() >= 0)
                .collect(Collectors.toList());

        List<Customer> negativeAccounts = customers.stream()
                .filter(customer -> customer.getBalance() < 0)
                .collect(Collectors.toList());

        //Print list of customers based on positive or negative balance;
        System.out.println("Positive accounts:");
        positiveAccounts.forEach(System.out::println);
        System.out.println("\n");
        System.out.println("Negative accounts:");
        negativeAccounts.forEach(System.out::println);
    }
}

