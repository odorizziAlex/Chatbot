package com.company.Response;


import com.company.Tools.JSONHandler;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;

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
        response.append(buildFirstSentence(data));
        response.append(buildSecondSentence(data));
        return response.toString();
    }

    /*
     * The first sentence of the response handles appearances of items, colors, fabrics, and gender.
     * The needed snippets for every part of the sentence will be read from the json file.
     */
    private String buildFirstSentence(ArrayList<String> data){
        String responseComponent = "";

        if(isItemAndNoColorGiven(data)){
            responseComponent += jsonHandler.getResponse(JSON_ST_RES_Q_ITEM_KEY,data.get(DEMAND_ITEM));
        } else if(isNoItemAndColorGiven(data)){
            responseComponent += jsonHandler.getResponse(JSON_ST_RES_Q_COLOR_KEY,"only")+" "+data.get(DEMAND_COLOR)+" item";
        } else if(isItemAndColorGiven(data)){
            responseComponent += jsonHandler.getResponse(JSON_ST_RES_Q_ITEM_KEY,data.get(DEMAND_ITEM)) + jsonHandler.getResponse(JSON_ST_RES_Q_COLOR_KEY,data.get(DEMAND_COLOR));
        }



        if(isGenderAndItemAndOrColorGiven(data)){
            responseComponent += jsonHandler.getResponse(JSON_ST_RES_Q_GENDER_KEY,data.get(DEMAND_GENDER));
        } else if(isGenderAndNotItemGiven(data)){
            responseComponent += " you need something"+jsonHandler.getResponse(JSON_ST_RES_Q_GENDER_KEY,data.get(DEMAND_GENDER));
        }

        if(isFabricsGiven(data)){
            responseComponent += jsonHandler.getResponse(JSON_ST_RES_Q_FABRIC_KEY,data.get(DEMAND_FABRIC));
        } else if(isOnlyFabricsGiven(data)){
            responseComponent += " you need something"+jsonHandler.getResponse(JSON_ST_RES_Q_FABRIC_KEY,data.get(DEMAND_FABRIC));
        }

        return responseComponent;
    }


    /*
     * The following boolean methods are for the building of the first sentence.
     * They basically check which components are given in the data array and which aren't.
     * Based on those methods, the first sentence of the response will be built.
     */
    private boolean isItemAndNoColorGiven(ArrayList<String> data){
        // item given and color empty
        // append: you need a new "item"
        return !data.get(DEMAND_ITEM).equals(EMPTY_POSITION)
                && data.get(DEMAND_COLOR).equals(EMPTY_POSITION);
    }

    private boolean isNoItemAndColorGiven(ArrayList<String> data){
        // item empty and color given
        // append: you need a "color" item
        return data.get(DEMAND_ITEM).equals(EMPTY_POSITION)
                && !data.get(DEMAND_COLOR).equals(EMPTY_POSITION);
    }

    private boolean isItemAndColorGiven(ArrayList<String> data){
        // item given and color given
        // append: you need a "color" "item"
        return !data.get(DEMAND_ITEM).equals(EMPTY_POSITION)
                && !data.get(DEMAND_COLOR).equals(EMPTY_POSITION);
    }

    private boolean isFabricsGiven(ArrayList<String> data){
        // fabric given
        // append: made of "fabric"
        return !data.get(DEMAND_FABRIC).equals(EMPTY_POSITION)
                && (!data.get(DEMAND_GENDER).equals(EMPTY_POSITION)
                || !data.get(DEMAND_COLOR).equals(EMPTY_POSITION)
                || !data.get(DEMAND_ITEM).equals(EMPTY_POSITION));
    }

    private boolean isOnlyFabricsGiven(ArrayList<String> data){
        // fabrics given
        // append: need something made of "fabric"
        return !data.get(DEMAND_FABRIC).equals(EMPTY_POSITION)
                && data.get(DEMAND_GENDER).equals(EMPTY_POSITION)
                && data.get(DEMAND_COLOR).equals(EMPTY_POSITION)
                && data.get(DEMAND_ITEM).equals(EMPTY_POSITION);
    }

    private boolean isGenderAndItemAndOrColorGiven(ArrayList<String> data){
        // gender given and item or/and color given
        // append: for "gender"
        return !data.get(DEMAND_GENDER).equals(EMPTY_POSITION)
                && (!data.get(DEMAND_ITEM).equals(EMPTY_POSITION)
                    || !data.get(DEMAND_COLOR).equals(EMPTY_POSITION));
    }

    private boolean isGenderAndNotItemGiven(ArrayList<String> data){
        // gender given and item empty
        // append: you need something for "gender"
        return !data.get(DEMAND_GENDER).equals(EMPTY_POSITION)
                && data.get(DEMAND_ITEM).equals(EMPTY_POSITION);
    }

    /*
     * The preciously defined rules, also apply to the following methods, which are the building of
     * the second response sentence and it's boolean methods.
     */
    private String buildSecondSentence(ArrayList<String> data){
        String responseComponent = "";
        if(isSizeGiven(data)){
            // size is numeric (shoes and pants)
            // append: It should be size "size"
            if(isSizeNumericValue(data)){
                responseComponent += ". It should be size "+data.get(DEMAND_SIZE);

                // size not numeric (jacket, shirt...)
                // append: Size should be "size"
            }else{
                responseComponent += jsonHandler.getResponse(JSON_ST_RES_Q_SIZE_KEY,data.get(DEMAND_SIZE));
            }
        }

        if(isPriceAndNothingElseGiven(data)){
            responseComponent += " you need something, that should cost around "+data.get(DEMAND_PRICE)+"s";

            // price and something else is given
        }else if(isPriceGiven(data)){
            // price is numeric
            // append: and it should cost around "price"
            if(isPriceNumeric(data)){
                responseComponent += " and it should cost around "+data.get(DEMAND_PRICE)+"s";
            }
        }
        return responseComponent;
    }

    /*
     * Boolean methods for the second sentence.
     */
    private boolean isSizeGiven(ArrayList<String> data){
        // if size is given = true
        return !data.get(DEMAND_SIZE).equals(EMPTY_POSITION);
    }

    private boolean isSizeNumericValue(ArrayList<String> data){
        return Pattern.matches(isNumberRegEx, data.get(DEMAND_SIZE));
    }

    private boolean isPriceAndNothingElseGiven(ArrayList<String> data){
        // nothing but price is given
        // append: you need something, that should cost around "price"
        return !data.get(DEMAND_PRICE).equals(EMPTY_POSITION) && data.get(DEMAND_GENDER).equals(EMPTY_POSITION)
                && data.get(DEMAND_ITEM).equals(EMPTY_POSITION)
                && data.get(DEMAND_COLOR).equals(EMPTY_POSITION)
                && data.get(DEMAND_SIZE).equals(EMPTY_POSITION);
    }

    private boolean isPriceGiven(ArrayList<String> data){
        // price is given
        return !data.get(DEMAND_PRICE).isEmpty();
    }

    private boolean isPriceNumeric(ArrayList<String> data){
        return Pattern.matches(isNumberRegEx,data.get(DEMAND_PRICE));
    }
}
