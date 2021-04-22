package Parser;// Generated from expression.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link expressionParser}.
 */
public interface expressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Brackets}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBrackets(expressionParser.BracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Brackets}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBrackets(expressionParser.BracketsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(expressionParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(expressionParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(expressionParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(expressionParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegInt}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNegInt(expressionParser.NegIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegInt}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNegInt(expressionParser.NegIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterInt(expressionParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitInt(expressionParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Power}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPower(expressionParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Power}
	 * labeled alternative in {@link expressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPower(expressionParser.PowerContext ctx);
}