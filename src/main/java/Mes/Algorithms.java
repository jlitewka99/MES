package Mes;

import Resoults.JakobianReturn;

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


    public static double[][] HMatrixIntegralOfSpecificPc(JakobianReturn pc, Element4_2D element4_2D, int pcNo, double wwc) {
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

        return matrix;
    }

    public static double[][] HMatrixIntegral(JakobianReturn[] pc, Element4_2D element4_2D, double wwc) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            double[][] matrix = HMatrixIntegralOfSpecificPc(pc[i], element4_2D, i, wwc);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[j][k] += matrix[j][k];
                }
            }
        }
        return result;
    }


    /**
     * Counts 1D shape functions
     *
     * @param ksi_eta ksi or eta
     * @return list with result of
     * 1/2*(1-x) <- index(0),
     * 1/2*(1+x) <- index(1)
     */
    public static List<Double> shapeFunction(double ksi_eta) {
        return List.of(
                (0.5 * (1 - ksi_eta)),
                (0.5 * (1 + ksi_eta))
        );
    }

    /**
     * Counts 2D shape functions
     *
     * @param ksi ksi
     * @param eta eta
     * @return list with results of
     * 1/4*(1 - ksi)*(1 - eta) <- index(0),
     * 1/4*(1 + ksi)*(1 - eta) <- index(1),
     * 1/4*(1 + ksi)*(1 + eta) <- index(2),
     * 1/4*(1 - ksi)*(1 + eta) <- index(3)
     */
    public static List<Double> shapeFunction(double ksi, double eta) {
        return List.of(
                (0.25 * (1.0 - ksi) * (1.0 - eta)),
                (0.25 * (1.0 + ksi) * (1.0 - eta)),
                (0.25 * (1.0 + ksi) * (1.0 + eta)),
                (0.25 * (1.0 - ksi) * (1.0 + eta)));
    }

    /**
     * @param grid
     * @param element4_2D
     * @param alfa        - [W/m^2*K]
     */
    public static void countHAndHBC(Grid grid, Element4_2D element4_2D, double alfa, double conductivity) {
        double[][] hMatrix;
        double[][] HBCMatrix;

        int[] indexes;
        JakobianReturn[] r = new JakobianReturn[4];

        Sides sides = new Sides();

        for (int i = 1; i <= grid.getElements().size(); i++) {

            HBCMatrix = new double[4][4];

            /**
             * Counting H matrix
             */
            r[0] = Algorithms.jakobian(grid, element4_2D, i, 0);
            r[1] = Algorithms.jakobian(grid, element4_2D, i, 1);
            r[2] = Algorithms.jakobian(grid, element4_2D, i, 2);
            r[3] = Algorithms.jakobian(grid, element4_2D, i, 3);

            hMatrix = Algorithms.HMatrixIntegral(r, element4_2D, alfa);

            grid.getElement(i).setH(hMatrix);

//            System.out.println("HMatrix");
//            System.out.println(Arrays.deepToString(hMatrix));
            /**
             * Counting HBC matrix
             */
            indexes = grid.getElement(i).getID();
            double det_J;
            if (grid.getNode(indexes[0]).getBC() == 1 && grid.getNode(indexes[1]).getBC() == 1) {
                det_J = countDet_J(grid.getNode(indexes[0]), grid.getNode(indexes[1]));
                HBCMatrix = Algorithms.addMatrixes(sides.getMatrixBottom(det_J, conductivity), HBCMatrix);
            }
            if (grid.getNode(indexes[1]).getBC() == 1 && grid.getNode(indexes[2]).getBC() == 1) {
                det_J = countDet_J(grid.getNode(indexes[1]), grid.getNode(indexes[2]));
                HBCMatrix = Algorithms.addMatrixes(sides.getMatrixRight(det_J, conductivity), HBCMatrix);
            }
            if (grid.getNode(indexes[2]).getBC() == 1 && grid.getNode(indexes[3]).getBC() == 1) {
                det_J = countDet_J(grid.getNode(indexes[2]), grid.getNode(indexes[3]));
                HBCMatrix = Algorithms.addMatrixes(sides.getMatrixTop(det_J, conductivity), HBCMatrix);
            }
            if (grid.getNode(indexes[3]).getBC() == 1 && grid.getNode(indexes[0]).getBC() == 1) {
                det_J = countDet_J(grid.getNode(indexes[3]), grid.getNode(indexes[0]));
                HBCMatrix = Algorithms.addMatrixes(sides.getMatrixLeft(det_J, conductivity), HBCMatrix);
            }
            grid.getElement(i).setHBC(HBCMatrix);
        }
//        System.out.println("HBC");
//        System.out.println(Arrays.deepToString(grid.getElement(1).getHBC()));

//        r[0] = Algorithms.jakobian(grid, element4_2D, 1, 0);
//        r[1] = Algorithms.jakobian(grid, element4_2D, 1, 1);
//        r[2] = Algorithms.jakobian(grid, element4_2D, 1, 2);
//        r[3] = Algorithms.jakobian(grid, element4_2D, 1, 3);

//        System.out.println(r[0]);
//        System.out.println(r[1]);
//        System.out.println(r[2]);
//        System.out.println(r[3]);
    }

    /**
     * @param matrix     - 2D matrix
     * @param multiplier - double multiplier
     * @return 2D matrix with all elements multiplied by multiplier
     */
    public static double[][] multiplyMatrix(double[][] matrix, double multiplier) {
        double[][] result = new double[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] * multiplier;
            }
        }
        return result;
    }

    /**
     * Adds two 2D matrix, with the same dimensions
     *
     * @param matrix1 - 2D matrix
     * @param matrix2 - 2D matrix
     * @return sum of matrix1 and matrix2
     */
    public static double[][] addMatrixes(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    private static double countDet_J(Node n1, Node n2) {
        return Math.sqrt(Math.pow(n2.getX() - n1.getX(), 2) +
                Math.pow((n1.getY() - n2.getY()), 2)) / 2.0;
    }

}