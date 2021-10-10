import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Element {

    private int[] id = new int[4];

    public Element(int n, int nH){
        id[0] = n;
        id[1] = id[0] + nH;
        id[2] = id[1] + 1;
        id[3] = id[0] + 1;
    }

    public int[] getID() {return id;}
    public int getID(int n) {return id[n+1];}
    public int[] getId() {return id;}

    @Override
    public String toString() {
        return "Element{" +
                "id=" + Arrays.toString(id) +
                '}';
    }
}
