package Utility;

import Tree.*;

import java.util.HashSet;
import java.util.Set;

public class Simplification {

    //WALKER
    public Node RecursiveSimp(Node current) {
        if (current.getLeft() instanceof OpNode) {
            current.setLeft(RecursiveSimp(current.getLeft()));
        }
        if (current.getRight() instanceof OpNode) {
            current.setRight(RecursiveSimp(current.getRight()));
        }
        switch(current.getType()) {
            case "+":
            case "-":
                return addSubHandler(current);

            case "*":
            case "/":
                return mulDivHandler(current);

            case "^":
                return pow(current.getLeft(), current.getRight());

            case "()":
                return bracket(current);

        }
        System.out.println("error, undefined operation found");
        return current;
    }

    //HANDLERS

    //addSub handler
    private Node addSubHandler(Node current){
        Node temp = null;
        //bottom of the tree check
        if(compSub(current.getLeft(), current.getRight())
                || (current.getRight().getType().equals("num")
                && current.getLeft().getType().equals("num"))){

            if( current.getType().equals("+") )
                temp = add(current.getLeft(), current.getRight());
            else
                temp = sub(current.getLeft(), current.getRight());

        }
        //checks if there could be like terms to gather
        else if(current.getLeft().getType().equals("+")
                || current.getLeft().getType().equals("-"))
            temp = recAddSub(current.getType(), current, current.getLeft());
        if(temp == null)
            return current;
        return temp;
    }

    //mulDiv handler
    private Node mulDivHandler(Node current){
        Node temp;

        if(current.getType().equals("*"))
            temp = mul(current.getLeft(), current.getRight());
        else
            temp = div(current.getLeft(), current.getRight());

        if(temp == null)
            return current;
        return temp;
    }


    //RECURSIVE RULES

    private Node recAddSub(String sign, Node target, Node current){
        Node temp;
        //check for like term on the right of the current node
        if(compSub(target.getRight(), current.getRight())){
            if(target.getRight().getType().equals("*")
                    || current.getRight().getType().equals("*"))
                current = addMul(sign, target, current.getType(), current, false);
            else
                current = addSub(sign, target, current.getType(), current, false);
            return current;

        }
        //moves down a layer in the tree
        else if(current.getLeft() instanceof OpNode){
            temp = recAddSub(sign, target, current.getLeft());
            if(temp != null)
                current.setLeft(temp);
            return current;

        }
        //check for like terms on the left hand side at the base of the branch
        else if ( compSub(target.getRight(), current.getLeft())){
            if(target.getRight().getType().equals("*"))
                current = addMul(sign, target, current.getType(), current, true);
            else
                current = addSub(sign, target, current.getType(),current, true);
            return current;
        }else if(compSub(target, current)){
            current = addMul(sign, target, current.getType(), current, true);
            return current;
        }
        return null;
    }

    //RULES

    //addition and subtraction
    //enters 'if' if their the same type
    private Node addSub(String sign1,Node target, String sign2, Node current, Boolean left){
        if (target.getRight() instanceof IntNode) {
            int num1 = ((IntNode) target.getRight()).getValue();
            int num2;

            if(!left)
                num2 = ((IntNode) current.getRight()).getValue();
            else
                num2 = ((IntNode) current.getLeft()).getValue();


            if(sign1.equals("-"))
                num1 = -num1;
            if(sign2.equals("-"))
                num2 = -num2;


            num1 = num1 + num2;

            if(num1 >= 0)
                current.setType("+");
            else
                current.setType("-");

            if(!left)
                current.setRight(new IntNode(num1));
            else
                current.setLeft(new IntNode(num1));

            return current;

        }else{
            if((sign1.equals("+") && sign2.equals("-"))
                    || (sign1.equals("-") && sign2.equals("+"))){
                current.setType("+");
                if(!(current.getLeft() instanceof OpNode)){
                    if (!left)
                        return current.getLeft();
                     else
                        return current.getRight();

                }else {
                    if (!left)
                        current.setRight(new IntNode(0));
                     else
                        current.setLeft(new IntNode(0));
                    return current;
                }
            }else{
                if(sign1.equals("+"))
                    current.setType("+");
                else
                    current.setType("-");
                if(!left)
                    current.setRight(new OpNode("*", new IntNode(2), target.getRight()));
                else
                    current.setLeft(new OpNode("*", new IntNode(2), target.getRight()));
                return current;
            }
        }
    }

    //TODO finish grouping like terms1
    //TODO compare passes in current node and target but in a base case it should be target and target
    //rule for adding multiples together
    private Node addMul(String sign1,Node target, String sign2, Node current, Boolean left){
        Node temp;
        int value;

        if(!left){
            temp = current.getRight();
        }else {
            temp = current.getLeft();
        }
        if (target.getRight() instanceof VarNode
                && ((current.getRight() instanceof VarNode && !left)
                || (current.getLeft() instanceof VarNode && left))) {
                if (sign1.equals("+") && sign2.equals("+")) {
                    return new OpNode("*", new IntNode(2), new VarNode(current.getRight().getType()));
                } else if (sign1.equals("-") && sign2.equals("-")) {
                    return new OpNode("*", new IntNode(2), new VarNode(current.getRight().getType()));
                }
            return new IntNode(0);
        } if(sign1.equals(sign2)){

            value = getNum(temp).getValue() + getNum(target.getRight()).getValue();
            if( value < 0 )
                current.setType("-");
            else
                current.setType("+");
            addInt(Math.abs(value), temp);
            return current;
        }
        return current;
    }

