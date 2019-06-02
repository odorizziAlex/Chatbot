package com.company;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;

public class POSTagger {

    public ArrayList<String> tag(String text) {
        // Initialize Variables
        ArrayList<String> posTags = new ArrayList<>();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument document = new CoreDocument(text);

        stanfordCoreNLP.annotate(document);
        List<CoreLabel> tokenList = document.tokens();

        for(CoreLabel token : tokenList) {
            String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            posTags.add(pos);
        }
        return posTags;
    }

}
