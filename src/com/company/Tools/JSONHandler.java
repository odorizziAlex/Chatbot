package com.company.Tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


public class JSONHandler {

    //https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library

    private Random random = new Random();
    private JSONObject jsonData;

    public JSONHandler(){
        try {
            readJSONFile();
        } catch (Exception e) {
            System.out.println("JSON file couldn't be read.");
            e.printStackTrace();
        }
    }

    private void readJSONFile()throws Exception {
        String JSONFilePath = System.getProperty("user.dir")+"/src/com/company/utils/Expressions.json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSONFilePath));
        jsonData =  (JSONObject) obj;
    }

    public boolean contains(String key, String value){
        JSONObject demandKey = (JSONObject) jsonData.get("demand");
        JSONArray innerKeyArray = (JSONArray) demandKey.get(key);
        ArrayList<String> listData = new ArrayList<>();
        if(innerKeyArray != null){
            for(int i = 0; i < innerKeyArray.size(); i++){
                listData.add((String) innerKeyArray.get(i));
            }
        }
        return listData.contains(value);
    }

    // Get a starting phrase
    public String getStarter(){
        JSONObject responseKey = (JSONObject) jsonData.get("response");
        JSONArray starterKey = (JSONArray) responseKey.get("starter");
        return (String) starterKey.get(random.nextInt(starterKey.size()));
    }

    // Get a question phrase
    public String getQuestion(String key){
        JSONObject responseKey = (JSONObject) jsonData.get("response");
        JSONObject questionKey = (JSONObject) responseKey.get("question");
        JSONArray targetKey = (JSONArray) questionKey.get(key);
        return (String) targetKey.get(random.nextInt(targetKey.size()));
    }

    // Get a response phrase
    public String getResponse(String demandType, String value){
        JSONObject responseKey = (JSONObject) jsonData.get("response");
        JSONObject standardResKey = (JSONObject) responseKey.get("standard");
        JSONObject demand = (JSONObject) standardResKey.get(demandType);
        String expression = (String) demand.get(value);
        return expression;
    }
}
