package dev.abgeo.cst;

/**
 * Class ParallelMultiplier.
 *
 * Multiply two matrices in parallel.
 *
 * @author Temuri Takalandze <me@abgeo.dev>
 */
public class ParallelMultiplier extends Thread {
    private int row;
    private int column;
    private int[] matrixA;
    private int[][] matrixB;
    private int[][] product;

    /**
     * ParallelMultiplier constructor.
     *
     * @param row     Row number for current thread.
     * @param column  Column number for current thread.
     * @param matrixA Matrix A for multiplication.
     * @param matrixB Matrix B for multiplication.
     * @param product The product of matrices A and B.
     */
    public ParallelMultiplier(int row, int column, int[] matrixA, int[][] matrixB, int[][] product) {
        this.row = row;
        this.column = column;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.product = product;
    }

    /**
     * Multiply two matrices in parallel.
     */
    public void run() {
        for (int i = 0; i < matrixA.length; i++) {
            product[row][column] += (matrixA[i] * matrixB[i][column]);
        }
    }

}
