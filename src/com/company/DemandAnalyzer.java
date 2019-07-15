package com.company;

import com.company.Tools.JSONHandler;
import com.company.Tools.Lemmatizer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.company.utils.Config.*;

public class DemandAnalyzer {

    // HashMap that stores the exact demand.
    private ArrayList<String> demandComponents = new ArrayList<>();
    private Lemmatizer lemmatizer = new Lemmatizer();
    private JSONHandler jsonHandler = new JSONHandler();

    //
    private static boolean areShoesOrPants = false;
    private StringBuilder priceWordHandler = new StringBuilder();
    private String isPriceRegEx = "([\\d]+([€$]|))";




    public DemandAnalyzer(){
        initEmptyDemandList();
    }

    // Creates empty hashMap but keys are already assigned.
    // Values are still empty at this point in time. (Going to be filled later).
    private void initEmptyDemandList() {
        for(int i=0;i<COMPONENT_NUMBER;i++){
            demandComponents.add(EMPTY_POSITION);
        }
    }

    public ArrayList<String> getDemandComponents(){
        return demandComponents;
    }

    // Returns List of components, that are still empty.
    public ArrayList<Integer> getAllEmptyDemandComponents(){
        ArrayList<Integer> emptyDemandComponents = new ArrayList<>();
        for(int i=0;i<demandComponents.size();i++){
            if(demandComponents.get(i).equals(EMPTY_POSITION)){
                emptyDemandComponents.add(i);
            }
        }
        return emptyDemandComponents;
    }

    public String getEmptyComponent(int index){
        return demandComponents.get(index);
    }

    public void setComponent(ArrayList<String> words){
        // iteration over every word in the array
        for(int i=0;i<words.size();i++){
            // some words couldn't be formatted correctly. They got combined with punctuation marks,
            //  if they were next in the string.
            // this removes all punctuation marks from words.
            String removeSpecialCharactersFromWord = words.get(i).replaceAll("[+^()=§%&/;.,]",""),
                    // this is the actual word we work with
                    lemmatizedWord = "",
                    // this checks the following word after an integer
                    nextLemmatizedWord = "";

            // here always the next word will be saved in this variable
            if(i!=words.size()-1){
                nextLemmatizedWord = lemmatizer.lemmatizeWord(words.get(i+1));
            }

            // numeric information mustn't be lemmatized!
            if(Pattern.matches(isPriceRegEx,words.get(i))){
                lemmatizedWord = removeSpecialCharactersFromWord;
                // if price is expressed with a word, for example: "100 euro", then those two tokens
                //  have to be combined in one string. so, later on, if the following word of a numeric token expresses
                //  currency, then it'll be appended to this StringBuilder.
                priceWordHandler.append(lemmatizedWord);
            } else {
                // if the current word isn't numeric, lemmatization should take place.
                lemmatizedWord = lemmatizer.lemmatizeWord(removeSpecialCharactersFromWord);
            }

            // this method inserts the right values into the ArrayList of needed components.
            insert(lemmatizedWord, nextLemmatizedWord);
        }
    }

    // this method inserts the right values into the ArrayList of needed components.
    private void insert(String word, String nextWord){
        // if the json file contains the given word for gender and the the ArrayLocation is still empty,
        //  word will be inserted into the array
        if(jsonHandler.containsDemandObject(JSON_DEMAND_GENDER_KEY, word)
                && getEmptyComponent(DEMAND_GENDER).equals(EMPTY_POSITION)){
            setDemandComponent(DEMAND_GENDER,word);

        // if the json file contains the given word for upper body clothing and the the Array Location is still empty,
        //  word will be inserted into the array
        }else if((jsonHandler.containsDemandObject(JSON_DEMAND_UPPER_BODY_ITEM_KEY, word))
                && getEmptyComponent(DEMAND_ITEM).equals(EMPTY_POSITION)){
            setDemandComponent(DEMAND_ITEM,word);
            // this variable helps to later insert the right size type (shoes = numbers, jacket = L,XL...)
            areShoesOrPants = false;

        // if the json file contains the given word for lower body clothing, or footwear and the the Array
        // Location is still empty word will be inserted into the array
        }else if((jsonHandler.containsDemandObject(JSON_DEMAND_LOWER_BODY_ITEM_KEY, word)
                || jsonHandler.containsDemandObject(JSON_DEMAND_FOOTWEAR_KEY, word))
                && getEmptyComponent(DEMAND_ITEM).equals(EMPTY_POSITION)) {
            setDemandComponent(DEMAND_ITEM, word);
            //
            areShoesOrPants = true;

        // if the json file contains the given word for color and the the Array Location is still empty,
        //  word will be inserted into the array
        }else if(jsonHandler.containsDemandObject(JSON_DEMAND_COLOR_KEY, word)
                && getEmptyComponent(DEMAND_COLOR).equals(EMPTY_POSITION)){
            setDemandComponent(DEMAND_COLOR,word);

        // if the json file contains the given word for size (xs,s,l..), isShoesOrPants is false
        // and the the Array Location is still empty word will be inserted into the array
        }else if(!areShoesOrPants
                && jsonHandler.containsDemandObject(JSON_DEMAND_SIZE_KEY, word)
                && getEmptyComponent(DEMAND_SIZE).equals(EMPTY_POSITION)){
            setDemandComponent(DEMAND_SIZE,word);

        // if areShoesOrPants is true, size value is numeric! Also if the next word doesn't express currency,
        // the word is numeric and the array position is empty word will be inserted into the array
        }else if(areShoesOrPants
                && !jsonHandler.containsDemandObject(JSON_DEMAND_PRICE_KEY,nextWord)
                && Pattern.matches("[\\d]+$", word)
                && getEmptyComponent(DEMAND_SIZE).equals(EMPTY_POSITION)){

            setDemandComponent(DEMAND_SIZE,word);
            priceWordHandler.delete(0,priceWordHandler.length());

        // if word is numeric with currency symbol following, and position empty, safe word
        }else if(Pattern.matches("[\\d]+[€$]", word)
                && getEmptyComponent(DEMAND_PRICE).equals(EMPTY_POSITION)){
            setDemandComponent(DEMAND_PRICE,word);

        // if next word is currency, then safe number plus currency word in array
        }else if(jsonHandler.containsDemandObject(JSON_DEMAND_PRICE_KEY,nextWord)
                && getEmptyComponent(DEMAND_PRICE).equals(EMPTY_POSITION)){

            priceWordHandler.append(" "+nextWord);
            //System.out.println("---priceGen: "+priceWordHandler.toString());
            setDemandComponent(DEMAND_PRICE,priceWordHandler.toString());
        // if fabric matches, then safe it in array
        } else if(jsonHandler.containsDemandObject(JSON_DEMAND_FABRIC_KEY,word)
                && getEmptyComponent(DEMAND_FABRIC).equals(EMPTY_POSITION)){
            setDemandComponent(DEMAND_FABRIC,word);
        }
    }

    private void setDemandComponent(int componentType ,String component) {
        demandComponents.set(componentType, component);
    }
}
