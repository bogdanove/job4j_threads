package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var fileNames = url.split("/");
        try (var in = new BufferedInputStream(new URL(url).openStream());
             var fileOutputStream = new FileOutputStream(fileNames[fileNames.length - 1])) {
            var dataBuffer = new byte[1024];
            var bytesRead = 0;
            var start = System.currentTimeMillis();
            var downloadData = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (downloadData >= speed) {
                    var stop = System.currentTimeMillis() - start;
                    if (stop < 1000) {
                        Thread.sleep(1000 - stop);
                    }
                    downloadData = 0;
                    start = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private static boolean validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        if (!args[0].startsWith("http")) {
            throw new IllegalArgumentException("Invalid URL");
        }
        if (Integer.parseInt(args[1]) <= 0) {
            throw new IllegalArgumentException("Invalid speed");
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        if (validateArgs(args)) {
            var url = args[0];
            var speed = Integer.parseInt(args[1]);
            var wget = new Thread(new Wget(url, speed));
            wget.start();
            wget.join();
        }
    }
}