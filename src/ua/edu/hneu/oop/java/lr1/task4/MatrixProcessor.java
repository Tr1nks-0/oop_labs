package ua.edu.hneu.oop.java.lr1.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MatrixProcessor {
    private static final int MAX_MATRIX_ELEMENT = 1;
    private static final int MIN_MATRIX_ELEMENT = 0;
    private Random random;

    public MatrixProcessor() {
        this.random = new Random(System.currentTimeMillis());
    }

        public int[][] obtainMatrix(int sizeX, int sizeY) {
            int[][] matrix = new int[sizeX][];
            for (int i = 0; i < sizeX; i++) {
                matrix[i] = new int[sizeY];
                for (int j = 0; j < sizeY; j++) {
                    matrix[i][j] = random.nextInt(((MAX_MATRIX_ELEMENT - MIN_MATRIX_ELEMENT) + 1)) + MIN_MATRIX_ELEMENT;
                }
            }
            return matrix;
        }

    public int[][] compactMatrix(int[][] matrix, int sizeX, int sizeY) {
        List<Integer> rowIndexes = getNotNullIndexes(matrix, sizeX, sizeY, true);
        List<Integer> columnIndexes = getNotNullIndexes(matrix, sizeY, sizeX, false);
        int[][] out = new int[rowIndexes.size()][];
        int x = 0, y = 0;
        for (int i = 0; i < rowIndexes.size(); i++) {
            out[i] = new int[columnIndexes.size()];
            for (int j = 0; j < columnIndexes.size(); j++) {
                out[i][j] = matrix[rowIndexes.get(i)][columnIndexes.get(j)];
            }
        }
        return out;
    }

    public String matrixToString(int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for(int[] row : matrix){
            builder.append(Arrays.toString(row).replace("[","").replace("]",System.lineSeparator()));
        }
        return builder.toString();
    }

    private List<Integer> getNotNullIndexes(int[][] matrix, int sizeOuter, int sizeInner, boolean rowRunningMode) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < sizeOuter; i++) {
            boolean isNotEmpty = false;
            for (int j = 0; j < sizeInner; j++) {
                int element = rowRunningMode ? matrix[i][j] : matrix[j][i];
                if (element != 0) {
                    isNotEmpty = true;
                    break;
                }
            }
            if (isNotEmpty) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
