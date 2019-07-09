package com.company.utils;

import java.util.ArrayList;

public final class Config {

    /*
    This is the file, that contains all constants and configuration values.
     */

    // All demand components
    /*
    public static final String DEMAND_GENDER = "gender";
    public static final String DEMAND_ITEM = "item";
    public static final String DEMAND_COLOR = "color";
    public static final String DEMAND_SIZE = "size";
    public static final String DEMAND_PRICE = "price";*/
    public static final int DEMAND_GENDER = 1;
    public static final int DEMAND_ITEM = 2;
    public static final int DEMAND_COLOR = 3;
    public static final int DEMAND_SIZE = 4;
    public static final int DEMAND_PRICE = 5;

    public static final ArrayList<String> GENDER = new ArrayList<>(){{
        add("female");
        add("male");
    }};

    public static final ArrayList<String> ITEMS = new ArrayList<>(){{
        add("clothes");
        add("jacket");
        add("coat");
        add("hoody");
        add("sweatshirt");
        add("t-shirt");
        add("shirt");
        add("cardigan");
        add("blazer");
        add("suit");
        add("jeans");
        add("pant");
        add("trouser");
        add("short");
        add("underwear");
        add("sock");
        add("shoes");

    }};

    public static final ArrayList<String> COLOR = new ArrayList<>(){{
        add("blue");
        add("yellow");
        add("green");
        add("red");
    }};

    public static final ArrayList<String> SIZE = new ArrayList<>(){{
        add("xs");
        add("s");
        add("m");
        add("l");
        add("xl");
    }};

    /*
    put("clothes", "What Clothes are you looking for?");
        put("jacket", "What size do you have?");
        put("coat", "What size do you have?");
        put("hoody", "What size do you have?");
        put("sweatshirt", "What size do you have?");
        put("t-shirt", "What size do you have?");
        put("shirt", "What size do you have?");
        put("cardigan", "What size do you have?");
        put("blazer", "What size do you have?");
        put("suit", "What size do you have?");
        put("jeans", "What size do you have?");
        put("pant", "What size do you have?");
        put("trouser", "What size do you have?");
        put("short", "What size do you have?");
        put("underwear", "What size do you have?");
        put("sock", "What size do you have?");
        put("shoes", "What Shoe size do you have?");
     */
}
