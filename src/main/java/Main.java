public class Main {
    public static void main(String[] args) {
        double H = 0.2;
        double B = 0.1;
        int nH = 5;
        int nB = 4;
        Grid grid = new Grid(H, B, nH, nB);

        for(int i = 0; i < grid.getElements().size(); i++){
            System.out.println("Element " + (i+1) + " = " + grid.getElement(i+1));
        }
        for(int i = 0; i < grid.getNodes().size(); i++){
            System.out.println("Node " + (i+1) + " = " + grid.getNode(i+1));
        }
    }
}
