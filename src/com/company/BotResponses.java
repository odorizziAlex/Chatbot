package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BotResponses {

    public static final ArrayList<String> responseStarter= new ArrayList<>(){{
        add("Alright,");
        add("Ok,");
        add("Well,");
    }};

    public static final Map<String,String> standardItemResponses = new HashMap<String, String>(){{
        put("clothes", "What Clothes are you looking for?");
        put("jacket", "so you need a new jacket");
        put("coat", "so you need a new coat");
        put("hoody", "so you need a new hoodie");
        put("sweatshirt", "so you need a new sweatshirt");
        put("t-shirt", "so you need a new t-shirt");
        put("shirt", "so you need a new shirt");
        put("cardigan", "so you need a new cardigan");
        put("blazer", "so you need a new blazer");
        put("suit", "so you need a new suit");
        put("jeans", "so you need a new pair of jeans");
        put("pant", "so you need a new pair of pants");
        put("pants", "so you need a new pair of pants");
        put("trouser", "so you need a new pair of trousers");
        put("trousers", "so you need a new pair of trousers");
        put("short", "so you need new shorts");
        put("shorts", "so you need new shorts");
        put("underwear", "so you need some new underwear");
        put("sock", "so you need a new pair of socks");
        put("socks", "so you need a new pair of socks");
        put("shoe", "so you need a new pair of shoes");
        put("shoes", "so you need a new pair of shoes");
    }};

    public static final Map<String,String> maleClothing = new HashMap<String, String>(){{
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
    }};

    public static final Map<String,String> femaleClothing = new HashMap<String, String>(){{
        put("clothes", "What Clothes are you looking for?");
        put("dress", "What size do you have?");
        put("shirt", "What size do you have?");
        put("blouse", "What size do you have?");
        put("top", "What size do you have?");
        put("skirt", "What size do you have?");
        put("jeans", "What size do you have?");
        put("pant", "What size do you have?");
        put("trouser", "What size do you have?");
        put("jumpsuits", "What size do you have?");
        put("romper", "What size do you have?");
        put("short", "What size do you have?");
        put("blazer", "What size do you have?");
        put("waistcoat", "What size do you have?");
        put("jacket", "What size do you have?");
        put("coat", "What size do you have?");
        put("knitwear", "What size do you have?");
        put("lingerie", "What size do you have?");
        put("nightwear", "What size do you have?");
        put("tights", "What size do you have?");
        put("hoody", "What size do you have?");
        put("sweatshirt", "What size do you have?");
        put("cardigan", "What size do you have?");
        put("jumper", "What size do you have?");
        put("underwear", "What size do you have?");
        put("sock", "What size do you have?");
        put("shoe", "What Shoe size do you have?");
    }};
}
