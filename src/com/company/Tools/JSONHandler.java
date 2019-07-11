package com.company.Tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class JSONHandler {

    private Random random = new Random();

    private JSONObject jsonObject;

    public JSONHandler(){
        try {
            readJSONFile();
        } catch (Exception e) {
            System.out.println("JSON file couldn't be read.");
            e.printStackTrace();
        }
    }

    private void readJSONFile()throws Exception {
        //System.out.println("---log: JSON HANDLER");
        //File file = new File(System.getProperty("user.dir")+"/src/com/company/utils/Expressions.json");
        //System.out.println("---log: json File exists: "+file.exists());
        String JSONFilePath = System.getProperty("user.dir")+"/src/com/company/utils/Expressions.json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSONFilePath));
        jsonObject =  (JSONObject) obj;
    }

    public String getRandomStarter(){
        JSONObject outterKEy = (JSONObject) jsonObject.get("response");
        JSONArray innerKey = (JSONArray) outterKEy.get("starter");

        //System.out.println(inn.get(random.nextInt(name2.size())));
        return (String) innerKey.get(random.nextInt(innerKey.size()));
    }

    public boolean containsDemandObject(String key, String value){
        JSONObject outterKey = (JSONObject) jsonObject.get("demand");
        JSONArray innerKeyArray = (JSONArray) outterKey.get(key);
        ArrayList<String> listData = new ArrayList<>();
        if(innerKeyArray != null){
            for(int i=0;i<innerKeyArray.size();i++){
                listData.add((String) innerKeyArray.get(i));
            }
        }
        return listData.contains(value);
    }


}
