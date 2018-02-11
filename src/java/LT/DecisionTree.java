package src.java.LT;

import java.util.concurrent.locks.Condition;

/**
 * Created by blumenra on 2/11/18.
 */
public class DecisionTree {


    private Node root;


    public DecisionTree() {
        this.root = null;
    }

    public DecisionTree(int label) {
        root = new Node(label);
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