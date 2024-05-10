package com.example.Crypto;

import java.io.Serializable;

public class Balance implements Serializable {

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

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    
}