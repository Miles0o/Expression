package Main;

import Tree.AbstractTree;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import Parser.*;

// TODO VARIABLES + -
// TODO mull divide */
// TODO brackets

public class ExpressionsMain {

    private AbstractTree abTree = new AbstractTree();

    public void expressionMain(String expression){

        String inputStr = expression;
        //input stream of the thing we want to parse
        CharStream input = CharStreams.fromString(inputStr);

        //generate the lexer giving the set of rules applied to the input stream
        expressionLexer lexer = new expressionLexer(input);

        //using the lexer making the individual tokes for the tree
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //parser apple's rules to the tokens
        expressionParser parser = new expressionParser(tokens);

        //generate the tree according to the exp grammar specified
        expressionParser.ExpContext tree = parser.exp();

        //listener apples methods to when calls are made on the tree
        myListener extractor = new myListener();

        //walks the tree applying the rules
        ParseTreeWalker.DEFAULT.walk(extractor, tree);

        abTree = extractor.getTree();
        System.out.println( "Inputted expression: "+abTree );
        abTree.simplify();
        System.out.println("Outputted expression: "+abTree);
    }

    public void treeMaker(String expression){
        String inputStr = expression;
        //input stream of the thing we want to parse
        CharStream input = CharStreams.fromString(inputStr);

        //generate the lexer giving the set of rules applied to the input stream
        expressionLexer lexer = new expressionLexer(input);

        //using the lexer making the individual tokes for the tree
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //parser apple's rules to the tokens
        expressionParser parser = new expressionParser(tokens);

        //generate the tree according to the exp grammar specified
        expressionParser.ExpContext tree = parser.exp();

        //listener apples methods to when calls are made on the tree
        myListener extractor = new myListener();

        //walks the tree applying the rules
        ParseTreeWalker.DEFAULT.walk(extractor, tree);
        abTree = extractor.getTree();
        System.out.println( "expression: "+abTree );
    }

    public String getTree(){
        return abTree.toString();
    }
}
