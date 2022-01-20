package Mes;

import Algorithms.Utilities;
import Resoults.Params;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.Arrays;



public class Main {

    private static Params getTest1(){
        double initialTemperature = 100;
        double simulationTime = 500;
        double dT = 50;
        double ambientTemperature = 1200;
        double alfa = 300;
        double H = 0.10;
        double B = 0.10;
        int nH = 4;
        int nB = 4;
        double C = 700;
        double conductivity = 25;
        double RO = 7800;

        return new Params(initialTemperature, simulationTime, dT, ambientTemperature, alfa, H, B, nH, nB, C, conductivity, RO);
    }
    private static Params getTest2(){
        double initialTemperature = 100;
        double simulationTime = 100;
        double dT = 1;
        double ambientTemperature = 1200;
        double alfa = 300;
        double H = 0.10;
        double B = 0.10;
        int nH = 31;
        int nB = 31;
        double C = 700;
        double conductivity = 25;
        double RO = 7800;

        return new Params(initialTemperature, simulationTime, dT, ambientTemperature, alfa, H, B, nH, nB, C, conductivity, RO);
    }
    private static Params getTest3(){
        double initialTemperature = 100;
        double simulationTime = 100;
        double dT = 1;
        double ambientTemperature = 1200;
        double alfa = 300;
        double H = 0.10;
        double B = 0.10;
        int nH = 100;
        int nB = 100;
        double C = 700;
        double conductivity = 25;
        double RO = 7800;

        return new Params(initialTemperature, simulationTime, dT, ambientTemperature, alfa, H, B, nH, nB, C, conductivity, RO);
    }
    public static void main(String[] args) {
        /*
        Start params.
        Size of grid, number of elements etc.
         */
        Params params = getTest3();
        // creating grid by specific params

        Grid grid = new Grid(params);

        // write down







    }


}
