package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    void whenIncrementAndGet() {
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get()).isEqualTo(2);
    }

    @Test
    void whenIncrementWithTwoThreads() throws InterruptedException {
        final CASCount casCount = new CASCount();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        casCount.increment();
                    }
                }
        );
        first.start();
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        casCount.increment();
                    }
                }
        );
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get()).isEqualTo(100);
    }
}