package com.company.Tools;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class Lemmatizer {

    public String lemmatizeWord(String word) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument document = new CoreDocument(word);
        stanfordCoreNLP.annotate(document);
        List<CoreLabel> tokenList = document.tokens();

        return tokenList.get(0).lemma();
    }
}
