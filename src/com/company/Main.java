package com.company;

import com.company.Response.QuestionGenerator;
import com.company.Response.StandardResponse;
import com.company.Tools.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.company.utils.Config.*;

public class Main {

    // Init tools:
    // Input tools:
    private static Tokenizer tokenizer = new Tokenizer();
    private static SentenceSplitter sentenceSplitter = new SentenceSplitter();
    private static POSTagger posTagger = new POSTagger();
    private static Lemmatizer lemmatizer = new Lemmatizer();
    private static Scanner scanner = new Scanner(System.in);
    private static StopWordRemover stopWordRemover = new StopWordRemover();
    // Processing tools:
    private static JSONHandler jsonHandler = new JSONHandler();
    private static DemandAnalyzer demandAnalyzer = new DemandAnalyzer();

    // init response tools:
    private static StandardResponse standardResponse = new StandardResponse();
    private static QuestionGenerator questionGenerator = new QuestionGenerator();

    //
    private static ArrayList<String> userAnswers = new ArrayList<>();
    private static ArrayList<String> input = new ArrayList<>();
    private static boolean firstIteration = true;

    // Cancellation condition
    private static Boolean isFinished = false;
/*
    TODO:       -next: standard response with JSON --> done
                    -add fabrics to color!!! --> done
                    -catch price information like "100 bucks" (euro, dollar, bucks, usd, etc.)--> done
                    -maybe ask for extras like: input: "i am looking for shoes", output: "what kind of shoes"
                    -make "no" as an answer acceptable! only item and size is obligatory! "no is stopword!!! <-- change!
                -next^2: ask for missing components --> partly done
                (-next^3: ask for extras
                -next^4: check spelling! Not autocorrect, but similarity check and understanding anyways
                -next^5: answer user questions as good as possible: input: what kind of clothes do you have?" output:"...")
 */

    public static void main(String[] args) {
        System.out.println(GREETING);

        //System.out.println("---log: Needed Components:");
        //System.out.println("---log:[gender, item, color, size, price, fabric]");
        while(!isFinished) {

            input = formattedInput();
            //System.out.println("---log: formattedInput:");
            //System.out.println("---log:"+input);

            if (firstIteration){
                demandAnalyzer.setComponent(input);
                //firstIteration = false;
            }

            //System.out.println("---log: empty demand components:");
            //System.out.println("---"+Arrays.toString(demandAnalyzer.getAllEmptyDemandComponents().toArray()));
            //System.out.println("--->"+Arrays.toString(demandAnalyzer.getDemandComponents().toArray()));

            System.out.println(standardResponse.generateStandardResponse(demandAnalyzer.getDemandComponents()));
            System.out.println(questionGenerator.generateQuestion(demandAnalyzer.getAllEmptyDemandComponents()));

        }

    }

    private static ArrayList<String> formattedInput(){
        // 1. Read user input
        String rawUserInput = scanner.nextLine(),
                //1.2 Remove special characters
                removeSpecialCharacters= rawUserInput.replaceAll("[-+^;,.!?()=§%&/]",""),
                //1.3 Every character to lower case
                finalUserInput = removeSpecialCharacters.toLowerCase();

        userAnswers.add(finalUserInput);
        // 2. Tokenize, Lemmatize, Remove Stop words of user Input
        ArrayList<String> formattedInput = tokenizer.tokenize(finalUserInput);

        //System.out.println("---log: tokenized input:");
        //System.out.println("---"+formattedInput);

        formattedInput = stopWordRemover.removeStopWords(formattedInput);

        //System.out.println("---log: stop words removed:");
        //System.out.println("---"+formattedInput);

        return formattedInput;
    }
}
