package com.example.Threads;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;

import com.example.CryptoList;

public class Caller extends Thread{
    String baseUrl="https://min-api.cryptocompare.com/data/pricemulti?fsyms=";
    String append2="&tsyms=EUR&api_key=8834b8ab3a21eac9fb72abab5a86ec3facab20e17a826a1c8cc9338d";
    CryptoList cryptoList;
    boolean isAlive=true;

    @Override
    public void run() {


        try {
            while(isAlive){
                cryptoList.getCall2Action().acquire();
                System.out.println(buildURL());
                request();
                
            }
            
        } catch (Exception e) {
            isAlive=false;
        }
        
    
    }

    public Caller(CryptoList list){
        this.cryptoList=list;
    }

    private String buildURL(){
        String url=baseUrl;
        for (int i = 0; i < cryptoList.getCryptoList().size(); i++) {
            if(i!=0){
                url+=",";
            }
            url+=(cryptoList.getCryptoList().get(i).getName().toUpperCase());
        }
        url+=append2;
        return url;
            
        
        
    }

    private void request(){
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(buildURL()))
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build() ;

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(response.body());
       
        
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
