package com.company;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;

public class Lemmatizer {

    public ArrayList<String> lemmatize(String text) {
        // Initialize Variables
        ArrayList<String> tokens = new ArrayList<>();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument document = new CoreDocument(text);

        stanfordCoreNLP.annotate(document);
        List<CoreLabel> tokenList = document.tokens();

        for(CoreLabel token : tokenList) {
            tokens.add(token.lemma());
        }
        return tokens;
    }

}
