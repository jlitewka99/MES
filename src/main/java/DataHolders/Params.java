package DataHolders;

/**
 * initialTemperature – initial temperature
 * simulationTime – simulation time [s],
 * dT – simulation step time [s],
 * ambientTemperature – ambient temperature [C],
 * alfa – alfa [W/m2K],
 * H – H [m],
 * B – B [m],
 * nH – N_H,
 * nB – N_B,
 * C – specific heat [J/(kgC)],
 * conductivity – conductivity [W/(mC)],
 * RO – density [kg/m3].
 */
public record Params(
        double initialTemperature,
        double simulationTime,
        double dT,
        double ambientTemperature,
        double alfa,
        double H,
        double B,
        int nH,
        int nB,
        double C,
        double conductivity,
        double RO
) {

}
