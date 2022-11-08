package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter o = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(content.substring(0, 10))
                ))) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}
