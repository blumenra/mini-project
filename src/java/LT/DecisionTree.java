package src.java.learntree;

/**
 * Created by blumenra on 2/11/18.
 */
public class DecisionTree {


    private Node root;
    private

    public DecisionTree() {
        this.root = null;
    }

    public DecisionTree(int label) {
//        root = new Node();
//        root.data = rootData;
//        root.children = new ArrayList<Node>();
    }

    public static class Node {
        private Condition cond = null;
        private int label;
        private Node parent = null;
        private Node leftChild = null;
        private Node rightChild = null;

        public Node(int label){
            this.label = label;
        }

        public boolean isLeaf(){
            return (leftChild == null) && (rightChild == null);
        }
    }
}