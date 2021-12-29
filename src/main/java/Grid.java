import java.util.ArrayList;

public class Grid {
    /**
     * Fields that are passed by the user
     */

    /**
     * H - height of grid
     */
    private double H;

    /**
     *  B - breadth of grid (width)
     */
    private double B;

    /**
     *  nH - number of horizontal nodes
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
     * nN - number of nodes
      */
    private int nN;
    /**
     * nE - number of elements
      */
    private int nE;

    /**
     * distance between x-axis nodes
     */
    private double deltaX;
    /**
     * distance between y-axis nodes
     */
    private double deltaY;


    private double[][] HGlobal;

    // list of nodes and list of elements
    ArrayList<Node> nodes = new ArrayList<Node>();
    ArrayList<Element> elements = new ArrayList<Element>();

    /**
     * Grid will be H x B and nH x nB nodes
     *
     * @param H  height of grid (centimeters or other unit of distance)
     * @param B  breadth of grid (centimeters or other unit of distance)
     * @param nH number of <b>vertical nodes</b>
     *           (that means that number of <b>vertical elements</b> will be <i>nH-1</i>)
     * @param nB number of <b>horizontal nodes</b>
     *           (that means that number of <b>horizontal elements</b> will be <i>nB-1</i>)
     */
    public Grid(double H, double B, int nH, int nB) {
        this.H = H;
        this.B = B;
        this.nH = nH;
        this.nB = nB;

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
            n++;
        }
        for (int i = 0; i < nB; i++) {
            for (int j = 0; j < nH; j++) {
                //sprawdzanie czy znajduje sie na krawedzi (wystepuje warunek brzegowy)
                if (i == 0 || j == 0 || i == nB - 1 || j == nH - 1) {
                    // tworzenie siatki
                    nodes.add(new Node(deltaX * i, deltaY * j, (short) 1));
                }
                else {
                    nodes.add(new Node(deltaX * i, deltaY * j, (short) 0));
                }
            }
        }
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

    public int getnN() {
        return nN;
    }

    public int getnE() {
        return nE;
    }

    /**
     * Setters
     */

    /**
     *
     * @param HGlobal
     */
    public void setHGlobal(double[][] HGlobal) {
        this.HGlobal = HGlobal;
    }
}
