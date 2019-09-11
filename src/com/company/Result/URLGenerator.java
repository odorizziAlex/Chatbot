package com.company.Result;

import com.company.Tools.JSONHandler;
import com.company.utils.Config;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.company.utils.Config.*;

public class URLGenerator {

    /**
     * This url generator specifically works for the zalando.de website.
     */

    private JSONHandler jsonHandler = new JSONHandler();
    private StringBuilder url = new StringBuilder("https://en.zalando.de/");

    public String buildURL(ArrayList<String> components){
        if(!components.get(DEMAND_GENDER).equals(EMPTY_STRING)
                && !components.get(DEMAND_ITEM).equals(EMPTY_STRING)){
            url.append(jsonHandler.getGenderAndItemSnippet(components.get(DEMAND_GENDER), components.get(DEMAND_ITEM)));
        }
        url.append(jsonHandler.getColorSnippet(components.get(DEMAND_COLOR)));
        if(Pattern.matches("[\\d]+", components.get(DEMAND_SIZE))){
            url.append(jsonHandler.getSizeSnippet("numeric")+components.get(DEMAND_SIZE));
        }else{
            url.append(jsonHandler.getSizeSnippet(components.get(DEMAND_SIZE)));
        }
        String price = components.get(DEMAND_PRICE).replaceAll("[^\\d]","");
        url.append(jsonHandler.getPriceSnippet("maxPrice")+price);
        url.append(jsonHandler.getFabricSnippet(components.get(DEMAND_FABRIC)));
        return "\nHere are your results:\n"+url;
    }
}
