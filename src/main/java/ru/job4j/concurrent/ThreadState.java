package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        first.start();
        System.out.println(first.getName());
        second.start();
        System.out.println(second.getName());
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState() + " - " + second.getState());
        }
        System.out.println(Thread.currentThread().getName() + " Done");
    }
}
