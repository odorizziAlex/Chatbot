package com.company.Response;

import com.company.Tools.JSONHandler;

import java.util.ArrayList;
import static com.company.utils.Config.*;


public class QuestionGenerator {

    private JSONHandler jsonHandler = new JSONHandler();

    public String generateQuestion(ArrayList<Integer> positions) {
        String question = "";
        for (int i = 0; i < positions.size(); i++) {
            if (positions.contains(i)) {
                question = jsonHandler.getQuestion(RESPONSE_KEYS.get(i));
            } else {
                question = "Alright, I know anything I need to know.";
            }
        }
        return question;
    }
}
