package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getBalance() {
        //update this
        //Returns the sum of all charges for a customer
        int balance = 0;
        for (int i = 0; i < charges.size(); i++){
            balance = balance + charges.get(i).getCharge();
        }
        return balance;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        //update this
        //Return the ID, Name, and Balance of a customer, in String primitive type
        return String.valueOf(id) + " " + name + " " + String.valueOf(this.getBalance());
    }
}
