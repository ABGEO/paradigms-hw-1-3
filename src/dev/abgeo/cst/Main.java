package dev.abgeo.cst;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] matrixA = Matrix.read("A");
        int[][] matrixB = Matrix.read("B");

        System.out.println("Multiplying A and B matrices...");
        int[][] matrixResult = Matrix.multiply(matrixA, matrixB);

        Matrix.print(matrixA, "A");
        Matrix.print(matrixB, "B");
        Matrix.print(matrixResult, "Result");
    }

}
