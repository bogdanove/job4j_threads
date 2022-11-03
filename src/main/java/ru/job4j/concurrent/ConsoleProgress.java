package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        try {
            var process = new String[]{"-", "\\", "|", "/"};
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                if (i == 4) {
                    i = 0;
                }
                System.out.print("\r load: " + process[i]);
                i++;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
    }
}
