package dev.abgeo.cst;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class Matrix.
 *
 * Matrix helper.
 *
 * @author Temuri Takalandze <me@abgeo.dev>
 */
public class Matrix {

    /**
     * Multiply the given matrices.
     *
     * @param matrixA Matrix A for multiplication.
     * @param matrixB Matrix B for multiplication.
     *
     * @return The product of matrices A and B
     *
     * @throws Exception Invalid dimensions exception.
     */
    public static int[][] multiply(int[][] matrixA, int[][] matrixB) throws Exception {
        final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
        final ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
        final int rowA = matrixA.length;
        final int colB = matrixB[0].length;

        if (rowA != colB) {
            throw new Exception("Invalid matrix dimensions! Cannot multiply.");
        }

        int[][] matrixResult = new int[rowA][colB];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                ParallelMultiplier multiplyThread = new ParallelMultiplier(i, j, matrixA[i], matrixB, matrixResult);
                executor.execute(multiplyThread);
            }
        }
        executor.shutdown();

        return matrixResult;
    }

    /**
     * Read matrix dimensions and elements from STDIN.
     *
     * @param matrixTitle Matrix title.
     *
     * @return Readed matrix.
     */
    public static int[][] read(String matrixTitle) {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.print(String.format("Enter dimension for Matrix %s [M x N]: ", matrixTitle));
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];

        System.out.println(String.format("Enter Matrix %s elements ", matrixTitle));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = sc.nextInt();
            }
        }

        return matrix;
    }

    /**
     * Print given matrix dimensions and elements.
     *
     * @param matrix Matrix for printing.
     * @param matrixTitle Matrix title.
     */
    public static void print(int[][] matrix, String matrixTitle) {
        System.out.print(String.format("Matrix %s: ", matrixTitle));
        System.out.println(String.format("Dimensions [%d x %d] ", matrix.length, matrix[0].length));
        for (int[] x : matrix) {
            for (int y : x) {
                System.out.print(String.format("%d ", y));
            }
            System.out.println();
        }
    }

}
