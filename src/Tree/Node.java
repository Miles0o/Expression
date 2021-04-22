package Tree;

public abstract class Node {
    private Node left;
    private Node right;
    private String id;

    public Node(String type){
        this.id = type;
        left = null;
        right = null;
    }

    public Node(String type, Node left, Node right){
        this.id = type;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getType() {
        return id;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setType(String type) {
        this.id = type;
    }

    public boolean equals(Node node) {
        if(this.id.equals(node.id)){
            return true;
        }
        return false;
    }

}
