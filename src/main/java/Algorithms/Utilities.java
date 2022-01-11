package Algorithms;

import Mes.Grid;

import java.util.Arrays;

public class Utilities {
    public static String getArrayString(Object[][] array){
        return (Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
    public static String getArrayString(double[][] array){
        return (Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
    public static String getArrayString(int[][] array){
        return (Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
    public static void displayElements(Grid grid){
        for (int i = 0; i < grid.getElements().size(); i++) {
            System.out.println("Element " + (i + 1) + " = " + grid.getElement(i + 1));
        }
    }
    public static void displayNodes(Grid grid){
        for (int i = 0; i < grid.getNodes().size(); i++) {
            System.out.println("Node " + (i + 1) + " = " + grid.getNode(i + 1));
        }
    }
}
