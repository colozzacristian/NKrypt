package com.example.Threads;

import java.net.HttpURLConnection;
import java.net.URL;

import com.example.CryptoList;

public class Caller extends Thread{
    String baseUrl="https://min-api.cryptocompare.com/data/pricemulti?fsyms=";
    String append2="&tsyms=EUR&api_key=8834b8ab09b3a21eac9fb72abab5a866ec3facab20e17a826a1c8d004cc9338d";
    CryptoList cryptoList;
    boolean isAlive=true;
    //API KEY in URL - just append ? or &api_key={your_api_key} the the end of your request url
    @Override
    public void run() {


        try {
            while(isAlive){
                cryptoList.getCall2Action().acquire();
                System.out.println(buildURL());
                
            }
            
        } catch (Exception e) {
            // TODO: handle exception
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
        try {
            URL url = new URL("http://example.com");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (Exception e) {
            System.out.println("error in the request");
        }
        
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
