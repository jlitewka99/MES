import java.util.Arrays;

public class Element {

    private int[] id = new int[4];

    private double[][] HBC = new double[4][4];
    private double[][] H = new double[4][4];




    public Element(int n, int nH){
        id[0] = n;
        id[1] = id[0] + nH;
        id[2] = id[1] + 1;
        id[3] = id[0] + 1;
    }

    public int[] getID() {return id;}
    public int getID(int n) {return id[n-1];}
    public double[][] getH() {
        return H;
    }


    public void setH(double[][] h) {
        H = h;
    }
    public double[][] getHBC() {
        return HBC;
    }

    public void setHBC(double[][] HBC) {
        this.HBC = HBC;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + Arrays.toString(id) +
                '}';
    }
}
