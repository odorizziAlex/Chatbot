package com.company;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. Greeting Bot (Hey there! 👋 Good to see you here. I'm Clofy.
        //    I'm here to help you find the best clothes that suit you and the specific occation.
        //.)
        // 2. Question Bot (looking for Male / Female?
        // 3. Question Bot

        String text = "This is my text. How are you doing? You're great!";
        Tokenizer tokenizer = new Tokenizer();
        SentenceSplitter sentenceSplitter = new SentenceSplitter();
        POSTagger posTagger = new POSTagger();
        Scanner scanner = new Scanner(System.in);

        String greeting = "Hey there! \uD83D\uDC4B Good to see you here. I'm Clofy.\n" +
                "I'm here to help you find the best clothes that suit you and the specific occation.\n" +
                "What are you looking for?";
        System.out.println(greeting);
        String userInput = scanner.nextLine();
        System.out.println("Great! Before we can do that we need some more data");
        System.out.println("Are you male or female?");
        String gender = scanner.nextLine();
        System.out.println("What is the occasion");
        String occasion = scanner.nextLine();






    }
}
