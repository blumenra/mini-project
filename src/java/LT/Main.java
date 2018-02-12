package src.java.LT;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException {

        int version = Integer.parseInt(args[0]);
        double P = Double.parseDouble(args[1]);
        int L = Integer.parseInt(args[2]);
        String csvFile = args[3];
        String outputFileName = args[4];
        printArgs(args);

        Data_Set trainingSet = new Data_Set(csvFile);
        Data_Set validationSet = Data_Set.CreatValidationSet(trainingSet, P);
        List<DecisionTree.Node> conditions;

        if(version == 1){

            conditions = createConditionsSetVersion1();
        }
        else if(version == 2){
//             TODO: Handle conditions for version 2
        }
        else{
            throw new IllegalArgumentException("version " + version + " is illegal. please choose version between 1/2");
        }

//        TODO: Release this loop eventually
//        for(int k=0; k < L; k++)
//          CreateDecisionTree(trainingSet, k);
          CreateDecisionTree(trainingSet, 0); //TODO: Remove this eventually for the general case of k


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

    public static DecisionTree CreateDecisionTree(Data_Set trainingSet, int k){

        DecisionTree tree = new DecisionTree(trainingSet.getMostCommonLabel());
        int T = (int) Math.pow(2, k);
        for(int i=0; i < T; i++){

        }

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
            }
        }

        return conditions;
    }
}
