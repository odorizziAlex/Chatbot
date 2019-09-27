package com.company.utils;

import java.util.ArrayList;
import java.util.HashMap;

public final class Config {

    /*
    This is the file, that contains all constants and configuration values.
     */

    public static final int COMPONENT_NUMBER = 6;
    public static final String EMPTY_POSITION = "E";
    public static final String EMPTY_STRING = "";
    public static final String GREETING = "Hey there! \uD83D\uDC4B Good to see you here. I'm Clofy.\nI'm here to help you find the best clothes that suit you and the specific occasion.\nWhat are you looking for?";
    public static final String NO_KEYWORDS_FOUND = "Sorry!\nI couldn't make out any informations about your demand.\nCould you tell me, which type of clothing you're looking for?";
    public static final String COMPONENTS_READY = "Alright, I know anything I need to know.";
    public static final String IS_PRICE_REGEX = "[\\d]+([€$]|euro|euros|dollar|bucks|usd|e)";

    // All demand components
    public static final int DEMAND_ITEM = 0;
    public static final int DEMAND_SIZE = 1;
    public static final int DEMAND_COLOR = 2;
    public static final int DEMAND_PRICE = 3;
    public static final int DEMAND_FABRIC = 4;
    public static final int DEMAND_GENDER = 5;
    public static final HashMap<String,Integer> DEMAND_KEYS = new HashMap<>() {{ put("item",DEMAND_ITEM); put("size",DEMAND_SIZE); put("color",DEMAND_COLOR); put("price",DEMAND_PRICE); put("fabric",DEMAND_FABRIC); put("gender",DEMAND_GENDER); }};


    // JSON demand keys
    public static final String JSON_DEMAND_NEGATE_KEY = "negate";
    public static final String JSON_DEMAND_GENDER_KEY = "gender";
    public static final String JSON_DEMAND_UPPER_BODY_ITEM_KEY = "upper_body_item";
    public static final String JSON_DEMAND_LOWER_BODY_ITEM_KEY = "lower_body_item";
    public static final String JSON_DEMAND_FOOTWEAR_KEY = "footwear_item";
    public static final String JSON_DEMAND_COLOR_KEY = "color";
    public static final String JSON_DEMAND_SIZE_KEY = "clothing_size";
    public static final String JSON_DEMAND_FABRIC_KEY = "fabric";
    public static final String JSON_DEMAND_PRICE_KEY = "price";

    // JSON demand standard response keys
    public static final String JSON_ST_RES_Q_GENDER_KEY = "gender";
    public static final String JSON_ST_RES_Q_ITEM_KEY = "item";
    public static final String JSON_ST_RES_Q_COLOR_KEY = "color";
    public static final String JSON_ST_RES_Q_SIZE_KEY = "size";
    public static final String JSON_ST_RES_Q_FABRIC_KEY = "fabric";
    public static final HashMap<Integer,String> RESPONSE_KEYS = new HashMap<>() {{ put(DEMAND_ITEM,"item"); put(DEMAND_SIZE,"size"); put(DEMAND_COLOR,"color"); put(DEMAND_PRICE,"price"); put(DEMAND_FABRIC,"fabric"); put(DEMAND_GENDER,"gender"); }};






}
