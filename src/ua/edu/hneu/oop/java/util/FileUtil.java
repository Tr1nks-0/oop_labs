package ua.edu.hneu.oop.java.util;

import ua.edu.hneu.oop.java.lr2p2.task2.MainClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {
    public String loadFileText(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(MainClass.class.getResourceAsStream(filename)))) {
            StringBuilder builder = new StringBuilder();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                builder.append(line);
            }
            return builder.toString().trim();
        }
    }
}
