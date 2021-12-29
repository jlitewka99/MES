import Resoults.JakobianReturn;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        Start params.
        Size of grid, number of elements etc.
         */
        double H = 0.1;
        double B = 0.075;
        int nH = 5;
        int nB = 4;

        // creating grid by specific params
        Grid grid = new Grid(H, B, nH, nB);

        // write down
        for (int i = 0; i < grid.getElements().size(); i++) {
            System.out.println("Element " + (i + 1) + " = " + grid.getElement(i + 1));
        }
        for (int i = 0; i < grid.getNodes().size(); i++) {
            System.out.println("Node " + (i + 1) + " = " + grid.getNode(i + 1));
        }

        Element4_2D element4_2D = new Element4_2D();
        System.out.println(element4_2D);

        Algorithms.countHAndHBC(grid, element4_2D);







        //agregacja

        double[][] HGlobal = new double[grid.getnN()][grid.getnN()];

        for (int i = 0; i < grid.getnE(); i++) {
            Element element = grid.getElement(i+1);
            int[] ids = element.getID();
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    HGlobal[ids[j]-1][ids[k]-1] += (element.getH()[j][k] + element.getHBC()[j][k]);
                }
            }
        }
        System.out.println("HGlobal: ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(HGlobal[i][j] + "    ");
            }
            System.out.println();
        }












    }
}
