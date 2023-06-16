package com.company;

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

        //Construct's WayneEnterprises accounts
        Customer wayneEnterprises = new Customer();
        AccountRecord wayneEnterprisesAccountRecord = new AccountRecord();
        List<String[]> wEnterprisesArray = new ArrayList<>();

        //Populate wEnterprisesArray to contain all strings[] with wayneEnterprises account data.
        wEnterprisesArray = customerData.stream()
                .filter(data -> data[0].equals("1"))
                .collect(Collectors.toList());

        for (String[] data : wEnterprisesArray){
            wayneEnterprisesAccountRecord = new AccountRecord();
            wayneEnterprisesAccountRecord.setCharge(Integer.parseInt(data[2]));
            wayneEnterprisesAccountRecord.setChargeDate(data[3]);
            wayneEnterprises.getCharges().add(wayneEnterprisesAccountRecord);
            wayneEnterprises.setId(Integer.parseInt(data[0]));
            wayneEnterprises.setName(data[1]);
        }   //---End of WayneEnterprises Account construction and data population;

        //Construct's Daily Planet's account
        Customer dailyPlanet = new Customer();
        AccountRecord dailyPlanetAccountRecord = new AccountRecord(); //May throw error since "account" is duplicate of the wayne account
        List<String[]> dPlanetArray;

        //Populate dPlanetArray to contain all strings[] with dailyPlanets account data
        dPlanetArray = customerData.stream()
                .filter(data -> data[0].equals("2"))
                .collect(Collectors.toList());
        for (String[] data: dPlanetArray) {
            dailyPlanetAccountRecord = new AccountRecord();
            dailyPlanetAccountRecord.setCharge(Integer.parseInt(data[2]));
            dailyPlanetAccountRecord.setChargeDate(data[3]);
            dailyPlanet.getCharges().add(dailyPlanetAccountRecord);
            dailyPlanet.setId(Integer.parseInt(data[0]));
            dailyPlanet.setName(data[1]);
        }   //---End of Daily Planet's account construction and data population;


        //Construct's Ace Chemical's account
        Customer aceChemical = new Customer();
        AccountRecord aceChemicalAccountRecord = new AccountRecord();
        List<String[]> aceChemicalArray = new ArrayList<>();

        //Populate aceChemicalArray to contain all strings[] with aceChemical's account data
        aceChemicalArray = customerData.stream()
                .filter(data -> data[0].equals("3"))
                .collect(Collectors.toList());

        for (String[] data: aceChemicalArray){
            aceChemicalAccountRecord = new AccountRecord();
            aceChemicalAccountRecord.setCharge(Integer.parseInt(data[2]));
            aceChemicalAccountRecord.setChargeDate(data[3]);
            aceChemical.getCharges().add(aceChemicalAccountRecord);
            aceChemical.setId(Integer.parseInt(data[0]));
            aceChemical.setName(data[1]);
        }   //---End of Ace Chemicals account construction and data population;


        //Create list<Customer> to hold one copy of each customer;
        List<Customer> clients = new ArrayList<Customer>();
        clients.add(wayneEnterprises);
        clients.add(aceChemical);
        clients.add(dailyPlanet);

        //Create and populate the positiveAccounts list and negativeAccounts list;
        List<Customer> positiveAccounts = clients.stream()
                .filter(client -> client.getBalance() >= 0)
                .collect(Collectors.toList());

        List<Customer> negativeAccounts = clients.stream()
                .filter(client -> client.getBalance() < 0)
                .collect(Collectors.toList());

        //Print list of customers based on positive or negative balance;
        System.out.println("Positive accounts:");  //Prints all customers with a positive balance;
        positiveAccounts.forEach(System.out::println);
        System.out.println("\n");

        System.out.println("Negative accounts:");   //Prints all customers with a negative balance;
        negativeAccounts.forEach(System.out::println);

        System.out.println("\n");

        System.out.println("CUSTOMER LIST BELOW:");
        clients.forEach(System.out::println);
    }
}
