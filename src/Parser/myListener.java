package Parser;

import Tree.AbstractTree;

public class myListener extends expressionBaseListener {

    private AbstractTree tree = new AbstractTree();

    public AbstractTree getTree() {
        return tree;
    }
    @Override
    public void enterInt(expressionParser.IntContext ctx) {
        if(ctx.VAR() == null){
            tree.addNode(ctx.NUMBER().getText());
        } else{
            tree.addNode(ctx.VAR().getText());
        }

    }

    @Override
    public void enterAddSub(expressionParser.AddSubContext ctx) {
        if(ctx.op.getType() == expressionParser.PLUS){
            tree.addNode("+");
        }else{
            tree.addNode("-");
        }
    }

    @Override
    public void enterMulDiv(expressionParser.MulDivContext ctx) {
        if(ctx.op.getType() == expressionParser.MUL){
            tree.addNode("*");
        }else{
            tree.addNode("/");
        }
    }

    @Override
    public void enterPower(expressionParser.PowerContext ctx) {
        tree.addNode("^");
    }

    @Override
    public void enterBrackets(expressionParser.BracketsContext ctx) {
        tree.addNode("()");
        tree.addNode("(");
    }


    @Override public void enterNegInt(expressionParser.NegIntContext ctx) {
        tree.addNode("-");
        tree.addNode("0");
    }
}


