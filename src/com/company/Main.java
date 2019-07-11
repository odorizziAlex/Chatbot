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
    private static Tokenizer tokenizer = new Tokenizer();
    private static SentenceSplitter sentenceSplitter = new SentenceSplitter();
    private static POSTagger posTagger = new POSTagger();
    private static Lemmatizer lemmatizer = new Lemmatizer();
    private static Scanner scanner = new Scanner(System.in);
    private static StopWordRemover stopWordRemover = new StopWordRemover();
    private static JSONHandler jsonHandler = new JSONHandler();

    // init response tools:
    private static StandardResponse standardResponse = new StandardResponse();

    //
    private static ArrayList<String> userAnswers = new ArrayList<>();
    private static Boolean isFinished = false;
    private static DemandAnalyzer demandAnalyzer = new DemandAnalyzer();
    private static boolean areShoesOrPants = false;
    private static ArrayList<String> formattedInputList = new ArrayList<>();


    //TODO ALEX: next standard response with JSON


    public static void main(String[] args) {
        // 1. Greeting Bot (Hey there! 👋 Good to see you here. I'm Clofy.
        //    I'm here to help you find the best clothes that suit you and the specific occation.
        //.)
        // 2. Question Bot (looking for Male / Female?
        // 3. Question Bot

        String greeting = "Hey there! \uD83D\uDC4B Good to see you here. I'm Clofy.\n" +
                "I'm here to help you find the best clothes that suit you and the specific occation.\n" +
                "What are you looking for?";
        System.out.println(greeting);
//        String clothing = scanner.nextLine().toLowerCase();
//        System.out.println("Great! Before we can do that we need some more info.");
//        System.out.println("Are you male or female?");
//        String gender = scanner.nextLine().toLowerCase();
//        System.out.println("What is the occasion?");
//        String occasion = scanner.nextLine().toLowerCase();Hi
//        System.out.println("Thank you very much. \n" +
//                "Here are clothes we found meeting your criteria:\n" +
//                "Gender: " + gender + "\n" +
//                "Clothing: " + clothing + "\n" +
//                "Occasion: " + occasion + "\n");
        //System.out.println("---log: lemmatizer test:");
        //System.out.println(lemmatizer.lemmatize("sneakers"));

        while(!isFinished) {

            // In formattedInput():
            //  1. Read User Input
            //  1.2 Remove special characters
            //  2. Tokenize, Lemmatize, Remove Stop words of user Input

            // 3. Check user input against HashMap
            for(String word : formattedInput()) {
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
                removeSpecialCharacters= rawUserInput.replaceAll("[-+^;,]",""),
                //1.3 Every character to lower case
                finalUserInput = removeSpecialCharacters.toLowerCase();

        userAnswers.add(finalUserInput);
        // 2. Tokenize, Lemmatize, Remove Stop words of user Input
        ArrayList<String> formattedInput = tokenizer.tokenize(finalUserInput);

        System.out.println("---log: tokenized input:");
        System.out.println("---"+formattedInput);

        formattedInput = stopWordRemover.removeStopWords(formattedInput);

        System.out.println("---log: stop words removed:");
        System.out.println("---"+formattedInput);

        formattedInputList = formattedInput;
        return formattedInput;
    }

    private static void setComponent(String word){
        String cleanedWord = word.replaceAll("[-+^()=§%&/;.,]","");

        System.out.println("---log: get demand object from json: ");
        System.out.println(jsonHandler.containsDemandObject(JSON_DEMAND_GENDER_KEY, cleanedWord));

        if(jsonHandler.containsDemandObject(JSON_DEMAND_GENDER_KEY, cleanedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_GENDER).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_GENDER,cleanedWord);

        }else if((jsonHandler.containsDemandObject(JSON_DEMAND_UPPER_BODY_ITEM_KEY, cleanedWord))
                && demandAnalyzer.getEmptyComponent(DEMAND_ITEM).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_ITEM,cleanedWord);
            areShoesOrPants = false;

        }else if((jsonHandler.containsDemandObject(JSON_DEMAND_LOWER_BODY_ITEM_KEY, cleanedWord)
                || jsonHandler.containsDemandObject(JSON_DEMAND_FOOTWEAR_KEY, cleanedWord))
                && demandAnalyzer.getEmptyComponent(DEMAND_ITEM).equals("")) {
            demandAnalyzer.setDemandComponent(DEMAND_ITEM, cleanedWord);
            areShoesOrPants = true;

        }else if(jsonHandler.containsDemandObject(JSON_DEMAND_COLOR_KEY, cleanedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_COLOR).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_COLOR,cleanedWord);

        }else if(!areShoesOrPants
                && jsonHandler.containsDemandObject(JSON_DEMAND_SIZE_KEY, cleanedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_SIZE).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_SIZE,cleanedWord);

        }else if(areShoesOrPants
                && Pattern.matches("[\\d]+$", cleanedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_SIZE).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_SIZE,cleanedWord);

        }else if(Pattern.matches("([\\d]+[€$])", cleanedWord)
                && demandAnalyzer.getEmptyComponent(DEMAND_PRICE).equals("")){
            demandAnalyzer.setDemandComponent(DEMAND_PRICE,cleanedWord);

        }
    }

    private static String generateStandardFeedbackResponse(ArrayList<String> filledComponents){
        StringBuilder response = new StringBuilder("Alright, ");
        for(int i=0;i<filledComponents.size();i++){
            if(!filledComponents.get(i).equals("")){
                switch (i){
                    case DEMAND_GENDER:
                        break;
                    case DEMAND_ITEM:
                        break;
                    case DEMAND_COLOR:
                        break;
                    case DEMAND_SIZE:
                        break;
                    case DEMAND_PRICE:
                        break;
                    default:
                        System.out.println("---");
                        response.delete(0,response.length()-1);
                        response.append("Sorry, I couldn't make out any Information I need to help you.");
                        return response.toString();
                }
            }
        }
        return "";
    }

}
