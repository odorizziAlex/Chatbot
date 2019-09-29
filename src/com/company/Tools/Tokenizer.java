package com.company.Tools;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Tokenizer {

    public ArrayList<String> tokenize(String text) {
        // Initialize Variables
        ArrayList<String> tokens = new ArrayList<>();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument document = new CoreDocument(text);

        stanfordCoreNLP.annotate(document);
        List<CoreLabel> tokenList = document.tokens();

        for(CoreLabel token : tokenList) {
            tokens.add(token.word());
        }

        return catchPriceInformation(tokens);
    }

    // Binds currency symbols to it's value, so it'll be stored as one token together.
    // This is meant to make it easier to separate price (100€) values and shoe size (42,43...) values from each other.
    private ArrayList<String> catchPriceInformation(ArrayList<String>tokenList){
        for(int i=0;i<tokenList.size();i++){
            if(Pattern.matches("[€$]",tokenList.get(i)) && Pattern.matches("[\\d]+", tokenList.get(i-1))){
                StringBuilder sb = new StringBuilder(tokenList.get(i-1));
                sb.append(tokenList.get(i));
                tokenList.set(i, sb.toString());
                tokenList.remove(i-1);
            }
        }
        return tokenList;
    }

}
