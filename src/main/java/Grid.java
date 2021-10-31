import java.util.ArrayList;

public class Grid {
    // height of grid
    private double H;
    // breadth of grid
    private double B;
    private int nH;
    private int nB;
    private int nN;
    private int nE;

    private double deltaX;
    private double deltaY;


    ArrayList<Node> nodes = new ArrayList<Node>();
    ArrayList<Element> elements = new ArrayList<Element>();

    public int getnE() {
        return nE;
    }


    /**
     * Grid will be H x B and nH x nB nodes
     * @param H height of grid
     * @param B breadth of grid
     * @param nH number of vertical nodes
     * @param nB number of horizontal nodes
     */
    public Grid(double H, double B, int nH, int nB) {
        this.H = H;
        this.B = B;
        this.nH = nH;
        this.nB = nB;

        // math equations
        this.deltaX = B / (nB - 1);
        this.deltaY = H / (nH - 1);


        nN = nH*nB;
        nE = (nH-1)*(nB-1);

        for (int i = 0, n = 1; i < (nB-1); i++) {
            for (int j = 0; j < (nH-1); j++) {
                elements.add(new Element(n++, nH));
            }
            n++;
        }
        for(int i = 0; i < nB; i++){
            for (int j = 0; j < nH; j++) {
                nodes.add(new Node(deltaX*i, deltaY*j));
            }
        }
    }

    /**
     * Returns element, indexes works from 1 to nE (NOT 0 TO nE-1!)
     * @param n index of element (must be number 1 to nE)
     * @return Element object with requested index
     */
    public Element getElement(int n){return elements.get(n-1);}
    /**
     * Returns node, indexes works from 1 to nE (NOT 0 TO nE-1!)
     * @param n index of node (must be number 1 to nE)
     * @return Node object with requested index
     */
    public Node getNode(int n){return nodes.get(n-1);}

    /**
     * Getter gets all nodes
     * @return nodes
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Getter gets all elements
     * @return elements
     */
    public ArrayList<Element> getElements() {
        return elements;
    }
}
