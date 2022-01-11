package Mes;

import java.util.Arrays;

import Algorithms.Utilities;

public class Element {

    /**
     * Fields automatically created by constructor
     */

    /**
     * id - array of 4 IDs of nodes
     */
    private int[] id = new int[4];

    /**
     * Fields that are set by the setters, and stored in each {@link Element}
     */

    //TODO: write what HBC and H matrix is
    /**
     * HBC - 2D 4x4 matrix
     */
    private double[][] HBC = new double[4][4];
    /**
     * H - 2D 4x4 matrix
     */
    private double[][] H = new double[4][4];


    /**
     * Constructor - creates element with IDs of nodes
     *
     * @param n  ID of bottom left node
     * @param nH number of horizontal nodes
     */
    public Element(int n, int nH) {
        id[0] = n;
        id[1] = id[0] + nH;
        id[2] = id[1] + 1;
        id[3] = id[0] + 1;
    }

    /**
     * Getters
     */

    /**
     * Getter of IDs matrix
     *
     * @return {@link Element#id}
     */
    public int[] getID() {
        return id;
    }

    /**
     * Returns specific ID by index (1 to 4)
     *
     * @param n - index of wanted ID (1-4)
     * @return {@link Element#id}
     */
    public int getID(int n) {
        return id[n - 1];
    }

    /**
     * Getter of H local matrix
     *
     * @return {@link Element#H}
     */
    public double[][] getH() {
        return H;
    }

    /**
     * Getter of HBC matrix
     *
     * @return {@link Element#HBC}
     */
    public double[][] getHBC() {
        return HBC;
    }

    /**
     * Setters
     */

    /**
     * Setter of H local matrix
     *
     * @param H {@link Element#H}
     */
    public void setH(double[][] H) {
        this.H = H;
    }

    /**
     * Setter of HBC matrix
     *
     * @param HBC {@link Element#HBC}
     */
    public void setHBC(double[][] HBC) {
        this.HBC = HBC;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + Arrays.toString(id) +
//                ",\nHBC=\n" + Arrays.toString(HBC[0]) + "\n" + Arrays.toString(HBC[1]) + "\n" + Arrays.toString(HBC[2]) + "\n" + Arrays.toString(HBC[3]) + "\n" +
//                ",\nH=\n" + Arrays.toString(H[0]) + "\n" + Arrays.toString(H[1]) + "\n" + Arrays.toString(H[2]) + "\n" + Arrays.toString(H[3]) + "\n" +
                "\nHBC=\n" + Utilities.getArrayString(HBC) +
                "\nH=\n" + Utilities.getArrayString(H) +
                "}\n";
    }
}
