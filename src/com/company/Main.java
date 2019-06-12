package com.company;

import edu.stanford.nlp.trees.WordStemmer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. Greeting Bot (Hey there! 👋 Good to see you here. I'm Clofy.
        //    I'm here to help you find the best clothes that suit you and the specific occation.
        //.)
        // 2. Question Bot (looking for Male / Female?
        // 3. Question Bot
        ArrayList<String> userAnswers = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer();
        SentenceSplitter sentenceSplitter = new SentenceSplitter();
        POSTagger posTagger = new POSTagger();
        Lemmatizer lemmatizer = new Lemmatizer();
        StopWordRemover stopWordRemover = new StopWordRemover();
        Scanner scanner = new Scanner(System.in);
        Boolean isFinished = false;


        String greeting = "Hey there! \uD83D\uDC4B Good to see you here. I'm Clofy.\n" +
                "I'm here to help you find the best clothes that suit you and the specific occation.\n" +
                "What are you looking for?";
        System.out.println(greeting);
//        String clothing = scanner.nextLine().toLowerCase();
//        System.out.println("Great! Before we can do that we need some more info.");
//        System.out.println("Are you male or female?");
//        String gender = scanner.nextLine().toLowerCase();
//        System.out.println("What is the occasion?");
//        String occasion = scanner.nextLine().toLowerCase();
//        System.out.println("Thank you very much. \n" +
//                "Here are clothes we found meeting your criteria:\n" +
//                "Gender: " + gender + "\n" +
//                "Clothing: " + clothing + "\n" +
//                "Occasion: " + occasion + "\n");


        while(!isFinished) {
            // 1. Read user input
            String userInput = scanner.nextLine();
            userAnswers.add(userInput);
            // 2. Tokenize, Lemmatize, Remove Stop words of user Input
            ArrayList<String> formattedInput = tokenizer.tokenize(userInput);
            System.out.println("log: tokenized iunput:");
            System.out.println(formattedInput);
            formattedInput = stopWordRemover.removeStopWords(formattedInput);
            System.out.println("log: stop words removed:");
            System.out.println(formattedInput);
            // 3. Check use input against HashMap
            for(String word : formattedInput) {
                if(BotResponses.maleClothing.containsKey(word)) {
                    System.out.println(BotResponses.maleClothing.get(word));
                }
            }
            // 3.1 If key is same -> print output otherwise (No clothes found from given input)
        }

    }


}
