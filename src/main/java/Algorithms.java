import java.util.ArrayList;
import java.util.List;

public class Algorithms {


    public static List jakobian(Grid grid, Element4_2D element4_2D, int index, int pcNo) {

        List<Node> list = new ArrayList<Node>();

        Node[][] nodes = new Node[2][2];

        int id1 = grid.getElement(index).getID(1);
        int id2 = grid.getElement(index).getID(2);
        int id3 = grid.getElement(index).getID(3);
        int id4 = grid.getElement(index).getID(4);


        double x1 = grid.getNode(id1).getX();
        double x2 = grid.getNode(id2).getX();
        double x3 = grid.getNode(id3).getX();
        double x4 = grid.getNode(id4).getX();

        double y1 = grid.getNode(id1).getY();
        double y2 = grid.getNode(id2).getY();
        double y3 = grid.getNode(id3).getY();
        double y4 = grid.getNode(id4).getY();

        double resoultY[] = new double[4];
        double resoultX[] = new double[4];

        double d1 = element4_2D.getDn_dEta()[pcNo][0];
        double d2 = element4_2D.getDn_dEta()[pcNo][1];
        double d3 = element4_2D.getDn_dEta()[pcNo][2];
        double d4 = element4_2D.getDn_dEta()[pcNo][3];

        resoultY[0] = y1 * d1 + y2 * d2 + y3 * d3 + y4 * d4;

        d1 = element4_2D.getDn_dKsi()[pcNo][0];
        d2 = element4_2D.getDn_dKsi()[pcNo][1];
        d3 = element4_2D.getDn_dKsi()[pcNo][2];
        d4 = element4_2D.getDn_dKsi()[pcNo][3];

        resoultY[1] = -(y1 * d1 + y2 * d2 + y3 * d3 + y4 * d4);

        d1 = element4_2D.getDn_dEta()[pcNo][0];
        d2 = element4_2D.getDn_dEta()[pcNo][1];
        d3 = element4_2D.getDn_dEta()[pcNo][2];
        d4 = element4_2D.getDn_dEta()[pcNo][3];

        resoultY[2] = x1 * d1 + x2 * d2 + x3 * d3 + x4 * d4;

        d1 = element4_2D.getDn_dKsi()[pcNo][0];
        d2 = element4_2D.getDn_dKsi()[pcNo][1];
        d3 = element4_2D.getDn_dKsi()[pcNo][2];
        d4 = element4_2D.getDn_dKsi()[pcNo][3];

        resoultY[3] = x1 * d1 + x2 * d2 + x3 * d3 + x4 * d4;

        System.out.print(resoultY[0]);
        System.out.println("    " + resoultY[1]);
        System.out.print(resoultY[2]);
        System.out.println("    " + resoultY[3]);

        return list;
    }


}
