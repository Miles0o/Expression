package Tree;

public class OpNode extends Node{

    public OpNode(String type){
        super(type);
    }


    public OpNode(String type, Node left, Node right){
        super(type, left, right);
    }
}
