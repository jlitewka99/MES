package Mes;

import DataHolders.Params;

public class Scenario {
    public static Params getTest1(){
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
    public static Params getTest2(){
        double initialTemperature = 100;
        double simulationTime = 20;
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
    public static Params getTest3(){
        double initialTemperature = 100;
        double simulationTime = 100;
        double dT = 1;
        double ambientTemperature = 1200;
        double alfa = 300;
        double H = 0.10;
        double B = 0.10;
        int nH = 31;
        int nB = 61;
        double C = 700;
        double conductivity = 25;
        double RO = 7800;

        return new Params(initialTemperature, simulationTime, dT, ambientTemperature, alfa, H, B, nH, nB, C, conductivity, RO);
    }
}
