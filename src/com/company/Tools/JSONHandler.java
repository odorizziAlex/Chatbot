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
    private JSONObject expJsonData;
    private JSONObject URLJsonData;

    public JSONHandler(){
        try {
            readExpressionJSONFile();
        } catch (Exception e) {
            System.out.println("EXPRESSION JSON file couldn't be read.");
            e.printStackTrace();
        }

        try {
            readURLJSONFile();
        } catch (Exception e){
            System.out.println("URL JSON file couldn't be read.");
        }
    }

    /**
     *
     * @throws Exception
     */
    private void readExpressionJSONFile()throws Exception {
        String JSONFilePath = System.getProperty("user.dir")+"/src/com/company/utils/Expressions.json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSONFilePath));
        expJsonData = (JSONObject) obj;
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public boolean expContains(String key, String value){
        JSONObject demandKey = (JSONObject) expJsonData.get("demand");
        JSONArray innerKeyArray = (JSONArray) demandKey.get(key);
        ArrayList<String> listData = new ArrayList<>();

        if(innerKeyArray != null){
            for(int i = 0; i < innerKeyArray.size(); i++){
                listData.add((String) innerKeyArray.get(i));
            }
        }
        return listData.contains(value);
    }

    /**
     *
     * @return
     */
    // Get a starting phrase
    public String getStarter(){
        JSONObject responseKey = (JSONObject) expJsonData.get("response");
        JSONArray starterKey = (JSONArray) responseKey.get("starter");

        return (String) starterKey.get(random.nextInt(starterKey.size()));
    }

    /**
     *
     * @param key
     * @return
     */
    // Get a question phrase
    public String getQuestion(String key){
        JSONObject responseKey = (JSONObject) expJsonData.get("response");
        JSONObject questionKey = (JSONObject) responseKey.get("question");
        JSONArray targetKey = (JSONArray) questionKey.get(key);

        return (String) targetKey.get(random.nextInt(targetKey.size()));
    }

    /**
     *
     * @param demandType
     * @param value
     * @return
     */
    // Get a response phrase
    public String getResponse(String demandType, String value){
        JSONObject responseKey = (JSONObject) expJsonData.get("response");
        JSONObject standardResKey = (JSONObject) responseKey.get("standard");
        JSONObject demand = (JSONObject) standardResKey.get(demandType);
        String expression = (String) demand.get(value);

        return expression;
    }

    /**
     *
     * @throws Exception json file couldn't be read
     */
    private void readURLJSONFile()throws Exception {
        String JSONFilePath = System.getProperty("user.dir")+"/src/com/company/utils/URLComponentsZalando.JSON";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSONFilePath));
        URLJsonData = (JSONObject) obj;
    }

    public String getGenderAndItemSnippet(String genderKey, String itemKey){
        String gender;
        if(genderKey.equals("man") || genderKey.equals("male")){
            gender = "male";
        } else {
            gender = "female";
        }
        JSONObject snippetGenderKey = (JSONObject) URLJsonData.get(gender);
        String urlSnippet = (String) snippetGenderKey.get(itemKey);
        return urlSnippet;
    }

    public String getColorSnippet(String value){
        JSONObject snippetColorKey = (JSONObject) URLJsonData.get("color");
        String urlSnippet = (String) snippetColorKey.get(value);
        return urlSnippet;
    }

    public String getSizeSnippet(String value){
        JSONObject snippetColorKey = (JSONObject) URLJsonData.get("size");
        String urlSnippet = (String) snippetColorKey.get(value);
        return urlSnippet;
    }

    public String getPriceSnippet(String value){
        JSONObject snippetColorKey = (JSONObject) URLJsonData.get("price");
        String urlSnippet = (String) snippetColorKey.get(value);
        return urlSnippet;
    }

    public String getFabricSnippet(String value){
        JSONObject snippetColorKey = (JSONObject) URLJsonData.get("fabric");
        String urlSnippet = (String) snippetColorKey.get(value);
        return urlSnippet;
    }


}
