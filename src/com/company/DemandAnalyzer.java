package com.company;

import com.company.Tools.JSONHandler;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.company.utils.Config.*;

public class DemandAnalyzer {

    // HashMap that stores the exact demand.
    private ArrayList<String> demandComponents = new ArrayList<>();
    private JSONHandler jsonHandler = new JSONHandler();

    //
    private static boolean areShoesOrPants = false;
    private StringBuilder priceWordBuilder = new StringBuilder();
    private String isPriceRegEx = "([\\d]+([€$]|))";


    public DemandAnalyzer(){
        initEmptyDemandList();
    }

    /*
     * Creates empty hashMap but keys are already assigned.
     * Values are still empty at this point in time. (Going to be filled later).
     */


    private void initEmptyDemandList() {
        for(int i = 0; i < COMPONENT_NUMBER; i++){
            demandComponents.add(EMPTY_POSITION);
        }
    }

    public ArrayList<String> getDemandComponents(){
        return demandComponents;
    }

    /*
     * Returns List of components, that are still empty.
     */
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
            // this is the actual word we work with
            String word = words.get(i),
            // this checks the following word after an integer
            nextWord = "";

            // here always the next word will be saved in this variable
            if(i!=words.size()-1){
                nextWord = words.get(i+1);
            }

            if(Pattern.matches(isPriceRegEx,words.get(i))){
                // if price is expressed with a word, for example: "100 euro", then those two tokens
                //  have to be combined in one string. so, later on, if the following word of a numeric token expresses
                //  currency, then it'll be appended to this StringBuilder.
                priceWordBuilder.append(word);
            }

            // this method inserts the right values into the ArrayList of needed components.
            insert(word, nextWord);
        }
    }

    /*
     * this method inserts the right values into the ArrayList of needed components.
     */
    private void insert(String word, String nextWord){
        if(isGenderDetail(word)){
            setDemandComponent(DEMAND_GENDER,word);

        }else if(isUpperBodyClothingDetail(word)){
            setDemandComponent(DEMAND_ITEM,word);
            // this variable helps to later insert the right size type (shoes = numbers, jacket = L,XL...)
            areShoesOrPants = false;

        }else if(isLowerBodyClothingOrFootwearDetail(word)) {
            setDemandComponent(DEMAND_ITEM, word);
            //
                areShoesOrPants = true;

        }else if(isColorOfClothingDetail(word)){
            setDemandComponent(DEMAND_COLOR,word);


        }else if(isSizeDetail(word, areShoesOrPants)){
            setDemandComponent(DEMAND_SIZE,word);


        }else if(isPriceDetailForLowerBodyClothing(word, nextWord, areShoesOrPants)){
            setDemandComponent(DEMAND_SIZE,word);
            priceWordBuilder.delete(0,priceWordBuilder.length());

        }else if(isPriceDetailForUpperBodyClothing(word)){
            setDemandComponent(DEMAND_PRICE,word);

        }else if(isPriceInformationWithSpace(nextWord)){
            priceWordBuilder.append(" "+nextWord);
            setDemandComponent(DEMAND_PRICE,priceWordBuilder.toString());

        } else if(isFabricDetail(word)){
            setDemandComponent(DEMAND_FABRIC,word);
        }
        priceWordBuilder.delete(0,priceWordBuilder.length());
    }

    private void setDemandComponent(int componentType ,String component) {
        demandComponents.set(componentType, component);
    }


    /*
     * if the json file contains the given word for gender and the the ArrayLocation is still empty,
     *  word will be inserted into the array
     */
    private boolean isGenderDetail(String word){
        return jsonHandler.contains(JSON_DEMAND_GENDER_KEY, word)
                && getEmptyComponent(DEMAND_GENDER).equals(EMPTY_POSITION);
    }

    /*
     * if the json file contains the given word for upper body clothing and the the Array Location is still empty,
     *   word will be inserted into the array
     */
    private boolean isUpperBodyClothingDetail(String word){
        return jsonHandler.contains(JSON_DEMAND_UPPER_BODY_ITEM_KEY, word)
                && getEmptyComponent(DEMAND_ITEM).equals(EMPTY_POSITION);
    }

    /*
     * if the json file contains the given word for lower body clothing, or footwear and the the Array
     *   Location is still empty word will be inserted into the array
     */
    private boolean isLowerBodyClothingOrFootwearDetail(String word){
        return (jsonHandler.contains(JSON_DEMAND_LOWER_BODY_ITEM_KEY, word)
                || jsonHandler.contains(JSON_DEMAND_FOOTWEAR_KEY, word))
                && getEmptyComponent(DEMAND_ITEM).equals(EMPTY_POSITION);
    }

    /*
     * if the json file contains the given word for color and the the Array Location is still empty,
     *  word will be inserted into the array
     */
    private boolean isColorOfClothingDetail(String word){
        return jsonHandler.contains(JSON_DEMAND_COLOR_KEY, word)
                && getEmptyComponent(DEMAND_COLOR).equals(EMPTY_POSITION);
    }

    /*
     * if the json file contains the given word for size (xs,s,l..), isShoesOrPants is false
     *  and the the Array Location is still empty word will be inserted into the array
     */

    private boolean isSizeDetail(String word, boolean areShoesOrPants){
        return !areShoesOrPants
                && jsonHandler.contains(JSON_DEMAND_SIZE_KEY, word)
                && getEmptyComponent(DEMAND_SIZE).equals(EMPTY_POSITION);
    }

    /*
     * if areShoesOrPants is true, size value is numeric! Also if the next word doesn't express currency,
     *  the word is numeric and the array position is empty word will be inserted into the array
     */

    private boolean isPriceDetailForLowerBodyClothing(String word, String nextWord, boolean areShoesOrPants){
        return areShoesOrPants
                && !jsonHandler.contains(JSON_DEMAND_PRICE_KEY,nextWord)
                && Pattern.matches("[\\d]+$", word)//$ = string ende
                && getEmptyComponent(DEMAND_SIZE).equals(EMPTY_POSITION);
    }

    /*
     * if word is numeric with currency symbol following, and position empty, safe word
     */
    private boolean isPriceDetailForUpperBodyClothing(String word){
        return Pattern.matches("[\\d]+[€$]", word)
                && getEmptyComponent(DEMAND_PRICE).equals(EMPTY_POSITION);
    }

    /*
     * if next word is currency, then safe number plus currency word in array
     */

    private boolean isPriceInformationWithSpace(String nextWord){
        return jsonHandler.contains(JSON_DEMAND_PRICE_KEY,nextWord)
                && getEmptyComponent(DEMAND_PRICE).equals(EMPTY_POSITION);
    }

    /*
     * if fabric matches, then safe it in array
     */

    private boolean isFabricDetail(String word){
        return jsonHandler.contains(JSON_DEMAND_FABRIC_KEY,word)
                && getEmptyComponent(DEMAND_FABRIC).equals(EMPTY_POSITION);
    }
}
