package com.company;

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

    //
    private static ArrayList<String> userAnswers = new ArrayList<>();
    private static Boolean isFinished = false;
    private static DemandAnalyzer demandAnalyzer = new DemandAnalyzer();



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


        while(!isFinished) {
            // In formattedInput():
            //  1. Read User Input
            //  1.2 Remove special characters
            //  2. Tokenize, Lemmatize, Remove Stop words of user Input
            System.out.println("---log: empty demand components:");
            System.out.println("---"+Arrays.toString(demandAnalyzer.getNeededDemandComponents().toArray()));
            System.out.println("---"+demandAnalyzer.getDemandComponents().values());
            // 3. Check user input against HashMap
            for(String word : formattedInput()) {
                if(GENDER.contains(word)){
                    demandAnalyzer.setDemandComponent(DEMAND_GENDER,word);
                } else if(ITEMS.contains(word)){
                    demandAnalyzer.setDemandComponent(DEMAND_ITEM,word);
                } else if(COLOR.contains(word)){
                    demandAnalyzer.setDemandComponent(DEMAND_COLOR,word);
                } else if(SIZE.contains(word)){
                    demandAnalyzer.setDemandComponent(DEMAND_SIZE,word);
                }else if(Pattern.matches("[\\d]+", word)){
                    demandAnalyzer.setDemandComponent(DEMAND_PRICE,word);
                }
                /*
                if(BotResponses.maleClothing.containsKey(word)) {
                    generateResponse(word);
                }

                 */
            }

            // 3.1 If key is same -> print output otherwise (No clothes found from given input)
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

        return formattedInput;
    }

    private static String generateResponse(String word){
        System.out.println(BotResponses.maleClothing.get(word));

        return "";
    }

}
