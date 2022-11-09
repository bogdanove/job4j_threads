package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {

    public synchronized void saveContent(String content) throws IOException {
        try (var out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("out.txt")
                ))) {
            for (var i = 0; i < content.length(); i++) {
                out.write(content.charAt(i));
            }
        }
    }
}
