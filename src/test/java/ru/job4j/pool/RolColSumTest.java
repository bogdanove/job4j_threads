package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {

    @Test
    void linearCalculate() {
        var arr = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        assertThat(new RolColSum().sum(arr)).isEqualTo(
                new RolColSum.Sums[]{
                        new RolColSum.Sums(6, 12),
                        new RolColSum.Sums(15, 15),
                        new RolColSum.Sums(24, 18)});
    }

    @Test
    void asyncCalculate() throws ExecutionException, InterruptedException {
        var arr = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        assertThat(new RolColSum().asyncSum(arr)).isEqualTo(
                new RolColSum.Sums[]{
                        new RolColSum.Sums(6, 12),
                        new RolColSum.Sums(15, 15),
                        new RolColSum.Sums(24, 18)});
    }
}