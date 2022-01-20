package BaseElements;

import DataHolders.Params;
import Algorithms.Algorithms;
import Algorithms.HelpingElements.Element4_2D;
import org.apache.commons.math3.linear.*;

import Algorithms.Utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    /**
     * Fields that are passed as argument by the user
     */

    /**
     * H - height of grid
     */
    private double H;

    /**
     * B - breadth of grid (width)
     */
    private double B;

    /**
     * nH - number of horizontal nodes
     */
    private int nH;
    /**
     * nB - number of landscape nodes
     */
    private int nB;

    /**
     * Fields that are calculated by passed arguments
     */
    /**
     * element4_2D
     */
    private Element4_2D element4_2D;
    /**
     * nN - number of nodes
     */
    private int nN;
    /**
     * nE - number of elements
     */
    private int nE;

    /**
     * deltaX - distance between x-axis nodes
     */
    private double deltaX;
    /**
     * deltaY - distance between y-axis nodes
     */
    private double deltaY;

    /**
     * HGlobal - global H matrix (nN x nN)
     */
    private double[][] HGlobal;
    /**
     * CGlobal - global C matrix (nN x nN)
     */
    private double[][] CGlobal;
    /**
     * PGlobal - global P matrix (nN x nN)
     */
    private double[] PGlobal;

