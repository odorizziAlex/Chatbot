package com.company.Response;


import com.company.Tools.JSONHandler;
import com.company.Tools.Lemmatizer;

import java.util.ArrayList;
import java.util.Arrays;

import static com.company.utils.Config.*;


public class StandardResponse {

    //JSON: https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library

    private static Lemmatizer lemmatizer = new Lemmatizer();
    private static JSONHandler jsonHandler;

    public String generateStandardResponse(ArrayList<String> data){
        System.out.println("---log: generate standard response:");
        System.out.println(Arrays.toString(data.toArray()));
        //Start: Alright,
        // item, color: so we need a "color" "item"
        // gender: for a "gender".
        // size: It's size is "size"
        // price: it should cost around "price".
        jsonHandler = new JSONHandler();
        StringBuilder response = new StringBuilder();

        if(data.get(DEMAND_ITEM).isEmpty()
                && data.get(DEMAND_COLOR).isEmpty()
                && data.get(DEMAND_GENDER).isEmpty()
                && data.get(DEMAND_SIZE).isEmpty()
                && data.get(DEMAND_PRICE).isEmpty()){
            return "Sorry, I couldn't make out any informations about your demand.\nCould you tell me, which type of clothing you're looking for?";
        }

        response.append(jsonHandler.getRandomStarter());

        /*
        response.append("Alright,");

        if(!data.get(DEMAND_ITEM).isEmpty() && data.get(DEMAND_COLOR).isEmpty()){
            response.append(" so we need a "+data.get(DEMAND_ITEM));
        } else if(data.get(DEMAND_ITEM).isEmpty() && !data.get(DEMAND_COLOR).isEmpty()){
            response.append(" so we need a "+data.get(DEMAND_COLOR)+" item");
        } else if(!data.get(DEMAND_ITEM).isEmpty() && !data.get(DEMAND_COLOR).isEmpty()){
            response.append(" so we need a "+data.get(DEMAND_COLOR)+" "+data.get(DEMAND_ITEM));
        }
        if(!data.get(DEMAND_GENDER).isEmpty()){
            response.append(" for "+data.get(DEMAND_GENDER));
        }
        if(!data.get(DEMAND_SIZE).isEmpty()){
            response.append(". It's size is "+data.get(DEMAND_SIZE));
        }
        if(!data.get(DEMAND_PRICE).isEmpty()){
            response.append(" and it should cost around "+data.get(DEMAND_PRICE));
        }
*/
        response.append(".");

        return response.toString();
    }

    private void lemmatizeData(ArrayList<String> data){

    }


}
