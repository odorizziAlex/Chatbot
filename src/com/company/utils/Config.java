package com.company.utils;

import java.util.ArrayList;

public final class Config {

    /*
    This is the file, that contains all constants and configuration values.
     */

    public static final int COMPONENT_NUMBER = 5;

    // All demand components
    public static final int DEMAND_GENDER = 0;
    public static final int DEMAND_ITEM = 1;
    public static final int DEMAND_COLOR = 2;
    public static final int DEMAND_SIZE = 3;
    public static final int DEMAND_PRICE = 4;

    // JSON demand keys
    public static final String JSON_DEMAND_GENDER_KEY = "gender";
    public static final String JSON_DEMAND_UPPER_BODY_ITEM_KEY = "upper_body_item";
    public static final String JSON_DEMAND_LOWER_BODY_ITEM_KEY = "lower_body_item";
    public static final String JSON_DEMAND_FOOTWEAR_KEY = "footwear_item";
    public static final String JSON_DEMAND_COLOR_KEY = "color";
    public static final String JSON_DEMAND_SIZE_KEY = "clothing_size";

    public static final ArrayList<String> GENDER = new ArrayList<>(){{
        add("female");
        add("male");
        add("men");
        add("man");
        add("women");
        add("woman");

    }};

    public static final ArrayList<String> CLOTH_ITEMS = new ArrayList<>(){{
        add("clothes");
        add("jacket");
        add("coat");
        add("hoodie");
        add("sweatshirt");
        add("t-shirt");
        add("shirt");
        add("cardigan");
        add("blazer");
        add("suit");
        add("jeans");
        add("pant");
        add("pants");
        add("trouser");
        add("trousers");
        add("short");
        add("underwear");
    }};

    public static final ArrayList<String> FOOTWEAR = new ArrayList<>(){{
        add("sock");
        add("socks");
        add("shoe");
        add("shoes");
        add("sandals");
        add("falerinas");
        add("farefootshoes");
        add("high-heels");
        add("running-shoes");
        add("flip-flops");
        add("high heels");
        add("running shoes");
        add("flip flops");
        add("sneakers");
    }};


    public static final ArrayList<String> COLOR = new ArrayList<>(){{
        add("blue");
        add("yellow");
        add("green");
        add("red");
    }};

    public static final ArrayList<String> CLOTH_SIZE = new ArrayList<>(){{
        add("xs");
        add("s");
        add("m");
        add("l");
        add("xl");
        add("large");
        add("medium");
        add("small");
        add("extra small");
        add("extra large");
    }};

}
