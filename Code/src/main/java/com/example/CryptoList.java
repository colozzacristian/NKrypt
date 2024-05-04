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

    public CryptoList() {
        cryptos.add(new Crypto("btc", 62000, 0.00001));
        cryptos.add(new Crypto("eth", 1000, 0.002));
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
    
    
    
    
    
}
