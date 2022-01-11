package Mes;

public class Sides {
    private final Side[] sidesBottom = new Side[2];
    private final Side[] sidesRight = new Side[2];
    private final Side[] sidesTop = new Side[2];
    private final Side[] sidesLeft = new Side[2];

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
        matrixLeft = Algorithms.addMatrixes(a, b);


        a = multiplyTwoMatrixes(sidesBottom[0]);
        b = multiplyTwoMatrixes(sidesBottom[1]);

        matrixBottom = Algorithms.addMatrixes(a, b);

        a = multiplyTwoMatrixes(sidesTop[0]);
        b = multiplyTwoMatrixes(sidesTop[1]);
        matrixTop = Algorithms.addMatrixes(a, b);

        a = multiplyTwoMatrixes(sidesRight[0]);
        b = multiplyTwoMatrixes(sidesRight[1]);
        matrixRight = Algorithms.addMatrixes(a, b);
    }


    // multiplies 4x4 matrix by added 4x1
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

    public double[][] getMatrixBottom(double det_J, double wpc) {
        return Algorithms.multiplyMatrix(matrixBottom, det_J*wpc);
    }

    public double[][] getMatrixRight(double det_J, double wpc) {
        return Algorithms.multiplyMatrix(matrixRight, det_J*wpc);
    }

    public double[][] getMatrixTop(double det_J, double wpc) {
        return Algorithms.multiplyMatrix(matrixTop, det_J*wpc);
    }

    public double[][] getMatrixLeft(double det_J, double wpc) {
        return Algorithms.multiplyMatrix(matrixLeft, det_J*wpc);
    }

    public void main(String[] args) {
//        Sides si = new Sides();
//        //hbc
//        System.out.println(Arrays.deepToString(Sides.getMatrixLeft(0.0125, 25.0)));

    }
}
