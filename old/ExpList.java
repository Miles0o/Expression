import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpList {
    private Map<Pol, Stack<Integer>> expList = new HashMap<>();

    ExpList(){

    }

    public Integer GetElement(Pol key){
        return expList.get(key).pop();
    }
}
