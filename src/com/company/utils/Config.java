package com.company.utils;

import java.util.ArrayList;

public final class Config {

    /*
    This is the file, that contains all constants and configuration values.
     */

    public static final int COMPONENT_NUMBER = 6;

    // All demand components
    public static final int DEMAND_GENDER = 0;
    public static final int DEMAND_ITEM = 1;
    public static final int DEMAND_COLOR = 2;
    public static final int DEMAND_SIZE = 3;
    public static final int DEMAND_PRICE = 4;
    public static final int DEMAND_FABRIC = 5;

    // JSON demand keys
    public static final String JSON_DEMAND_GENDER_KEY = "gender";
    public static final String JSON_DEMAND_UPPER_BODY_ITEM_KEY = "upper_body_item";
    public static final String JSON_DEMAND_LOWER_BODY_ITEM_KEY = "lower_body_item";
    public static final String JSON_DEMAND_FOOTWEAR_KEY = "footwear_item";
    public static final String JSON_DEMAND_COLOR_KEY = "color";
    public static final String JSON_DEMAND_SIZE_KEY = "clothing_size";
    public static final String JSON_DEMAND_FABRIC_KEY = "fabric";

    // JSON response types
    public static final String JSON_STANDARD_RESPONSE_KEY = "standard";
    // JSON demand standard response keys
    public static final String JSON_ST_RES_GENDER_KEY = "gender";
    public static final String JSON_ST_RES_ITEM_KEY = "item";
    public static final String JSON_ST_RES_COLOR_KEY = "color";
    public static final String JSON_ST_RES_SIZE_KEY = "size";
    public static final String JSON_ST_RES_NUMERIC_SIZE_KEY = "numeric_size";
    public static final String JSON_ST_RES_PRICE_KEY = "price";
    public static final String JSON_ST_RES_FABRIC_KEY = "fabric";


}
