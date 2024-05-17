package com.example.Threads;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;

import com.example.MainUiController;
import com.example.StringParserCC;
import com.example.Crypto.CryptoList;

public class Caller extends Thread{
    private String baseUrl="https://min-api.cryptocompare.com/data/pricemulti?fsyms=";
    private String append2="&tsyms=EUR&api_key=8834b8ab09b3a21eac9fb72abab5a866ec3facab20e17a826a1c8d004cc9338d";
    //we need this to use the semaphore and the list of cryptoes
    private CryptoList cryptoList;
    private MainUiController main;
    private boolean isAlive=true;

    @Override
    public void run() {
        ArrayList<Double> list;

        try {
            while(isAlive){
                
                //waits for it to be called
                cryptoList.getCall2Action().acquire();
                //using the class parses the response to build a list of ordered prices
                list = new ArrayList<Double>(
                    StringParserCC.retrieveValues(request())
                );
                if(list!=null){
                    for (int i = 0; i < list.size(); i++) {
                        cryptoList.getCryptoList().get(i).setPrice(list.get(i));
                        
                    }
                    //refresh the tableview
                    main.refreshTable();
                }
                System.out.println("list contents:" + list);
            }
            
        } catch (InterruptedException e) {
            //on interrupt kill the thread
            isAlive=false;
        }catch(Exception e){
            //if unkown errors are encountered(pritns to the terminal to make it more evident to us)
            e.printStackTrace();
        }

        System.out.println("caller has stopped running");
        
    }

    public Caller(CryptoList list){
        this.cryptoList=list;
    }

    //it builds the url using the cryptocurrencies id in the list
    private String buildURL(){
        String url=baseUrl;
        // this loop concatenates the ids to the alredy existing url
        for (int i = 0; i < cryptoList.getCryptoList().size(); i++) {
            if(i!=0){
                url+=",";
            }
            url+=(cryptoList.getCryptoList().get(i).getName().toUpperCase());
        }
        //ads the last bit required by the api
        url+=append2;
        return url;
            
        
        
    }

    //this makes the request to the api provider
    private String request(){
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(buildURL()))
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build() ;

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("connected");
            //tells the maincontroller that there is a connection
            main.connected();
            System.out.println("response: "+response.body());
            return response.body();
        } catch (ConnectException e) {
            // in case that there is no connection
            System.out.println("not connected");
            main.noConnection();
        }catch(InterruptedException e){
            // kills the thread on interrupts
            System.out.println("Interrupdted caller");
            isAlive=false;
        }catch(Exception e){
            //self explanatory 
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
