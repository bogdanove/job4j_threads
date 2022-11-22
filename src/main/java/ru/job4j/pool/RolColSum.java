package ru.job4j.pool;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        var result = new Sums[matrix.length];
        var r = 0;
        var c = 0;
        for (int i = 0; i < matrix.length; i++) {
            result[i] = calculate(matrix, i, r, c);
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            var result = new Sums[matrix.length];
            var r = 0;
            var c = 0;
            for (int i = 0; i < matrix.length; i++) {
                result[i] = calculate(matrix, i, r, c);
            }
            return result;
        }).get();
    }

    private static Sums calculate(int[][] matrix, int start, int row, int col) {
        for (int i = 0; i < matrix.length; i++) {
            row += matrix[start][i];
            col += matrix[i][start];
        }
        return new Sums(row, col);
    }
}