import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Pol {
    public Stack<Integer> value;
    public map
    public Map<String, String> key = new HashMap<>();

    public Pol(Map<String, String> tempKey){
        this.key = tempKey;
    }

    public Pol(String var , String pow ){
        key.put(var, pow);
    }

    public void hashFunction(){
        for(Map.Entry<String, String> key : var.entrySet()){
            temp =+ key.getKey() + key.getValue();

        }
        hash = 23 * (temp);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pol other = (Pol) obj;
        if (hash != other.hash)
            return false;
        return true;
    }
}
