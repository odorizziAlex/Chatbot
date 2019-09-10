package com.company.Result;

import java.util.ArrayList;

public class URLGenerator {

    /**
     * This url generator specifically works for the zalando.de website.
     */

    private String url = "https://en.zalando.de/";
    private ArrayList<String> components;

    public URLGenerator(ArrayList<String> components){
        System.out.println("\nHere are all results:\nhttp://...");

        this.components = components;
        System.out.println(components);

    }
}
