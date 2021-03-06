package Algorithms.HelpingElements;

import Algorithms.Algorithms;

import java.text.DecimalFormat;
import java.util.List;

/**
 *
 */
public class Element4_2D {

    private final double A = 1.0 / Math.sqrt(3);

    private final double[] ksi = new double[]{-1.0 * A, A, A, -1.0 * A};
    private final double[] eta = new double[]{-1.0 * A, -1.0 * A, A, A};


    private double[][] dn_dKsi = new double[4][4];

    private double[][] dn_dEta = new double[4][4];

    public Element4_2D() {

        for (int i = 0; i < 4; i++) {
            List<Double> etaSF = Algorithms.shapeFunction(eta[i]);

            dn_dKsi[i][0] = -etaSF.get(0) * 0.5;
            dn_dKsi[i][1] = etaSF.get(0) * 0.5;
            dn_dKsi[i][2] = etaSF.get(1) * 0.5;
            dn_dKsi[i][3] = -etaSF.get(1) * 0.5;

            List<Double> ksiSF = Algorithms.shapeFunction(ksi[i]);

            dn_dEta[i][0] = -ksiSF.get(0) * 0.5;
            dn_dEta[i][1] = -ksiSF.get(1) * 0.5;
            dn_dEta[i][2] = ksiSF.get(1) * 0.5;
            dn_dEta[i][3] = ksiSF.get(0) * 0.5;

            dn_dKsi[i][0] = function1(eta[i], true);
            dn_dKsi[i][1] = function1(eta[i], false);
            dn_dKsi[i][2] = function2(eta[i], false);
            dn_dKsi[i][3] = function2(eta[i], true);
            dn_dEta[i][0] = function1(ksi[i], true);
            dn_dEta[i][1] = function2(ksi[i], true);
            dn_dEta[i][2] = function2(ksi[i], false);
            dn_dEta[i][3] = function1(ksi[i], false);
        }
    }



    private double function1(double a, boolean isNegative) {
        double result = 0.25 * (1 - a);
        if (isNegative) {
            return -result;
        }
        return result;
    }
    private double function2(double a, boolean isNegative) {
        double result = 0.25 * (1 + a);
        if (isNegative) {
            return -result;
        }
        return result;
    }

    public double[][] getDn_dKsi() {
        return dn_dKsi;
    }

    public double[][] getDn_dEta() {
        return dn_dEta;
    }


    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0000");
        StringBuilder ksiString = new StringBuilder("Element4_2D{\nksi[\n");
        StringBuilder etaString = new StringBuilder("\neta[\n");

        for (int i = 0; i < 4; i++) {
            ksiString.append("[");
            etaString.append("[");
            for (int j = 0; j < 3; j++) {
                ksiString.append(df.format(dn_dKsi[i][j]) + ",  ");
                etaString.append(df.format(dn_dEta[i][j]) + ",  ");
            }
            ksiString.append(df.format(dn_dKsi[i][3]) + "]\n");
            etaString.append(df.format(dn_dEta[i][3]) + "]\n");
        }

        ksiString.append("]" + etaString + "]");
        return ksiString.toString().replace("]\n]", "]]").replace("  -", " -");
    }
}
