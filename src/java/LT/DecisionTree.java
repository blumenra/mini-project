package src.java.LT;

import java.util.*;

/**
 * Created by blumenra on 2/11/18.
 */
public class DecisionTree {


    private Node root;
    private Map<Node, Node> leaves;

    public DecisionTree() {
        this.root = null;
    }

    public DecisionTree(int label) {
        this.root = new Node(label);
        this.leaves = new HashMap<>();
        this.leaves.put(this.root, this.root);
//        root.data = rootData;
//        root.children = new ArrayList<Node>();
    }

    public Node getRoot(){

        return this.root;
    }

    public void setNode(Node node, Node other){

        if(node.isRoot())
            this.root = other;

        other.copy(node);

        if(node.isLeaf())
            removeFromLeavesList(node);

        addToLeavesList(other.getLeftChild());
        addToLeavesList(other.getRightChild());
    }

    private void addToLeavesList(Node leaf){

        if(leaf != null && leaf.isLeaf() && !this.leaves.containsKey(leaf)){
            this.leaves.put(leaf, leaf);
        }
    }

    private void removeFromLeavesList(Node leaf){

        if(leaf != null && leaf.isLeaf() && this.leaves.containsKey(leaf)){
            this.leaves.remove(leaf);
        }
    }

    public void improve(Data_Set trainingSet, List<Node> conditions) {

        // TODO: find the leaf and condition which create the best improvement
        if(conditions == null){

            System.out.println("No conditions were found");
            return;
        }
        if(this.leaves == null){

            System.out.println("No leaves were found");
            return;
        }

        Node bestLeaf = this.leaves.get(0);
        Node bestCond = conditions.get(0);
        double bestIG = this.calcIG(trainingSet, bestCond, bestLeaf);; // TODO: not sure if IG supposed to be double or float. have to check both cases and pick the better one

        double IG;
        for(Node cond : conditions){
            // TODO: maybe I should check if cond == bestCond (for the first iteration) to avoid calculating for it because it was already done before the loop
//            for(Node leaf : this.leaves){
            for(Node leaf : this.leaves.keySet()){
                // TODO: maybe I should check if leaf == bestLeaf (for the first iteration) to avoid calculating for it because it was already done before the loop
                IG = this.calcIG(trainingSet, cond, leaf); // TODO: not sure if IG supposed to be double or float. have to check both cases and pick the better one
                if(IG > bestIG){
                    bestLeaf = leaf;
                    bestCond = cond;
                    bestIG = IG;
                }
            }
        }

        /*
        At this point we found the leaf and the condition (and their entropy) which create the best entropy
        They are stored in:
            bestLeaf
            bestCond
            bestIG
         */

        // TODO: find the left new son for the above condition
        // TODO: find the right new son for the above condition
        List<Example> N_Set = this.retrieveN_Set(trainingSet, bestLeaf);
        List<Example> N_LeftChild_Set = new ArrayList<>();
        List<Example> N_RightChild_Set = new ArrayList<>();
        for(Example e : N_Set){

            if(bestCond.cond(e)) {
                N_LeftChild_Set.add(e);
            }
            else {
                N_RightChild_Set.add(e);
            }
        }


        // TODO: replace the above leaf with the above condition
        this.setNode(bestLeaf, bestCond);


        bestCond.leftChild = new Node(Example.most_appeared_digit(N_LeftChild_Set));
        bestCond.rightChild = new Node(Example.most_appeared_digit(N_RightChild_Set));
        addToLeavesList(bestCond.leftChild);
        addToLeavesList(bestCond.rightChild);
    }

    /*
    Calculate the IG for @cond and @leaf according to the formula from the project:
    IG(X, L) = H(L) - H(X)
     */
    private double calcIG(Data_Set trainingSet, Node cond, Node leaf) {

        return this.calcH_Leaf(trainingSet, leaf) - this.calcH_Cond(trainingSet, cond);
    }

    /*
    Calculate the H for @node according to the formula from the project of H(L) (if L is a leaf) and H(X) (if X is an inner node)
     */
    private double calcH_Leaf(Data_Set trainingSet, Node leaf) {

        double H = 0;
        if(leaf.isLeaf()){

            double N_L = this.calcN(trainingSet, leaf);
            double Ni_L;

            // H(L) = ...
            for(int i=0; i < this.leaves.size(); i++){

                Ni_L = this.calcN(trainingSet, leaf, i);

                H += (Ni_L/N_L)*(this.log(2, N_L/Ni_L));
            }
        }
        else
            System.out.println("Inside 'calcH_Leaf()': The node is not a leaf!");

        return H;
    }


