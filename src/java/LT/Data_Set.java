package src.java.LT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blumenra on 2/11/18.
 */
public class Data_Set {

    private String csvFile;
    private List<Example> examples = new ArrayList<>();
    private Map<Integer, Integer> labelsInstances = new HashMap<>();

    public Data_Set(String csvFile){

        this.csvFile = csvFile;
        this.initialize();

    }

    public Data_Set(){
        this.initializeLabelsInstacesCount();
    }

    private void initialize(){

        this.initializeLabelsInstacesCount();

        String line;
        String cvsSplitBy = ",";

        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] exampleStr = line.split(cvsSplitBy);
                int label = Integer.parseInt(exampleStr[0]);
                this.examples.add(new Example(i+1, label, this.csvFile));
                accLabelCount(label);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeLabelsInstacesCount(){

        for(int i=0; i < 10; i++){
            this.labelsInstances.put(i, 0);
        }
    }

    private void accLabelCount(int label){

        int count = this.labelsInstances.get(label);
        this.labelsInstances.put(label, count+1);
    }

    private void decLabelCount(int label){

        int count = this.labelsInstances.get(label);
        if(count > 0)
            this.labelsInstances.put(label, count-1);
    }

    public void printLabels(){

        for(Example e : examples){

            System.out.println("index: " + e.getIndex() + ", " + "label: "  + e.getLabel());
        }
    }

    public int size(){

        return examples.size();
    }

    public Example remove(int i){

        decLabelCount(examples.get(i).getLabel());

        return examples.remove(i);
    }

    public Boolean add(Example e){

        boolean wasAdded = false;
        if(!this.examples.contains(e)){

            wasAdded = examples.add(e);
            accLabelCount(e.getLabel());
        }

        return wasAdded;
    }

    public boolean containsIndex(int index) {

        boolean ans = false;
        for(Example e : this.examples){
            if(e.getIndex() == index)
                ans = true;
        }

        return ans;
    }

    /*
      separates between validation set and training set
    */
    public static Data_Set CreatValidationSet(Data_Set trainingSet, double P){


        Data_Set validationSet = new Data_Set();

        //calculate validationSet size
        int validationSetSize = (int) (trainingSet.size()*P/100);

        for(int k=0; k < validationSetSize; k++){

            int trainingSetSize = trainingSet.size();

            int randomNum;
            do{
                randomNum = ((int) Math.floor(Math.random()*trainingSetSize));
            }
            while(validationSet.containsIndex(randomNum));

            validationSet.add(trainingSet.remove(randomNum));
        }

        return validationSet;
    }

    public int getMostCommonLabel(){

        int ans = 0;
        int value;
        for(Integer label : this.labelsInstances.keySet()){

            value = this.labelsInstances.get(label);
            if(value > ans){
                ans = value;
            }
        }

        return ans;
    }

    public void printLabelInstances(){

        int totalInstances = 0;
        for(Integer label : this.labelsInstances.keySet()){

            int value = this.labelsInstances.get(label);
            System.out.println("Number of instances of label " + label + ": " + value);
            totalInstances += value;
        }

        System.out.println("Total amount of instances: " + totalInstances);
    }
}
