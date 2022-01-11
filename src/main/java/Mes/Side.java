package Mes;

import java.util.Arrays;
import java.util.List;


public class Side {
    private double ksi;
    private double eta;
    private double N[] = new double[4];


    public Side(double ksi, double eta) {
        this.ksi = ksi;
        this.eta = eta;
        List<Double> shapeFunctions = Algorithms.shapeFunction(ksi, eta);
        N[0] = shapeFunctions.get(0);
        N[1] = shapeFunctions.get(1);
        N[2] = shapeFunctions.get(2);
        N[3] = shapeFunctions.get(3);
    }


    // getters

    public double getKsi() {
        return ksi;
    }

    public double getEta() {
        return eta;
    }

    public double[] getN() {
        return N;
    }

    @Override
    public String toString() {
        return "Side{" +
                "ksi=" + ksi +
                ", eta=" + eta +
                ", N=" + Arrays.toString(N) +
                '}';
    }
}
