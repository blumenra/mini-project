package src.java.LT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by blumenra on 2/11/18.
 */
public class Data_Set {

    private String csvFile;
    private List<Example> examples = new ArrayList<>();
    private Map<Integer, Integer> labelsInstances = new HashMap<>();

    public List<Example> getExamples() {

        return examples;
    }

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
        String[] exampleStr;
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.csvFile))) {

            while ((line = br.readLine()) != null) {

                System.out.println("Example number " + (i+1));
                // use comma as separator
//                System.out.println("exampleStr length: " + exampleStr.length);

//                this.examples.add(new Example(i+1, label, this.csvFile));
                this.examples.add(new Example(i+1, this.csvFile, line, 200));
                accLabelCount(this.examples.get(this.examples.size()-1).getLabel());
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

        printLabelInstances();
        int ans = 0;
        int maxValue = 0;
        int value;
        int  index = 0;
        for(Integer label : this.labelsInstances.keySet()){

            value = this.labelsInstances.get(label);
            if(maxValue < value){
                maxValue = value;
                ans = index;
            }

            index++;
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

    public void loadPixelsFrom(int i, int j){

        int pixelIndexInArray = (i*28)+j+1;
        String strExample;
        int k = 0;
        int l = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.csvFile))) {
//
            while ((strExample = br.readLine()) != null) {

                Example e = this.examples.get(l);
                if(e.getIndex() == k){

                    e.setPixelIndex(pixelIndexInArray);
                    int indexOfLastPixel = strExample.length() - 2;

                    int indexOfLast = e.getPixelIndex() + e.getChunkSize();
                    if(indexOfLast > indexOfLastPixel){

                        e.InitializePixels(strExample, e.getPixelIndex(), indexOfLastPixel+1);
                    }
                    else{
                        e.InitializePixels(strExample, e.getPixelIndex(), indexOfLast);
                    }

                    l++;
                    if(l >= this.examples.size())
                        return;

                }

                k++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    public void updatePixles(int index){
//
//
//        String line;
//        String[] exampleStr;
//        int i = 0;
//        try (BufferedReader br = new BufferedReader(new FileReader(this.csvFile))) {
//
//            while ((line = br.readLine()) != null) {
//
////                System.out.println("Example number " + (i+1));
////                // use comma as separator
//////                System.out.println("exampleStr length: " + exampleStr.length);
////
//
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
