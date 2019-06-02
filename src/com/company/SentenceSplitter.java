package com.company;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;

public class SentenceSplitter {

    public ArrayList<String> splitToSentences(String text) {
        // Initialize Variables
        ArrayList<String> sentences = new ArrayList<>();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument document = new CoreDocument(text);

        stanfordCoreNLP.annotate(document);
        List<CoreSentence> splitSentences = document.sentences();

        for(CoreSentence sentence : splitSentences) {
            sentences.add(sentence.toString());
        }
        return sentences;
    }
}