    private double calcH_Cond(Data_Set trainingSet, Node cond) {

        List<Example> N_Set = this.retrieveN_Set(trainingSet, cond);
        double N_L = N_Set.size();

        List<Example> N_SetA = new ArrayList<>();
        List<Example> N_SetB = new ArrayList<>();

        for(Example example : N_Set){
            if(cond.cond(example)){
                N_SetA.add(example);
            }
            else {
                N_SetB.add(example);
            }
        }


        // H(La) = ...
        // H(Lb) = ...
        double H_La = 0;
        double H_Lb = 0;

        double N_La = N_L + N_SetA.size();
        double Ni_La = 0;
        double N_Lb = N_L + N_SetB.size();
        double Ni_Lb = 0;
        for(int i=0; i < this.leaves.size(); i++){

            for(Example e : N_SetA){
                if(e.getLabel() == i) {
                    Ni_La++;
                }
            }
            for(Example e : N_SetB){
                if(e.getLabel() == i) {
                    Ni_Lb++;
                }
            }

            H_La += (Ni_La/N_La)*(this.log(2, N_La/Ni_La));
            H_Lb += (Ni_Lb/N_Lb)*(this.log(2, N_Lb/Ni_Lb));
        }

        // return H(X)
        return ((N_SetA.size()/N_L)*H_La) + ((N_SetB.size()/N_L)*H_Lb);
    }

    private double log(double base, double x) {

        return (Math.log(x))/(Math.log(base));
    }



    /*
    Returns the number of examples in @trainingSet that get to @leaf when starting from the root of the tree
     */
    private double calcN(Data_Set trainingSet, Node leaf) {

        return this.retrieveN_Set(trainingSet, leaf).size();
    }


    /*
    Returns a list of all the examples in @trainingSet that get to @leaf when starting from the root of the tree
     */
    private List<Example> retrieveN_Set(Data_Set trainingSet, Node leaf) {

        List<Example> N_Set = new ArrayList<>();

        Node destLeaf;
        for(Example example : trainingSet.getExamples()){

            destLeaf = this.execOn(example);
            if(destLeaf == leaf)
                N_Set.add(example);
        }

        return N_Set;
    }


    /*
    Returns the leaf that that the example gets to, after running it on the tree from the root
     */
    private Node execOn(Example example) {

        Node node = this.root;

        while(!node.isLeaf()){

            if(node.cond(example))
                node = node.getLeftChild();
            else
                node = node.getRightChild();
        }

        return node;
    }

    /*
    Number of examples in the training set that get to @leaf when starting from the root of the tree and their label is @i
     */
    private double calcN(Data_Set trainingSet, Node leaf, int i) {

        return this.retrieveN_Set(trainingSet, leaf, i).size();
    }


    /*
    Returns a list of all the examples in @trainingSet that get to @leaf when starting from the root of the tree
    and(!!) and their labels are equals @i
     */
    private List<Example> retrieveN_Set(Data_Set trainingSet, Node leaf, int i) {

        List<Example> N_Set = this.retrieveN_Set(trainingSet, leaf);
        List<Example> Ni_Set = new ArrayList<>();

        for(Example example : N_Set){

            if(example.getLabel() == i)
                Ni_Set.add(example);
        }

        return Ni_Set;
    }

    public static class Node {

//        private Condition cond = null;
        private int label;
        private Node parent = null;
        private Node leftChild = null;
        private Node rightChild = null;

        public Node(int label){
            this.label = label;
        }

        public Node() {

        }

        public int getLabel() {
            return label;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public boolean isLeaf(){
            return (leftChild == null) && (rightChild == null);
        }

        public boolean cond(Example e) {
//            return Boolean.parseBoolean(null);
            return true;
        }

        public boolean isRoot(){
            return (this.parent == null);
        }

        public boolean isLeftChild(Node node){
            return node == this.getLeftChild();
        }

        public void copy(Node node){
            this.label = node.getLabel();
            this.parent = node.getParent();
            this.leftChild = node.getLeftChild();
            this.rightChild = node.getRightChild();
            if(node.getParent() != null){
                if(node.getParent().isLeftChild(node))
                    node.getParent().leftChild = this;
                else
                    node.getParent().rightChild = this;
            }
        }
    }
}