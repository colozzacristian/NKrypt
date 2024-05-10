package com.example;

import java.util.ArrayList;

public class StringParserCC {
    public static ArrayList<Double> retrieveValues(String response){
        ArrayList<Double> prices = new ArrayList<Double>();
        int var1=0;
        String aux="";

        System.out.println("response being parsed: "+ response);

        if(response==null){
            System.out.println("response is null, probable cause: no connection");
            return prices;
        }
        else{
            System.out.println("parsing");
            for (int index = 0; index < response.length(); index++) {
                char character= response.charAt(index);
                if(var1==2){
                    if("0123456789.".contains(String.valueOf(character))){
                        aux+=character;
                    }else{
                        var1=0;
                        prices.add(Double.parseDouble(aux));
                        aux="";
                    }
                }
                else{
                if(character==':'){
                    var1+=1;
                }
            }
        }

        return prices;

        }
    }


    public static String toNum(String text){
        System.out.println("Correcting output: "+text);
        String aux="";
        boolean start=false;
        boolean floated=false;
        for (int index = 0; index < text.length(); index++) {
            char character= text.charAt(index);
                if(character==',' && start && !floated){
                    floated=true;
                    aux+='.';
                }else if("0123456789".contains(String.valueOf(character))){
                    aux+=character;
                    start=true;
                }else if(character=='.' && start && !floated){
                    aux+=character;
                    floated=true;
                }
       

    }
    System.out.println(aux);
    return aux;
}

}
