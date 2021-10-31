public class Main {
    public static void main(String[] args) {
        double H = 0.1;
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

        Element4_2D element4_2D = new Element4_2D();
        System.out.println(element4_2D);


        Algorithms.jakobian(grid, element4_2D, 1);

        int nE = grid.getnE();
        int npc = 4;



        for (int i = 0; i < nE; i++) {
            for (int j = 0; j < npc; j++) {

            }

        }


    }
}
