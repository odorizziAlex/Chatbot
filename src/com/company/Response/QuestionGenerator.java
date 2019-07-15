package com.company.Response;

import com.company.Tools.JSONHandler;

import java.util.ArrayList;
import java.util.Arrays;
import static com.company.utils.Config.*;


public class QuestionGenerator {

    private JSONHandler jsonHandler = new JSONHandler();

    public String generateQuestion(ArrayList<Integer> positions){

        String q;
        if(positions.contains(DEMAND_ITEM)){
            q=jsonHandler.getRandomQuestionFromKey(JSON_ST_RES_Q_ITEM_KEY);

        }else if(positions.contains(DEMAND_COLOR)){
            q=jsonHandler.getRandomQuestionFromKey(JSON_ST_RES_Q_COLOR_KEY);

        }else if(positions.contains(DEMAND_SIZE)){
            q=jsonHandler.getRandomQuestionFromKey(JSON_ST_RES_Q_SIZE_KEY);

        }else if(positions.contains(DEMAND_FABRIC)){
            q=jsonHandler.getRandomQuestionFromKey(JSON_ST_RES_Q_FABRIC_KEY);

        }else if(positions.contains(DEMAND_PRICE)){
            q=jsonHandler.getRandomQuestionFromKey(JSON_ST_RES_Q_PRICE_KEY);

        }else if(positions.contains(DEMAND_GENDER)){
            q=jsonHandler.getRandomQuestionFromKey(JSON_ST_RES_Q_GENDER_KEY);
        }else{
            q="Alright, I know everything I need to know.";
        }
        return q;
    }

    public void answerQuestion(){
        //accept only answers that match the current question above
        //ask again if mot matched
    }
}
