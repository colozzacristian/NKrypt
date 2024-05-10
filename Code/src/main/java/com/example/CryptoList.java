/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.concurrent.Semaphore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author giuseppe.depietro
 */
public class CryptoList {

    private Semaphore call2Action = new Semaphore(1);
    private ObservableList<Crypto> cryptos = FXCollections.observableArrayList(); 
    private Double balance;

    public CryptoList() {
        cryptos.add(new Crypto("btc", 0, 0));
        cryptos.add(new Crypto("eth", 0, 0));
        cryptos.add(new Crypto("sol", 0, 0));
        cryptos.add(new Crypto("etc", 0, 0));
        cryptos.add(new Crypto("usdt", 0, 0));
        cryptos.add(new Crypto("usdc", 0, 0));
        cryptos.add(new Crypto("doge", 0, 0));
        cryptos.add(new Crypto("shib", 0, 0));
        cryptos.add(new Crypto("pepe", 0, 0));
        balance=50.0;

    }
    
     public ObservableList<Crypto> getCryptoList() {
         return cryptos;
    }

    public Semaphore getCall2Action() {
        return call2Action;
    }

    public void setCall2Action(Semaphore call2Action) {
        this.call2Action = call2Action;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    
    
    
    
    
    
}
