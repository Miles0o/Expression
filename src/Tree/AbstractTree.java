package Tree;

import Utility.Simplification;

public class AbstractTree {

    Node root;
    String str;

    public AbstractTree(){
    }

    public void addNode(String id){
        if(!(this.root == null)){
            recAddNode(id, root);
        }else{
            this.root = genNode(id);
        }
    }

    public boolean recAddNode(String id, Node current){
        boolean set = false;
            if(current.getLeft() == null){
                current.setLeft(genNode(id));
                return true;
            }else if(current.getLeft() instanceof OpNode) {
                set = recAddNode(id, current.getLeft());
            }
            if (!set) {
                if(current.getRight() == null){
                    current.setRight(genNode(id));
                    return true;
                }else if(current.getRight() instanceof OpNode) {
                    set = recAddNode(id, current.getRight());
                }
            }
        return set;
    }

    public Node genNode(String id){
        if(isNumeric(id)){
            return new IntNode( Integer.parseInt(id));
        }else if(id.matches("[A-Za-z]{1}")){
            return new VarNode(id);
        }else if(id.equals("(")){
            return new BracketNode();
        }else{
            return new OpNode(id);
        }
    }

    public boolean isNumeric(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public void simplify(){
        Simplification simp = new Simplification();
        this.root = simp.RecursiveSimp(this.root);
    }

    @Override
    public String toString() {
        this.str = "";
        if(this.root instanceof IntNode){
            this.str += ((IntNode) this.root).getValue();
            return this.str;
        } else if(this.root instanceof VarNode){
            this.str +=  this.root.getType();
            return this.str;
        }
        return recStr(this.root);
    }

    private String recStr(Node current) {
        try {
            Node Left = current.getLeft();
            Node Right = current.getRight();
            if (Left instanceof OpNode) {
                recStr(Left);
            } else if (Left instanceof IntNode) {
                str += ((IntNode) Left).getValue();
            }else if(Left instanceof VarNode){
                str += Left.getType();
            }
            if (current instanceof OpNode && !(current.getType().equals("()"))) {
                str += current.getType();
            }

            if (Left instanceof BracketNode) {
                str += "(";
                if (Right instanceof OpNode) {
                    recStr(Right);
                } else if (Right instanceof IntNode) {
                    str += ((IntNode) Right).getValue();
                }
                str += ")";
            } else if (Right instanceof OpNode) {
                recStr(Right);
            } else if (Right instanceof IntNode) {
                str += ((IntNode) Right).getValue();
            } else if (Right instanceof VarNode) {
                str += Right.getType();
            }

        }catch(NullPointerException e){
            System.out.println("missing node");
        }
        return str;
    }
}
