package com.example;

import java.util.ArrayList;

// the nane is this to avoid any confusion with alredy existing packages
// the functions are static so that i can call them without istanciating any object
public abstract class StringParserCC {

    public static ArrayList<Double> retrieveValues(String response){
        /*
         * this function retrieves the prices from the json response of the http request
         */
        ArrayList<Double> prices = new ArrayList<Double>();
        //this is the amount of ":" found since the last price
        int var1=0;
        String aux="";

        if(response==null){
            System.out.println("response is null, probable cause: no connection");
            return prices;
        }
        else{
            System.out.println("parsing");
            for (int index = 0; index < response.length(); index++) {
                char character= response.charAt(index);
                // looking at the response, every two columns(i due punti), there is price
                if(var1==2){
                    //if it is a number, or the floating point, add it to the string
                    //we'll later trasform the string into a double
                    if("0123456789.".contains(String.valueOf(character))){
                        aux+=character;
                    }else{
                        //if the char is not in the above contition, then the price has ended
                        //we need to save this one, and move on to the next one
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
        //System.out.println("Correcting input: "+text);

         /*
         * this function makes the strings suitable to be casted into doubles or float
         */

         //all the legal characters will be put in this String
        String aux="";
        //floating points can be added only after a number
        boolean start=false;
        //check if the number has alredy a floating point
        boolean Doubleed=false;

        if(text==null){return "";}

        
        for (int index = 0; index < text.length(); index++) {
            char character= text.charAt(index);
            // if there is a "," and thre isn't alredy a floating point, then autocorrect the "," to a "."
                if(character==',' && start && !Doubleed){
                    Doubleed=true;
                    aux+='.';
                }else if("0123456789".contains(String.valueOf(character))){
                    aux+=character;
                    start=true;
                }else if(character=='.' && start && !Doubleed){
                    aux+=character;
                    Doubleed=true;
                }
       

    }
    //System.out.println("Corrected: "+aux);
    return aux;
}

}
