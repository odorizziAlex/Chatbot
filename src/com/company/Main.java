package com.company;

import com.company.Response.StandardResponse;
import com.company.Tools.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.company.utils.Config.*;

public class Main {

    // Init tools:
    // Input tools:
    private static Tokenizer tokenizer = new Tokenizer();
    private static SentenceSplitter sentenceSplitter = new SentenceSplitter();
    private static POSTagger posTagger = new POSTagger();
    private static Lemmatizer lemmatizer = new Lemmatizer();
    private static Scanner scanner = new Scanner(System.in);
    private static StopWordRemover stopWordRemover = new StopWordRemover();
    // Processing tools:
    private static JSONHandler jsonHandler = new JSONHandler();
    private static DemandAnalyzer demandAnalyzer = new DemandAnalyzer();

    // init response tools:
    private static StandardResponse standardResponse = new StandardResponse();

    //
    private static ArrayList<String> userAnswers = new ArrayList<>();
    private static boolean areShoesOrPants = false;
    private static ArrayList<String> input = new ArrayList<>();

    // Cancellation condition
    private static Boolean isFinished = false;
/*
    TODO:       -next: standard response with JSON --> done
                    -add fabrics to color!!!
                    -catch price information like "100 bucks" (euro, dollar, bucks, usd, etc.)
                    -maybe ask for extras like: input: "i am looking for shoes", output: "what kind of shoes"
                -next^2: ask for missing components
                (-next^3: ask for extras
                -next^4: check spelling! Not autocorrect, but similarity check and understanding anyways
                -next^5: answer user questions as good as possible: input: what kind of clothes do you have?" output:"...")
 */

    public static void main(String[] args) {
        String greeting = "Hey there! \uD83D\uDC4B Good to see you here. I'm Clofy.\n" +
                "I'm here to help you find the best clothes that suit you and the specific occation.\n" +
                "What are you looking for?";
        System.out.println(greeting);

        System.out.println("---log: Needed Components:");
        System.out.println("---log:[gender, item, color, size, price, fabric]");
        while(!isFinished) {

            input = formattedInput();
            System.out.println("---log: formattedInput:");
            System.out.println("---log:"+input);

            for(String word : input) {
                setComponent(word);
            }

            System.out.println("---log: empty demand components:");
            System.out.println("---"+Arrays.toString(demandAnalyzer.getAllEmptyDemandComponents().toArray()));
            System.out.println("--->"+Arrays.toString(demandAnalyzer.getDemandComponents().toArray()));

            System.out.println(standardResponse.generateStandardResponse(demandAnalyzer.getDemandComponents()));
        }

    }

    private static ArrayList<String> formattedInput(){
        // 1. Read user input
        String rawUserInput = scanner.nextLine(),
                //1.2 Remove special characters
                removeSpecialCharacters= rawUserInput.replaceAll("[-+^;,.!?()=§%&/]",""),
                //1.3 Every character to lower case
                finalUserInput = removeSpecialCharacters.toLowerCase();

        userAnswers.add(finalUserInput);
        // 2. Tokenize, Lemmatize, Remove Stop words of user Input
        ArrayList<String> formattedInput = tokenizer.tokenize(finalUserInput);

        //System.out.println("---log: tokenized input:");
        //System.out.println("---"+formattedInput);

        formattedInput = stopWordRemover.removeStopWords(formattedInput);

        //System.out.println("---log: stop words removed:");
        //System.out.println("---"+formattedInput);

        return formattedInput;
    }

    private static void setComponent(String word){
        String removeSpecialCharactersFromWord = word.replaceAll("[+^()=§%&/;.,]",""),
                lemmatizedWord;

        // Numeric information shouldn't be lemmatized!
        if(Pattern.matches("([\\d]+([€$]|))",word)){
            lemmatizedWord = removeSpecialCharactersFromWord;
        } else {
            lemmatizedWord = lemmatizer.lemmatizeString(removeSpecialCharactersFromWord);
        }

        //System.out.println("---log: lemmatized word:");
        //System.out.println("---"+lemmatizedWord);
        if(jsonHandler.containsDemandObject(JSON_DEMAND_GENDER_KEY, lemmatizedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_GENDER).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_GENDER,lemmatizedWord);

        }else if((jsonHandler.containsDemandObject(JSON_DEMAND_UPPER_BODY_ITEM_KEY, lemmatizedWord))
                && demandAnalyzer.getEmptyComponent(DEMAND_ITEM).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_ITEM,lemmatizedWord);
            areShoesOrPants = false;

        }else if((jsonHandler.containsDemandObject(JSON_DEMAND_LOWER_BODY_ITEM_KEY, lemmatizedWord)
                || jsonHandler.containsDemandObject(JSON_DEMAND_FOOTWEAR_KEY, lemmatizedWord))
                && demandAnalyzer.getEmptyComponent(DEMAND_ITEM).equals("")) {
            demandAnalyzer.setDemandComponent(DEMAND_ITEM, lemmatizedWord);
            areShoesOrPants = true;

        }else if(jsonHandler.containsDemandObject(JSON_DEMAND_COLOR_KEY, lemmatizedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_COLOR).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_COLOR,lemmatizedWord);

        }else if(!areShoesOrPants
                && jsonHandler.containsDemandObject(JSON_DEMAND_SIZE_KEY, lemmatizedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_SIZE).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_SIZE,lemmatizedWord);

        }else if(areShoesOrPants
                && Pattern.matches("[\\d]+$", lemmatizedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_SIZE).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_SIZE,lemmatizedWord);

        }else if(Pattern.matches("[\\d]+[€$]", lemmatizedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_PRICE).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_PRICE,lemmatizedWord);

        } else if(jsonHandler.containsDemandObject(JSON_DEMAND_FABRIC_KEY,lemmatizedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_FABRIC).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_FABRIC,lemmatizedWord);
        }
    }
}
