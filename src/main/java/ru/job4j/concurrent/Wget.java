package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Wget {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        Thread thread = new Thread(
                () -> {
                    try {
                        while (counter.get() < 101) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + counter + "%");
                            counter.getAndIncrement();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
