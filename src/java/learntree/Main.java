package src.java.learntree;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int version = Integer.parseInt(args[0]);
        double P = Double.parseDouble(args[1]);
        int L = Integer.parseInt(args[2]);
//        String csvFile = args[3];
        String outputFileName = args[4];

//        String csvFile = "../../mini-project/mnist_test.csv";
        String csvFile = "mnist_train.csv";
        String line = "";
        String cvsSplitBy = ",";

        List<Example> trainingSet = new ArrayList<>();
        List<Example> validationSet = new ArrayList<>();

        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                trainingSet.add(new Example(line.split(cvsSplitBy)));

                System.out.println("label" + i + ": "  + trainingSet.get(i).getLabel() + ", pixel 100: " + trainingSet.get(i).getPixle(3, 15));

                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println("args[0]: " + args[0]);
        // System.out.println("args[1]: " + args[1]);
        // System.out.println("args[2]: " + args[2]);


        /*
         seperate between validation set and training set
        */
        //calculate validationSet size
        int validationSetSize = (int) (trainingSet.size()*P/100);

        for(int k=0; k < validationSetSize; k++){

            validationSet.add(trainingSet.remove((int) Math.random()*trainingSet.size()));
        }

        printArgs(args);
        System.out.println();
        System.out.println("validationSetSize: " + validationSetSize);
    }

    public static void printArgs(String[] args){

        System.out.println("Version: " + args[0]);
        System.out.println("P: " + args[1]);
        System.out.println("L: " + args[2]);
        System.out.println("data_set: " + args[3]);
        System.out.println("output file name: " + args[4]);
    }
}
