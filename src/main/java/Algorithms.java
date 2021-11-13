import Resoults.JakobianReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Algorithms {


    public static JakobianReturn jakobian(Grid grid, Element4_2D element4_2D, int index, int pcNo) {


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


        double dnEta1 = element4_2D.getDn_dEta()[pcNo][0];
        double dnEta2 = element4_2D.getDn_dEta()[pcNo][1];
        double dnEta3 = element4_2D.getDn_dEta()[pcNo][2];
        double dnEta4 = element4_2D.getDn_dEta()[pcNo][3];

        double dnKsi1 = element4_2D.getDn_dKsi()[pcNo][0];
        double dnKsi2 = element4_2D.getDn_dKsi()[pcNo][1];
        double dnKsi3 = element4_2D.getDn_dKsi()[pcNo][2];
        double dnKsi4 = element4_2D.getDn_dKsi()[pcNo][3];


        double dy_dEta = dnEta1 * y1 + dnEta2 * y2 + dnEta3 * y3 + dnEta4 * y4;
        double dx_dEta = dnEta1 * x1 + dnEta2 * x2 + dnEta3 * x3 + dnEta4 * x4;

        double dy_dKsi = dnKsi1 * y1 + dnKsi2 * y2 + dnKsi3 * y3 + dnKsi4 * y4;
        double dx_dKsi = dnKsi1 * x1 + dnKsi2 * x2 + dnKsi3 * x3 + dnKsi4 * x4;


        // | dx_dKsi dy_dKsi |
        // |                 |
        // | dx_dEta dy_dEta |

        double det = dx_dKsi * dy_dEta - dy_dKsi * dx_dEta;

        double d_1_det = 1 / det;

        double matrix[][] = new double[2][2];
        double matrix_inv[][] = new double[2][2];

        matrix[0][0] = d_1_det * dx_dKsi;
        matrix[0][1] = d_1_det * dy_dKsi;
        matrix[1][0] = d_1_det * dx_dEta;
        matrix[1][1] = d_1_det * dy_dEta;

        matrix_inv[0][0] = matrix[1][1];
        matrix_inv[0][1] = -matrix[0][1];
        matrix_inv[1][0] = -matrix[1][0];
        matrix_inv[1][1] = matrix[0][0];


        return new JakobianReturn(det, d_1_det, matrix, matrix_inv);
    }


    public static void HMatrixIntegral(JakobianReturn pc, Element4_2D element4_2D, int pcNo, double wwc) {
        double[][] J = pc.getJ();

        double[] dN_dx = new double[4];
        double[] dN_dy = new double[4];

        dN_dx[0] = element4_2D.getDn_dKsi()[pcNo][0] * J[0][0] + element4_2D.getDn_dEta()[pcNo][0] * J[0][1];
        dN_dx[1] = element4_2D.getDn_dKsi()[pcNo][1] * J[0][0] + element4_2D.getDn_dEta()[pcNo][1] * J[0][1];
        dN_dx[2] = element4_2D.getDn_dKsi()[pcNo][2] * J[0][0] + element4_2D.getDn_dEta()[pcNo][2] * J[0][1];
        dN_dx[3] = element4_2D.getDn_dKsi()[pcNo][3] * J[0][0] + element4_2D.getDn_dEta()[pcNo][3] * J[0][1];

        dN_dy[0] = element4_2D.getDn_dKsi()[pcNo][0] * J[1][0] + element4_2D.getDn_dEta()[pcNo][0] * J[1][1];
        dN_dy[1] = element4_2D.getDn_dKsi()[pcNo][1] * J[1][0] + element4_2D.getDn_dEta()[pcNo][1] * J[1][1];
        dN_dy[2] = element4_2D.getDn_dKsi()[pcNo][2] * J[1][0] + element4_2D.getDn_dEta()[pcNo][2] * J[1][1];
        dN_dy[3] = element4_2D.getDn_dKsi()[pcNo][3] * J[1][0] + element4_2D.getDn_dEta()[pcNo][3] * J[1][1];

        double[][] matrixX = new double[4][4];
        double[][] matrixY = new double[4][4];
        double[][] matrix = new double[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrixX[i][j] = dN_dx[i] * dN_dx[j];
                matrixY[i][j] = dN_dy[i] * dN_dy[j];
            }

        }


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = (matrixX[i][j] + matrixY[i][j]) * wwc * pc.getDet();
            }
        }

        System.out.println(Arrays.toString(matrix[0]));
        System.out.println(Arrays.toString(matrix[1]));
        System.out.println(Arrays.toString(matrix[2]));
        System.out.println(Arrays.toString(matrix[3]));



    }


}
