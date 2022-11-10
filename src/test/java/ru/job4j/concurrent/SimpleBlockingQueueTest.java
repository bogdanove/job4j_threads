package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {

    @Test
    void whenAddOneAndTakeOne() throws InterruptedException {
        var queue = new SimpleBlockingQueue<Integer>(1);
        var list1 = List.of(6, 5, 4, 3, 2, 1);
        var first = new Thread(() -> {
            list1.forEach(i -> {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        var list2 = new ArrayList<Integer>();
        var second = new Thread(() -> {
            while (list2.size() != list1.size()) {
                try {
                    list2.add(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list2.get(list2.size() - 1)).isEqualTo(1);
    }

    @Test
    void whenQueueIsEmpty() {
        var queue = new SimpleBlockingQueue<Integer>(1);
        final int[] result = {0};
        var second = new Thread(() -> {
            try {
                result[0] = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        second.start();
        assertThat(result[0]).isEqualTo(0);
    }
}