package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T desired;
    private final int from;
    private final int to;


    public ParallelSearch(T[] array, T desired, int from, int to) {
        this.array = array;
        this.desired = desired;
        this.from = from;
        this.to = to;
    }


    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return search();
        }
        var mid = from + to / 2;
        var left = new ParallelSearch(array, desired, from, mid);
        var right = new ParallelSearch(array, desired, mid + 1, to);
        left.fork();
        right.fork();
        var res1 = (Integer) right.join();
        var res2 = (Integer) left.join();
        return Math.max(res1, res2);
    }

    private Integer search() {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(desired)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> Integer find(T[] array, T desired) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (Integer) forkJoinPool.invoke(new ParallelSearch(array, desired, 0, array.length - 1));
    }
}