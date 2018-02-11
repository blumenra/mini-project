package src.java.learntree;

public class Main {

    public static void main(String[] args) {

        int version = Integer.parseInt(args[0]);
        double P = Double.parseDouble(args[1]);
        int L = Integer.parseInt(args[2]);
        String csvFile = args[3];
        String outputFileName = args[4];
        printArgs(args);

        Data_Set trainingSet = new Data_Set(csvFile);
        Data_Set validationSet = Data_Set.CreatValidationSet(trainingSet, P);

//        System.out.println();
//        System.out.println("validationSetSize: " + validationSet.size());
//        validationSet.printLabels();
//        validationSet.printLabelInstances();
//        System.out.println();
//        trainingSet.printLabelInstances();
//        System.out.println("Most common label in traning set: " + trainingSet.getMostCommonLabel());
//        System.out.println("Most common label in validation set: " + validationSet.getMostCommonLabel());
//        for(int k=0; k < L; k++)
//            CreateDecisionTree(trainingSet, k);

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
}
