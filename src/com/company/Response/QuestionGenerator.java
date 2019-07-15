package com.company.Response;

import com.company.Tools.JSONHandler;

import java.util.ArrayList;
import static com.company.utils.Config.*;


public class QuestionGenerator {

    private JSONHandler jsonHandler = new JSONHandler();

    public String generateQuestion(ArrayList<Integer> positions){

        String question = "";

        if(positions.contains(DEMAND_ITEM)){
            question = jsonHandler.getQuestion(JSON_ST_RES_Q_ITEM_KEY);

        }else if(positions.contains(DEMAND_COLOR)){
            question = jsonHandler.getQuestion(JSON_ST_RES_Q_COLOR_KEY);

        }else if(positions.contains(DEMAND_SIZE)){
            question = jsonHandler.getQuestion(JSON_ST_RES_Q_SIZE_KEY);

        }else if(positions.contains(DEMAND_FABRIC)){
            question = jsonHandler.getQuestion(JSON_ST_RES_Q_FABRIC_KEY);

        }else if(positions.contains(DEMAND_PRICE)){
            question = jsonHandler.getQuestion(JSON_ST_RES_Q_PRICE_KEY);

        }else if(positions.contains(DEMAND_GENDER)){
            question = jsonHandler.getQuestion(JSON_ST_RES_Q_GENDER_KEY);
        }else{
            question ="Alright, I know anything I need to know.";
        }
        return question ;
    }
}
