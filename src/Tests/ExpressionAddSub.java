package Tests;

import Main.ExpressionsMain;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionAddSub {

    //integer tests
    @Test
    public void invalidTest(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("-4");
        Assert.assertEquals("-4", exp1.getTree());
    }

    //integer tests
    @Test
    public void intAdd(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("4+6+5+2");
        Assert.assertEquals("17", exp1.getTree());
    }

    @Test
    public void intMinus(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("2-8");
        Assert.assertEquals("-6", exp1.getTree());
    }

    @Test
    public void intDivide(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("4/2");
        Assert.assertEquals("2", exp1.getTree());
    }

    @Test
    public void intMul(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("4*5");
        Assert.assertEquals("20", exp1.getTree());
    }

    @Test
    public void intbracket(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("(4+6)");
        Assert.assertEquals("10", exp1.getTree());
    }
    @Test
    public void intpow(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("4^4");
        Assert.assertEquals("256", exp1.getTree());
    }
    @Test
    public void negInt(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("-4");
        Assert.assertEquals("-4", exp1.getTree());
    }




    //variable tests
    @Test
    public void varAdd(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("x+x");
        Assert.assertEquals("2*x", exp1.getTree());
    }

    @Test
    public void varMinus(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("x-x");
        Assert.assertEquals("0", exp1.getTree());
    }

    @Test
    public void varDivide(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("x/x");
        Assert.assertEquals("1", exp1.getTree());
    }

    @Test
    public void varMul(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("x*x");
        Assert.assertEquals("x^2", exp1.getTree());
    }

    @Test
    public void varbracket(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("(x)");
        Assert.assertEquals("x", exp1.getTree());
    }
    @Test
    public void varpow(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("x^(2+4)");
        Assert.assertEquals("x^6", exp1.getTree());
    }

    //mixed trees
    @Test
    public void intAddWithVar(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("3+4+x");
        Assert.assertEquals("7+x", exp1.getTree());
    }
    @Test
    public void varAddWithIntnegative(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("2-x+y+x");
        Assert.assertEquals("2+y", exp1.getTree());
    }

    @Test
    public void varAddWithInt(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("x+2+x");
        Assert.assertEquals("2*x+2", exp1.getTree());
    }

    @Test
    public void intVarstrTest(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.treeMaker("x+3+4");
        Assert.assertEquals("x+3+4", exp1.getTree());
    }

    @Test
    public void varAddWithIntnegative2(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("2-x+y+x-y");
        Assert.assertEquals("2", exp1.getTree());
    }

    @Test
    public void varAddWithIntnegative3(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("2+x+y+x-y");
        Assert.assertEquals("2+2*x", exp1.getTree());
    }

    @Test
    public void addMulVar(){
        ExpressionsMain exp1 = new ExpressionsMain();

        exp1.expressionMain("2*x+2*y");
        Assert.assertEquals("2*x+2*y", exp1.getTree());
    }

    @Test
    public void addMulVarOne(){
        ExpressionsMain exp1 = new ExpressionsMain();

        exp1.expressionMain("x+2*x");
        Assert.assertEquals("3*x", exp1.getTree());
    }

    @Test
    public void addMulVarTwo(){
        ExpressionsMain exp1 = new ExpressionsMain();

        exp1.expressionMain("x*y+2*y*x");
        Assert.assertEquals("3*x*y", exp1.getTree());
    }

    @Test
    public void addMulVarThree(){
        ExpressionsMain exp1 = new ExpressionsMain();

        exp1.expressionMain("1+4*x+2*y*x+3*x*y+x+4");
        Assert.assertEquals("5+5*x+5*y*x", exp1.getTree());
    }

}
