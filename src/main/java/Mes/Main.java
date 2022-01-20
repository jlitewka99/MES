package Mes;

import BaseElements.Grid;
import DataHolders.Params;


public class Main {
    public static void main(String[] args) {
        /*
        Start params.
        Size of grid, number of elements etc.
         */
        Params params = Scenario.getTest2();
        // creating grid by specific params

        Grid grid = new Grid(params);


    }


}
