package com.example.Threads;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;

import com.example.CryptoList;
import com.example.MainUiController;
import com.example.StringParserCC;

public class Caller extends Thread{
    String baseUrl="https://min-api.cryptocompare.com/data/pricemulti?fsyms=";
    String append2="&tsyms=EUR&api_key=ciao";
    CryptoList cryptoList;
    MainUiController main;
    boolean isAlive=true;

    @Override
    public void run() {
        ArrayList<Double> list;

        try {
            while(isAlive){
                
                cryptoList.getCall2Action().acquire();
                //System.out.println(buildURL());
                list = new ArrayList<Double>(
                    StringParserCC.retrieveValues(request())
                );
                if(list!=null){
                    for (int i = 0; i < list.size(); i++) {
                        cryptoList.getCryptoList().get(i).setPrice(list.get(i));
                        
                    }
                    main.refreshTable();
                }
                System.out.println("list contents:" + list);
            }
            
        } catch (InterruptedException e) {
            isAlive=false;
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("caller has stopped running");
        
    
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

    private String request(){
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(buildURL()))
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build() ;

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("connected");
            main.connected();
            System.out.println("response: "+response.body());
            return response.body();
        } catch (ConnectException e) {
            System.out.println("not connected");
            main.noConnection();
        }catch(InterruptedException e){
            System.out.println("Interrupdted caller");
            isAlive=false;
        }catch(Exception e){
            System.out.println("unknown error during request");
            e.printStackTrace();
        }
        return null;
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

    public MainUiController getMain() {
        return main;
    }

    public void setMain(MainUiController main) {
        this.main = main;
    }

    
    
}
