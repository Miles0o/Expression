package Tests;

import Main.ExpressionsMain;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExpressionAddPow {

    //simple add power
    @Test
    public void invalidTest(){
        ExpressionsMain exp1 = new ExpressionsMain();
        exp1.expressionMain("x^2+x^2");
        Assert.assertEquals("2*x^2", exp1.getTree());
    }
}
