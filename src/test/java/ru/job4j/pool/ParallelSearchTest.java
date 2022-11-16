package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

class ParallelSearchTest {

    @Test
    void whenFound() {
        var array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        var search = new ParallelSearch(array, 5);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        assertThat(forkJoinPool.invoke(search)).isEqualTo(4);
    }

    @Test
    void whenFindInDataDifferent() {
        var array = new Object[]{1, 2, 3, "4", 5, 6, 7, 8, 9, 10, 11, 11.5};
        var search = new ParallelSearch(array, "4");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        assertThat(forkJoinPool.invoke(search)).isEqualTo(3);
    }

    @Test
    void whenShortArray() {
        var array = new Object[]{1, 2, 3};
        var search = new ParallelSearch(array, 3);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        assertThat(forkJoinPool.invoke(search)).isEqualTo(2);
    }

    @Test
    void whenNotFound() {
        var array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        var search = new ParallelSearch(array, 22);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        assertThat(forkJoinPool.invoke(search)).isNull();
    }
}