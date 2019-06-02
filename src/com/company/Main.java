package com.company;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
        ArrayList<String> tokens = tokenizer.tokenize(text);
        ArrayList<String> sentences = sentenceSplitter.splitToSentences(text);
        ArrayList<String> posTags = posTagger.tag(text);
        System.out.println(tokens);
        System.out.println(posTags);

    }
}
