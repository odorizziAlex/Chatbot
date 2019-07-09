package com.company;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static com.company.utils.Config.*;
public class DemandAnalyzer {

    // HashMap that stores the exact demand.
    private ArrayList<String> demandComponents = new ArrayList<>();

    public DemandAnalyzer(){
        initEmptyDemandList();
    }

    // Creates empty hashMap but keys are already assigned.
    // Values are still empty at this point in time. (Going to be filled later).
    private void initEmptyDemandList() {
        for(int i=0;i<COMPONENT_NUMBER;i++){
            demandComponents.add("");
        }
    }

    public ArrayList<String> getDemandComponents(){
        return demandComponents;
    }

    // Returns List of components, that are still empty.
    public ArrayList<Integer> getAllNeededDemandComponents(){
        ArrayList<Integer> emptyDemandComponents = new ArrayList<>();
        for(int i=0;i<demandComponents.size();i++){
            if(demandComponents.get(i).equals("")){
                emptyDemandComponents.add(i);
            }
        }
        return emptyDemandComponents;
    }

    public String getEmptyComponent(int index){
        return demandComponents.get(index);
    }

    public void setDemandComponent(int componentType ,String component) {
        demandComponents.set(componentType, component);
    }
}
