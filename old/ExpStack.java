import java.util.*;

import static jdk.nashorn.internal.runtime.JSType.isNumber;

public class ExpStack {

    private ArrayList<Map<Pol, Stack<Integer>>> stack = new ArrayList<>();
    private int stackLvl;


    public ExpStack(){
        stack.add(new HashMap<Pol, Stack<Integer>>());
        stackLvl = 0;
    }

    public void addElement(expressionParser.IntContext ctx){
        Pol tempKey;
        int tempValue;
        if(isNumber(ctx.NUMBER().getText())) {
            tempKey = new Pol("num", "0");
            tempValue = Integer.valueOf(ctx.NUMBER().getText());

        }else{
            tempKey = new Pol(ctx.NUMBER().getText(), "1");
            tempValue = 1;

        }
        addElement(tempKey, tempValue);
    }


    public void addElement(Pol key, int value){
        addElement(key,value,0);
    }

    public void addElement(Pol key, int value, int offset){
        if(stack.get(stackLvl+offset).containsKey(key)){
            stack.get(stackLvl+offset).get(key).push(value);
        }else{
            Stack<Integer> tempStack = new Stack<>();
            tempStack.push(value);
            stack.get(stackLvl+offset).put(key, tempStack);
        }
    }

    public void moveElementUp(Pol key){
        addElement(key, getElement(key), -1);
    }

    public void newStack(){
        stack.add(new HashMap<Pol, Stack<Integer>>());
        stackLvl ++;
    }

    public void rmvStack(){
        stack.remove(stackLvl);
        stackLvl --;
    }


    public Map<Pol,Stack<Integer>> getStack(){
        return getStack(0);
    }
    public Map<Pol,Stack<Integer>> getStack(int offset){
        return stack.get(stackLvl+offset);
    }

    public int getElement(Pol key){
        return getStack().get(key).pop();
    }
}
