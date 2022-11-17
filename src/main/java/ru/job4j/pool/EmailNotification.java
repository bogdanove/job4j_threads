package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final static String TEMPLATE_SUBJECT = "Notification {username} to email {email}";
    private final static String TEMPLATE_BODY = "Add a new event to {username}";

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(
                () -> {
                    var subject = TEMPLATE_SUBJECT.replace("{username}",
                            user.getUsername()).replace("{email}", user.getEmail());
                    var body = TEMPLATE_BODY.replace("{username}",
                            user.getUsername());
                    send(subject, body, user.getEmail());
                });
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
