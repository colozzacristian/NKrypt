package com.example.Threads;

import java.net.HttpURLConnection;
import java.net.URL;

import com.example.CryptoList;

//si lo so il nome non è il massimo ma mi faceva ridere
public class CallerCaller extends Thread {
     
    CryptoList cryptoList;
    boolean isAlive=true;
    //API KEY in URL - just append ? or &api_key={your_api_key} the the end of your request url
    @Override
    public void run() {


        try {
            while(isAlive){
                sleep(120000);
                cryptoList.getCall2Action().release();
                
            }
            
        } catch (Exception e) {
            isAlive=false;
        }
        
    
    }

    public CallerCaller(CryptoList list){
        this.cryptoList=list;
    }


    public CryptoList getCryptoList() {
        return cryptoList;
    }

    public void setCryptoList(CryptoList cryptoList) {
        this.cryptoList = cryptoList;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    
    
}
