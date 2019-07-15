package com.company.Response;

import com.company.Tools.JSONHandler;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;
import java.util.Arrays;

import static com.company.utils.Config.*;


public class QuestionGenerator {

    private JSONHandler jsonHandler = new JSONHandler();

    public String generateQuestion(ArrayList<Integer> positions) {
        String question = "";
        if(positions.size() ==0){
            return COMPONENTS_READY;
        }
        for(int i = 0; i < positions.size(); i++) {
            for(int j = 0; j < COMPONENT_NUMBER; j++) {
                if(positions.contains(j)) question = jsonHandler.getQuestion(RESPONSE_KEYS.get(j));
            }
        }
        return question;
    }
}
