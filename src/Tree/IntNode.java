package Tree;

public class IntNode extends Node{
    private int value;

    public IntNode(int id){
        super("num");
        this.value = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

}