    //changing the compare function to compare powers
    private Node addPow(String sign1,Node target, String sign2, Node current, Boolean left){
        Node temp;
        int value;

        if(!left){
            if(!compSub(target.getRight(), current.getRight())){
                return null;
            }
            temp = current.getRight();
        }else {
            if(!compSub(target.getRight(), current.getRight())){
                return null;
            }
            temp = current.getLeft();
        }


        return null;
    }

    //base add rule
    private Node add(Node v1, Node v2) {
        if (v1 instanceof IntNode) {
            return new IntNode(((IntNode) v1).getValue() + ((IntNode) v2).getValue());
        } else if (v1 instanceof VarNode && v2 instanceof VarNode) {
            return new OpNode("*", new IntNode(2), new VarNode(v2.getType()));
        } else if (compSub(v1, v2)) {
            return addInt((getNum(v1).getValue() + getNum(v2).getValue()), v1);
        }
        return null;
    }

    //base subtraction rule
    private Node sub(Node v1, Node v2) {
        if (v1 instanceof IntNode) {
            return new IntNode(((IntNode) v1).getValue() - ((IntNode) v2).getValue());
        } else if (v1 instanceof VarNode && v2 instanceof VarNode) {
            return new IntNode(0);
        } else if (compSub(v1, v2)) {
            return addInt((getNum(v1).getValue() - getNum(v2).getValue()), v1);
        }
        return null;
    }

    //multiplication and division
    private Node mul(Node v1, Node v2) {
        if (v1 instanceof IntNode
                && v2 instanceof IntNode) {
            return new IntNode(((IntNode) v1).getValue() * ((IntNode) v2).getValue());
        } else if(v1 instanceof VarNode
                && v1.getType().equals(v2.getType())){
            return new OpNode("^", new VarNode(v1.getType()), new IntNode(2));
        }
        return null;
    }

    //divide
    private Node div(Node v1, Node v2) {
        if (v1 instanceof IntNode) {
            if(v2 instanceof IntNode){
                return new IntNode(((IntNode) v1).getValue() / ((IntNode) v2).getValue());
            }else{
                return new OpNode("/", new IntNode(((IntNode) v1).getValue()), v2);
            }
        } else {
            if(v2 instanceof IntNode){
                return new OpNode("/", v1, new IntNode(((IntNode) v2).getValue()));
            }else{
                if(v1.getType().equals(v2.getType())){
                    return new IntNode(1);
                }else{
                    return new OpNode("/", v1, v2);
                }
            }
        }
    }

    //power
    private Node pow(Node v1, Node v2) {
        if (v1 instanceof IntNode && v2 instanceof IntNode) {
            return new IntNode((int) Math.pow(((IntNode) v1).getValue(), ((IntNode) v2).getValue()));
        }else {
            return new OpNode("^", v1, v2);
        }
    }

    //brackets
    private Node bracket(Node v1) {
        if (v1.getRight() instanceof IntNode || v1.getRight() instanceof VarNode) {
            return v1.getRight();
        }
        return v1;
    }

    //used to compare two subtrees, not including the value of integer nodes
    private Boolean compSub(Node node1, Node node2){
        Set<String> vars1 = new HashSet<>();
        Set<String> vars2 = new HashSet<>();

        getVars(node1, vars1);
        getVars(node2, vars2);

        if(node1 instanceof IntNode && node2 instanceof IntNode)
            return true;
        else if(vars1.isEmpty() || vars2.isEmpty())
            return false;

        for(String var : vars1){
            if(!vars2.contains(var))
                return false;
            else
                vars2.remove(var);
        }
        return vars2.isEmpty();
    }

    private Set<String> getVars(Node node, Set<String> vars){
        if (node instanceof VarNode) {
            vars.add(node.getType());
            return vars;
        }
        if (node.getType().equals("*")) {
            vars.add(node.getRight().getType());
            if (node.getLeft().getType().equals("*"))
                return getVars(node.getLeft(), vars);
            else if (node.getLeft() instanceof VarNode)
                vars.add(node.getLeft().getType());
        }
        else if(node.getType().equals("^")){

        }
        return vars;
    }

    private Node addInt (int value, Node node) {
        Node temp;
        if (!(node instanceof VarNode) && node.getLeft().getType().equals("*")) {
            addInt(value, node.getLeft());
        }else if(node.getLeft() instanceof IntNode){
            ((IntNode) node.getLeft()).setValue(value);
        }else if (node instanceof VarNode) {
            temp = node;
            node = new OpNode("*", new IntNode(value), temp);
        } else {
            temp = node.getLeft();
            node.setLeft(new OpNode("*", new IntNode(value), temp));
        }
        return node;
    }

    private IntNode getNum(Node node){
        if(node instanceof IntNode){
            return  (IntNode) node;
        } else if(node instanceof VarNode){
            return new IntNode(1);
        }else if (node.getLeft() instanceof IntNode) {
            return (IntNode) node.getLeft();
        } else if (node.getRight() instanceof IntNode) {
            return (IntNode) node.getRight();
        } else{
            return getNum(node.getLeft());
        }
    }
}
