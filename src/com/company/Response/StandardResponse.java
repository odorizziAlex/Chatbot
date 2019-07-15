package com.company.Response;


import com.company.Tools.JSONHandler;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.company.utils.Config.*;


public class StandardResponse {

    private static JSONHandler jsonHandler = new JSONHandler();
    private String isNumberRegEx = ".*\\d.*";

    public String generateStandardResponse(ArrayList<String> data){

        StringBuilder standardResponse = new StringBuilder();

        boolean isEmpty = true;
        for(int i = 0; i < data.size(); i++) {
            if(!data.get(i).equals(EMPTY_POSITION)) isEmpty = false;
        }
        // If no item has been found
        if(isEmpty) return NO_KEYWORDS_FOUND;


        // append: random phrase starter (Ok, Alright...)
        standardResponse.append(jsonHandler.getStarter());
        // build phrase based on given information
        buildStandardAnswer(data, standardResponse);
        // append: "."
        standardResponse.append(".");

        return standardResponse.toString();
    }

    private String buildStandardAnswer(ArrayList<String> data, StringBuilder response){

        // item not empty and color empty
        // append: you need a new "item"
        if(!data.get(DEMAND_ITEM).equals(EMPTY_POSITION) && data.get(DEMAND_COLOR).equals(EMPTY_POSITION)){
            response.append(jsonHandler.getResponse(JSON_ST_RES_Q_ITEM_KEY,data.get(DEMAND_ITEM)));

        // item empty and color not empty
        // append: you need a "color" item
        } else if(data.get(DEMAND_ITEM).equals(EMPTY_POSITION) && !data.get(DEMAND_COLOR).equals(EMPTY_POSITION)){
            response.append(jsonHandler.getResponse(JSON_ST_RES_Q_COLOR_KEY,"only")+" "+data.get(DEMAND_COLOR)+" item");

        // item empty and color not empty
        // append: you need a "color" "item"
        } else if(!data.get(DEMAND_ITEM).equals(EMPTY_POSITION) && !data.get(DEMAND_COLOR).equals(EMPTY_POSITION)){
            response.append(jsonHandler.getResponse(JSON_ST_RES_Q_ITEM_KEY,data.get(DEMAND_ITEM)));
            response.append(jsonHandler.getResponse(JSON_ST_RES_Q_COLOR_KEY,data.get(DEMAND_COLOR)));
        }

        // fabric not empty
        // append: made of "fabric"
        if(!data.get(DEMAND_FABRIC).equals(EMPTY_POSITION)){
            response.append(jsonHandler.getResponse(JSON_ST_RES_Q_FABRIC_KEY,data.get(DEMAND_FABRIC)));
        }

        // gender not empty and item or/and color not empty
        // append: for "gender"
        if(!data.get(DEMAND_GENDER).equals(EMPTY_POSITION) && (!data.get(DEMAND_ITEM).equals(EMPTY_POSITION)||!data.get(DEMAND_COLOR).equals(EMPTY_POSITION))){
            response.append(jsonHandler.getResponse(JSON_ST_RES_Q_GENDER_KEY,data.get(DEMAND_GENDER)));

        // gender not empty and item empty
        // append: you need something for "gender"
        } else if(!data.get(DEMAND_GENDER).equals(EMPTY_POSITION) && data.get(DEMAND_ITEM).equals(EMPTY_POSITION)){
            response.append(" you need something"+jsonHandler.getResponse(JSON_ST_RES_Q_GENDER_KEY,data.get(DEMAND_GENDER)));
        }

        // size not empty
        if(!data.get(DEMAND_SIZE).equals(EMPTY_POSITION)){

            // size is numeric (shoes and pants)
            // append: It should be size "size"
            if(Pattern.matches(isNumberRegEx, data.get(DEMAND_SIZE))){
                response.append(". It should be size "+data.get(DEMAND_SIZE));

            // size not numeric (jacket, shirt...)
            // append: Size should be "size"
            }else{
                response.append(jsonHandler.getResponse(JSON_ST_RES_Q_SIZE_KEY,data.get(DEMAND_SIZE)));
            }
        }

        // everything but price is empty
        // append: you need something, that should cost around "price"
        if(!data.get(DEMAND_PRICE).equals(EMPTY_POSITION) && data.get(DEMAND_GENDER).equals(EMPTY_POSITION)
                && data.get(DEMAND_ITEM).equals(EMPTY_POSITION)
                && data.get(DEMAND_COLOR).equals(EMPTY_POSITION)
                && data.get(DEMAND_SIZE).equals(EMPTY_POSITION)){
            response.append(" you need something, that should cost around "+data.get(DEMAND_PRICE)+"s");

        // price and something else is not empty
        }else if(!data.get(DEMAND_PRICE).isEmpty()){
            // price is numeric
            // append: and it should cost around "price"
            if(Pattern.matches(isNumberRegEx,data.get(DEMAND_PRICE))){
                response.append(" and it should cost around "+data.get(DEMAND_PRICE)+"s");
            }
        }

        return response.toString();
    }
}
