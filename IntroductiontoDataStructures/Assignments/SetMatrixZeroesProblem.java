import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroesProblem {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Task 1: Initialize zeroRows and zeroColumns.
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();

        // Task 2: Iterate through the matrix and record zero positions.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        // Task 3: Iterate through the matrix again and set elements to zero.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (zeroRows.contains(i) || zeroCols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        SetMatrixZeroesProblem solution = new SetMatrixZeroesProblem();
        int[][] matrix = {
                {1, 0, 3},
                {4, 5, 6},
                {7, 0, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        solution.setZeroes(matrix);

        // Task 4: Return the Modified Array.
        System.out.println("Modified Matrix:");
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