//    private double[] t1;

    /**
     * nodes - list of all nodes
     */
    private ArrayList<Node> nodes = new ArrayList<Node>();
    /**
     * elements - list of all elements
     */
    private ArrayList<Element> elements = new ArrayList<Element>();

    /**
     * Grid will be H x B and nH x nB nodes
     * params used to create grid:
     * H                  height of grid (centimeters or other unit of distance)
     * B                  breadth of grid (centimeters or other unit of distance)
     * nH                 number of <b>vertical nodes</b>
     * (that means that number of <b>vertical elements</b> will be <i>nH-1</i>)
     * nB                 number of <b>horizontal nodes</b>
     * (that means that number of <b>horizontal elements</b> will be <i>nB-1</i>)
     * initialTemperature
     *
     * @param params
     */
    public Grid(Params params) {

        element4_2D = new Element4_2D();


        H = params.H();
        B = params.B();
        nH = params.nH();
        nB = params.nB();
        double initialTemperature = params.initialTemperature();
        // calculating the gap between the nodes
        //
        // for example grid is 3cm x 2cm and 6 x 8 elements
        // that means that gap will be 0.5cm and 0.25 cm
        this.deltaX = B / (nB - 1);
        this.deltaY = H / (nH - 1);


        // calculating nN (number of nodes)
        // and nE (number of elements)
        nN = nH * nB;
        nE = (nH - 1) * (nB - 1);

        for (int i = 0, n = 1; i < (nB - 1); i++) {
            for (int j = 0; j < (nH - 1); j++) {
                elements.add(new Element(n++, nH));
            }
            // we need to increase n because it's an ID of bottom left node,
            // and we are operating on elements (nH = nHE + 1)
            n++;
        }

        short BC;

        for (int i = 0; i < nB; i++) {
            for (int j = 0; j < nH; j++) {
                // checking if a node is on an edge (whether the boundary condition exists)
                // BC = 1 when is on an edge, BC = 0 when it's not on an edge
                if (i == 0 || j == 0 || i == nB - 1 || j == nH - 1) {
                    BC = 1;
                } else {
                    BC = 0;
                }
                nodes.add(new Node(deltaX * i, deltaY * j, BC, params.initialTemperature()));
            }
        }


        Algorithms.countH_HBC_P_C(this, params);

        aggregate();


        Utilities.displayElements(this);
        Utilities.displayNodes(this);

        System.out.println("HGlobal: ");
        System.out.println(Utilities.getArrayString(getHGlobal()));

        System.out.println("CGlobal: ");
        System.out.println(Utilities.getArrayString(getCGlobal()));

        System.out.println("PGlobal: ");
        System.out.println(Arrays.toString(getPGlobal()));

        iterate(params.simulationTime(), params.dT());


    }


    /**
     * Getters
     */

    /**
     * Getter that returns element by index,
     * indexes are from 1 to nE (NOT 0 TO nE-1!)
     *
     * @param n index of element (must be number 1 to nE)
     * @return Element object with requested index
     */
    public Element getElement(int n) {
        return elements.get(n - 1);
    }

    /**
     * Getter that returns node by index,
     * indexes are from 1 to nE <b>(NOT 0 TO nE-1!)</b>
     *
     * @param n index of node (must be number 1 to nE)
     * @return Node object with requested index
     */
    public Node getNode(int n) {
        return nodes.get(n - 1);
    }

    /**
     * Getter that gets all nodes
     *
     * @return nodes
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Getter that gets all elements
     *
     * @return elements
     */
    public ArrayList<Element> getElements() {
        return elements;
    }

    /**
     * Getter that returns number of nodes
     *
     * @return {@link Grid#nN} (number of nodes)
     */
    public int getnN() {
        return nN;
    }

    /**
     * Getter that returns number of elements
     *
     * @return {@link Grid#nE} (number of elements)
     */
    public int getnE() {
        return nE;
    }

    public double[][] getHGlobal() {
        return HGlobal;
    }

    public double[][] getCGlobal() {
        return CGlobal;
    }

    public double[] getPGlobal() {
        return PGlobal;
    }

    public Element4_2D getElement4_2D() {
        return element4_2D;
    }

    private double[] getTemperatures() {
        double[] result = new double[nN];
        for (int i = 0; i < nN; i++) {
            result[i] = nodes.get(i).getTemperature();
        }
        return result;
    }
    /**
     * Setters
     */

    /**
     * Setter of {@link Grid#HGlobal}
     *
     * @param HGlobal - 2D array
     */
    public void setHGlobal(double[][] HGlobal) {
        this.HGlobal = HGlobal;
    }

    public void setCGlobal(double[][] CGlobal) {
        this.CGlobal = CGlobal;
    }

    public void setPGlobal(double[] PGlobal) {
        this.PGlobal = PGlobal;
    }

    private void setTemperatures(RealVector results) {
        for (int i = 0; i < nN; i++) {
            nodes.get(i).setTemperature(results.getEntry(i));
        }
    }

    public void aggregate() {
        HGlobal = new double[nN][nN];
        CGlobal = new double[nN][nN];
        PGlobal = new double[nN];

        for (int i = 0; i < nE; i++) {
            Element element = getElement(i + 1);
            int[] ids = element.getID();
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    HGlobal[ids[j] - 1][ids[k] - 1] += (element.getH()[j][k] + element.getHBC()[j][k]);
                    CGlobal[ids[j] - 1][ids[k] - 1] += (element.getC()[j][k]);
                }
                PGlobal[ids[j] - 1] += (element.getP())[j];
            }
        }

    }


    /**
     * private methods
     */
    private void iterate(double simulationTime, double dT) {
        while (true) {
            if (simulationTime == 0) {
                return;
            } else if (simulationTime > dT) {
                simulationTime -= dT;
                iterate(dT);
            } else {
                iterate(simulationTime);
                return;
            }
        }
    }

    private void iterate(double dT) {
        RealMatrix HGlobalRM = new Array2DRowRealMatrix(HGlobal, false);
        RealMatrix CGlobalRM = new Array2DRowRealMatrix(CGlobal, false);
        RealVector PGlobalRV = new ArrayRealVector(PGlobal);

        RealVector t0 = new ArrayRealVector(getTemperatures());

        /**
         * [H] + [C] / dT
         */
        RealMatrix leftSide = HGlobalRM.add(CGlobalRM.scalarMultiply(1 / dT));
        /**
         * {P} + ([C] / dT) * {t0}
         */
        RealVector rightSide = PGlobalRV.add(CGlobalRM.scalarMultiply(1 / dT).operate(t0));
//        System.out.println("left side");
//        System.out.println(leftSide);
//        System.out.println("right side");
//        System.out.println(rightSide);

        DecompositionSolver decompositionSolver = new LUDecomposition(leftSide).getSolver();

        RealVector t1 = decompositionSolver.solve(rightSide);

        setTemperatures(t1);

        System.out.println("Temperatures: ");
        System.out.println(t1);
        System.out.println("Min: " + t1.getMinValue() + " Max: " + t1.getMaxValue());


    }


}
