package ru.job4j.pool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {

    private final Object[] array;
    private final Object desired;


    public ParallelSearch(Object[] array, Object desired) {
        this.array = array;
        this.desired = desired;
    }


    @Override
    protected Integer compute() {
        if (array.length < 10) {
            return search();
        }
        var mid = array.length / 2;
        var left = new ParallelSearch(Arrays.copyOfRange(array, 0, mid), desired);
        var right = new ParallelSearch(Arrays.copyOfRange(array, mid + 1, array.length), desired);
        left.fork();
        right.fork();
        var res1 = right.join();
        var res2 = left.join();
        return merge(res1, res2);
    }

    private Integer search() {
        for (var i = 0; i < array.length; i++) {
            if (array[i].equals(desired)) {
                return i;
            }
        }
        return null;
    }

    private Integer merge(Integer first, Integer second) {
        if (first == null && second == null) {
            return null;
        }
        return first == null ? second : first;
    }

}
