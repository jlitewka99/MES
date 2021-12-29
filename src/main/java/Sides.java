import java.util.Arrays;

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
        matrixLeft = addTwoMatrixes(a, b);


        a = multiplyTwoMatrixes(sidesBottom[0]);
        b = multiplyTwoMatrixes(sidesBottom[1]);

        System.out.println("DEBBUGING");
        System.out.println((sidesBottom[0]));
        System.out.println((sidesBottom[1]));
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));

        matrixBottom = addTwoMatrixes(a, b);

        a = multiplyTwoMatrixes(sidesTop[0]);
        b = multiplyTwoMatrixes(sidesTop[1]);
        matrixTop = addTwoMatrixes(a, b);

        a = multiplyTwoMatrixes(sidesLeft[0]);
        b = multiplyTwoMatrixes(sidesLeft[1]);
        matrixRight = addTwoMatrixes(a, b);
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

    public static double[][] addTwoMatrixes(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public double[][] getMatrixBottom(double det_J, double wpc) {
        return multiplyMatrix(matrixBottom, det_J*wpc);
    }

    public double[][] getMatrixRight(double det_J, double wpc) {
        return multiplyMatrix(matrixRight, det_J*wpc);
    }

    public double[][] getMatrixTop(double det_J, double wpc) {
        return multiplyMatrix(matrixTop, det_J*wpc);
    }

    public double[][] getMatrixLeft(double det_J, double wpc) {
        return multiplyMatrix(matrixLeft, det_J*wpc);
    }

    // przekazywane jest det[J]
    private double[][] multiplyMatrix(double[][] matrix, double multiplier){
        double[][] result = new double[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] * multiplier;
            }
        }
        return result;
    }

    public void main(String[] args) {
//        Sides si = new Sides();
//        //hbc
//        System.out.println(Arrays.deepToString(Sides.getMatrixLeft(0.0125, 25.0)));

    }
}
