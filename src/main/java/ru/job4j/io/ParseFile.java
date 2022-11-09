package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) throws IOException {
        var output = new StringBuilder();
        try (var in = new BufferedReader(new FileReader(file))) {
            var data = 0;
            while ((data = in.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public synchronized String getAllContent() throws IOException {
        return getContent(x -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(x -> x < 0x80);
    }
}