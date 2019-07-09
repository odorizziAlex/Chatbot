package com.company;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static com.company.utils.Config.*;
public class DemandAnalyzer {

    // HashMap that stores the exact demand.
    private HashMap<Integer, String> demandComponents = new HashMap<>();

    public DemandAnalyzer(){
        initEmptyDemandList();
        //fillTestDemandList();
    }

    // Creates empty hashMap but keys are already assigned.
    // Values are still empty at this point in time. (Going to be filled later).
    private void initEmptyDemandList() {
        demandComponents.put(DEMAND_GENDER, "");
        demandComponents.put(DEMAND_ITEM, "");
        demandComponents.put(DEMAND_COLOR, "");
        demandComponents.put(DEMAND_SIZE, "");
        demandComponents.put(DEMAND_PRICE, "");
    }
/*
    private void fillTestDemandList() {
        demandComponents.put(DEMAND_GENDER, "male");
        demandComponents.put(DEMAND_ITEM, "jacket");
        demandComponents.put(DEMAND_COLOR, "black");
        demandComponents.put(DEMAND_SIZE, "56");
        demandComponents.put(DEMAND_PRICE, "199");
    }
 */

    public HashMap<Integer,String> getDemandComponents(){
        return demandComponents;
    }

    public ArrayList<Integer> getNeededDemandComponents(){
        ArrayList<Integer> emptyDemandComponents = new ArrayList<>();
        for(int i=1;i<demandComponents.size()+1;i++){
            if(demandComponents.get(i).equals("")){
                emptyDemandComponents.add(i);
            }
        }
        return emptyDemandComponents;
    }

    public void setDemandComponent(int componentType ,String component) {
        demandComponents.replace(componentType,"",component);
    }
}
