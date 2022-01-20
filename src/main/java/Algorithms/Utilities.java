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
    /**
     * Adds two 2D matrix, with the same dimensions
     *
     * @param matrix1 - 2D matrix
     * @param matrix2 - 2D matrix
     * @return sum of matrix1 and matrix2
     */
    public static double[][] addMatrixes(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }
    public static double[] addMatrixes(double[] matrix1, double[] matrix2) {
        double[] result = new double[matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
                result[i] = matrix1[i] + matrix2[i];
        }
        return result;
    }
    /**
     * @param matrix     - 2D matrix
     * @param multiplier - double multiplier
     * @return 2D matrix with all elements multiplied by multiplier
     */
    public static double[][] multiplyMatrix(double[][] matrix, double multiplier) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] * multiplier;
            }
        }
        return result;
    }
    public static double[] multiplyMatrix(double[] matrix, double multiplier) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
                result[i] = matrix[i] * multiplier;
        }
        return result;
    }
}
