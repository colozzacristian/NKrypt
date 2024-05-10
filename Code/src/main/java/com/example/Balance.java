package com.example;

public class Balance {

    private Double balance;

    public Balance(Double bal) {
        this.balance=bal;
    }

    public Balance(Balance balance) {
        this.balance=balance.getBalance();
    }

    public Double getBalance() {
        return balance;
    }

    
}