package src.java.LT;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static Data_Set trainingSet;
    private static Data_Set validationSet;

    public static void main(String[] args) throws IllegalArgumentException {

        // TODO: REMOVE ME (
//        int[] arr = {0, 1, 2, 3, 4};
//        int[] copy = Arrays.copyOfRange(arr, 1, 3);
////        System.out.println("arr: " + arr.toString());
//        System.out.println("copy length: " + copy.length);
//        System.out.println("copy[0]: " + copy[0]);
//        System.out.println("copy[" + (copy.length-1) + "]: " + copy[copy.length-1]);
        // TODO: REMOVE ME )



        // TODO: change in configuration the csv file to mnist_test.csv
        // TODO: handle errors such as files that wont open, bad arguments etc.
        int version = Integer.parseInt(args[0]);
        double P = Double.parseDouble(args[1]);
        int L = Integer.parseInt(args[2]);
        String csvFile = args[3];
        String outputFileName = args[4];
        printArgs(args);

        trainingSet = new Data_Set(csvFile);
        validationSet = Data_Set.CreatValidationSet(trainingSet, P);
        List<DecisionTree.Node> conditions;

        if(version == 1){

            conditions = createConditionsSetVersion1();
        }
        else if(version == 2){
            //TODO: Handle conditions for version 2
            conditions = createConditionsSetVersion1(); //TODO: remove this line if not necessary. put it only for the code to temporary run
        }
        else{
            throw new IllegalArgumentException("version " + version + " is illegal. please choose version between 1/2");
        }

        //TODO: Release this loop eventually
//        for(int k=0; k < L; k++)
//          CreateDecisionTree(trainingSet, k);
          CreateDecisionTree(conditions,0); //TODO: Remove this eventually for the general case of k


//        DecisionTree tree = new DecisionTree(trainingSet.getMostCommonLabel());

//        Example e = trainingSet.remove(0);
//        System.out.println("e.getPixel(27, 27): " + e.getPixel(27, 27));
//        System.out.println("tree.getRoot().cond(trainingSet.get(0)) (before): " + tree.getRoot().cond(e));
//        tree.setNode(tree.getRoot(), conditions.get(conditions.size()-1));
//        System.out.println("tree.getRoot().cond(trainingSet.get(0)) (after): " + tree.getRoot().cond(e));

//        System.out.println();
//        System.out.println("validationSetSize: " + validationSet.size());
//        validationSet.printLabels();
//        validationSet.printLabelInstances();
//        System.out.println();
//        trainingSet.printLabelInstances();
//        System.out.println("Most common label in traning set: " + trainingSet.getMostCommonLabel());
//        System.out.println("Most common label in validation set: " + validationSet.getMostCommonLabel());

    }


    public static void printArgs(String[] args){

        System.out.println("Version: " + args[0]);
        System.out.println("P: " + args[1]);
        System.out.println("L: " + args[2]);
        System.out.println("data_set: " + args[3]);
        System.out.println("output file name: " + args[4]);
    }

    public static DecisionTree CreateDecisionTree(List<DecisionTree.Node> conditions,int k){

        DecisionTree tree = new DecisionTree(trainingSet.getMostCommonLabel());
        System.out.println("tree's root: " + tree.getRoot().getLabel());
        // TODO: uncomment the following..
//        int T = (int) Math.pow(2, k);
//        for(int i=0; i < T; i++){ //increase the tree to the size of T so that it has the best entropy
            tree.improve(conditions); //find the leaf and the condition to replace with one another and make the swap
//        }

        return tree;
    }

    public static List<DecisionTree.Node> createConditionsSetVersion1(){

        List<DecisionTree.Node> conditions = new ArrayList<>();

        for(int i=0; i < 28; i++){
            int[] i0 = {i};
            for(int j=0; j < 28; j++){
                int[] j0 = {j};

                conditions.add(new DecisionTree.Node() {
                    @Override
                    public boolean cond(Example e) {
                        return (e.getPixel(i0[0], j0[0]) > 128);
                    }
                });
//                conditions.add(new DecisionTree.Node() { //TODO: REMOVE ME
//                    @Override
//                    public boolean cond(Example e) {
//                        return (e.getLabel() > 5);
//                    }
//                });
            }
        }

        return conditions;
    }
}
