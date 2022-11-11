package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int next;
        do {
            next = count.get() + 1;
        } while (!count.compareAndSet(count.get(), next));
    }

    public int get() {
        int temp;
        do {
            temp = count.get();
        } while (!count.compareAndSet(count.get(), temp));
        return temp;
    }
}
