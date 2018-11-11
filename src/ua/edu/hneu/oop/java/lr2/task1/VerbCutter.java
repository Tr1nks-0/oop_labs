package ua.edu.hneu.oop.java.lr2.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VerbCutter {
    private static final String VERB_PATTERN_FORMAT = "(?muU)(?<=\\W)(\\w)*(%s)(?=\\W)";
    private static final String ENDINGS_FILE_NAME = "/verb-endings";

    private static List<String> endings;

    public VerbCutter() throws IOException {
        if (Objects.isNull(endings)) {
            endings = loadEndings();
        }
    }


    public String cutVerbs(String input) {
        return input.replaceAll(String.format(VERB_PATTERN_FORMAT, String.join("|", endings)), "")
                .replaceAll(" {2,}", " ");
    }

    private List<String> loadEndings() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(ENDINGS_FILE_NAME)))) {
            List<String> loaded = new ArrayList<>();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                loaded.add(line.trim());
            }
            return loaded;
        }
    }
}
