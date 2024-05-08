package com.example;

import java.util.ArrayList;

public class StringParserCC {
    public static ArrayList<Double> retrieveValues(String response){
        ArrayList<Double> prices = new ArrayList<Double>();
        int var1=0;
        String aux="";

        for (int index = 0; index < response.length(); index++) {
            char c = response.charAt(index);
            if(var1==2){
                if("0123456789.".contains(String.valueOf(c))){
                    aux+=c;
                }else{
                    var1=0;
                    prices.add(Double.parseDouble(aux));
                    aux="";
                }
            }
            else{
                if(c==':'){
                    var1+=1;
                }
            }
        }

        return prices;


    }
}
