package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);
    private volatile boolean isRunning = true;

    public void work(Runnable job) throws InterruptedException {
        while (isRunning) {
            if (threads.size() < size) {
                threads.add(new Thread(job));
                tasks.offer(job);
            }
            threads.forEach(Thread::run);
        }

    }

    public void shutdown() {
        isRunning = false;
        threads.forEach(Thread::interrupt);
    }
}
