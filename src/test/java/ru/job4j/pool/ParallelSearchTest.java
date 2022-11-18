package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

class ParallelSearchTest {

    @Test
    void whenFound() {
        var array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ParallelSearch.find(array, 5);
        assertThat(ParallelSearch.find(array, 5)).isEqualTo(4);
    }

    @Test
    void whenFindInDataDifferent() {
        var array = new Object[]{1, 2, 3, "4", 5, 6, 7, 8, 9, 10, 11, 11.5};
        assertThat(ParallelSearch.find(array, "4")).isEqualTo(3);
    }

    @Test
    void whenShortArray() {
        var array = new Object[]{1, 2, 3};
        assertThat(ParallelSearch.find(array, 3)).isEqualTo(2);
    }

    @Test
    void whenNotFound() {
        var array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        assertThat(ParallelSearch.find(array, 22)).isEqualTo(-1);
    }
}