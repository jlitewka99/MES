package Algorithms.HelpingElements;

import Algorithms.Utilities;

public class Sides {
    private final Side[] sidesBottom = new Side[2];
    private final Side[] sidesRight = new Side[2];
    private final Side[] sidesTop = new Side[2];
    private final Side[] sidesLeft = new Side[2];

    /**
     * used to get shape function values in integration point (in this case ksi and eta are +/- 1/sqrt(3))
     */
    private final Side[] sidesC = new Side[4];

    private double[][] matrixBottom;
    private double[][] matrixRight;
    private double[][] matrixTop;
    private double[][] matrixLeft;

    public Sides() {
        double one_sqrt3 = 1 / Math.sqrt(3);
        sidesLeft[0] = new Side(-1.0, one_sqrt3);
        sidesLeft[1] = new Side(-1.0, -one_sqrt3);

        sidesBottom[0] = new Side(-one_sqrt3, -1);
        sidesBottom[1] = new Side(one_sqrt3, -1);

        sidesRight[0] = new Side(1.0, -one_sqrt3);
        sidesRight[1] = new Side(1.0, one_sqrt3);

        sidesTop[0] = new Side(one_sqrt3, 1.0);
        sidesTop[1] = new Side(-one_sqrt3, 1.0);


        double[][] a = multiplyTwoMatrixes(sidesLeft[0]);
        double[][] b = multiplyTwoMatrixes(sidesLeft[1]);
        matrixLeft = Utilities.addMatrixes(a, b);


        a = multiplyTwoMatrixes(sidesBottom[0]);
        b = multiplyTwoMatrixes(sidesBottom[1]);

        matrixBottom = Utilities.addMatrixes(a, b);

        a = multiplyTwoMatrixes(sidesTop[0]);
        b = multiplyTwoMatrixes(sidesTop[1]);
        matrixTop = Utilities.addMatrixes(a, b);

        a = multiplyTwoMatrixes(sidesRight[0]);
        b = multiplyTwoMatrixes(sidesRight[1]);
        matrixRight = Utilities.addMatrixes(a, b);


        sidesC[0] = new Side(-one_sqrt3, -one_sqrt3);
        sidesC[1] = new Side(one_sqrt3, -one_sqrt3);
        sidesC[2] = new Side(one_sqrt3, one_sqrt3);
        sidesC[3] = new Side(-one_sqrt3, one_sqrt3);

        for (int i = 0; i < 4; i++) {
            System.out.println("SHAPE FUNC");
            System.out.println(sidesC[i]);
        }

    }


    // counts 4x4 matrix by passed 4x1
    private double[][] multiplyTwoMatrixes(Side side) {
        double[] N = side.getN();
        double[][] matrix = new double[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = N[i] * N[j];// * multiplier;
            }
        }
        return matrix;
    }

    private double[][] multiplyTwoMatrixes(double[] matrix1D) {

        int matrixLength = matrix1D.length;

        double[][] matrix = new double[matrixLength][matrixLength];

        for (int i = 0; i < matrixLength; i++) {
            for (int j = 0; j < matrixLength; j++) {
                matrix[i][j] = matrix1D[i] * matrix1D[j];// * multiplier;
            }
        }
        return matrix;
    }

    /**
     * Methods used to count HBC matrix
     */

    public double[][] getMatrixBottom(double det_J, double wpc) {
        return Utilities.multiplyMatrix(matrixBottom, det_J * wpc);
    }

    public double[][] getMatrixRight(double det_J, double wpc) {
        return Utilities.multiplyMatrix(matrixRight, det_J * wpc);
    }

    public double[][] getMatrixTop(double det_J, double wpc) {
        return Utilities.multiplyMatrix(matrixTop, det_J * wpc);
    }

    public double[][] getMatrixLeft(double det_J, double wpc) {
        return Utilities.multiplyMatrix(matrixLeft, det_J * wpc);
    }

    /**
     * Methods used to count P matrix
     */

    public double[] getMatrixBottom(double ambientTemperature, double alfa, double detJ) {
        double w1 = 1.0;
        double w2 = 1.0;
        return Utilities.multiplyMatrix(
                Utilities.addMatrixes(
                        Utilities.multiplyMatrix(sidesBottom[0].getN(), ambientTemperature * w1 * detJ),
                        Utilities.multiplyMatrix(sidesBottom[1].getN(), ambientTemperature * w2 * detJ)
                ), alfa);
    }

    public double[] getMatrixRight(double ambientTemperature, double alfa, double detJ) {
        double w1 = 1.0;
        double w2 = 1.0;

        return Utilities.multiplyMatrix(
                Utilities.addMatrixes(
                        Utilities.multiplyMatrix(sidesRight[0].getN(), ambientTemperature * w1 * detJ),
                        Utilities.multiplyMatrix(sidesRight[1].getN(), ambientTemperature * w2 * detJ)
                ), alfa);
    }

    public double[] getMatrixTop(double ambientTemperature, double alfa, double detJ) {
        double w1 = 1.0;
        double w2 = 1.0;

        return Utilities.multiplyMatrix(
                Utilities.addMatrixes(
                        Utilities.multiplyMatrix(sidesTop[0].getN(), ambientTemperature * w1 * detJ),
                        Utilities.multiplyMatrix(sidesTop[1].getN(), ambientTemperature * w2 * detJ)
                ), alfa);
    }

    public double[] getMatrixLeft(double ambientTemperature, double alfa, double detJ) {
        double w1 = 1.0;
        double w2 = 1.0;

        return Utilities.multiplyMatrix(
                Utilities.addMatrixes(
                        Utilities.multiplyMatrix(sidesLeft[0].getN(), ambientTemperature * w1 * detJ),
                        Utilities.multiplyMatrix(sidesLeft[1].getN(), ambientTemperature * w2 * detJ)
                ), alfa);
    }

    /**
     * Used to count C matrix of specific point
     * @param pointLocalId
     * @param C
     * @param RO
     * @param w
     * @param detJ
     * @return
     */
    public double[][] multiplyMatrixes1Dto2D(int pointLocalId, double C, double RO, double w, double detJ) {

        return Utilities.multiplyMatrix(multiplyTwoMatrixes(sidesC[pointLocalId]), C * RO * w * detJ);
//        return d;
    }

    public Side[] getSidesC() {
        return sidesC;
    }

}
