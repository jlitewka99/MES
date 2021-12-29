package Resoults;

import java.util.Arrays;

/**
 * This class is for returning Jakobian result
 */
public class JakobianReturn {
    private double det;
    private double d_1_det;
    private double[][] J;
    private double[][] J_inv;

    public JakobianReturn(double det, double d_1_det, double[][] j, double[][] j_inv) {
        this.det = det;
        this.d_1_det = d_1_det;
        J = j;
        J_inv = j_inv;
    }

    public double getDet() {
        return det;
    }

    public double getD_1_det() {
        return d_1_det;
    }

    public double[][] getJ() {
        return J;
    }

    public double[][] getJ_inv() {
        return J_inv;
    }

    @Override
    public String toString() {
        return "JakobianReturn{" +
                "det=" + det +
                ", d_1_det=" + d_1_det +
                ",\nJ=\n[" + Arrays.toString(J[0]) +
                ",\n" + Arrays.toString(J[1]) +
                "]}" +
                ",\nJ_inv=\n[" + Arrays.toString(J_inv[0]) +
                ",\n" + Arrays.toString(J_inv[1]) +
                "]}";
    }
}
