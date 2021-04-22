package Utility;

import Tree.*;

public class TreeSort {
    public Node SortExpression(Node root){

        //navigate the tree in order
        if (root.getLeft() instanceof OpNode) {
            root.setLeft(SortExpression(root.getLeft()));
        }
        if (root.getRight() instanceof OpNode) {
            root.setRight(SortExpression(root.getRight()));
        }

        //back up tree does the sort checks
        if(root.getRight().getType().equals("+")
                || root.getRight().getType().equals("*")
                || root.getRight().getType().equals("-")){
            
        }

        //if this runs theres probably been an oopsy or maybe not, its not finished
        return root;
    }
}
