package com.company;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        // 1. Greeting Bot (Hey there! 👋 Good to see you here. I'm Clofy.
        //    I'm here to help you find the best clothes that suit you and the specific occation.
        //.)
        // 2. Question Bot (looking for Male / Female?
        // 3. Question Bot

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument("This is my Text");
        pipeline.annotate(document);
        List<CoreLabel> firstSentence = document.sentences().get(0).tokens();
        for(CoreLabel token : firstSentence) {
            System.out.println(token.word());
        }


    }
}
