import java.util.ArrayList;
import java.util.List;

public class Algorithms {


    public static List jakobian(Grid grid, Element4_2D element4_2D, int index) {

        List<Node> list = new ArrayList<Node>();

        Node[][] nodes= new Node[2][2];

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
        double resountY1;

//        System.out.println(resountY1);
        for (int i = 0; i < 4; i++) {
            double dY1 = element4_2D.getDn_dEta()[i][0];
            double dY2 = element4_2D.getDn_dEta()[i][1];
            double dY3 = element4_2D.getDn_dEta()[i][2];
            double dY4 = element4_2D.getDn_dEta()[i][3];

            resoultY[i] = y1*dY1 + y2*dY2 + y3*dY3 + y4*dY4;
        }

        System.out.println(resoultY[0]);
        System.out.println(resoultY[1]);
        System.out.println(resoultY[2]);
        System.out.println(resoultY[3]);

        return list;
    }


}
