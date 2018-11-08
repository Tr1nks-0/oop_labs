package ua.edu.hneu.oop.java.lr2p2.task1;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainClass {
    private static final String FILE_NAME = "/sentences.txt";
    public static final String SENTENCES_COUNT_FORMAT = "%n%nTotal sentences count = %d";
    private static final String INVITATION = "For highlight next sentence press any button." + System.lineSeparator() +
            "After last sentence total sentences count will be shown";
    private static SentenceFinder sentenceFinder;
    private static ConsoleUtil consoleUtil;
    private static String text;


    public static void main(String[] args) {
        try {
            init();
            List<String> sentences = sentenceFinder.splitOnSentences(text);
            int index = 0;
            consoleUtil.print(INVITATION);
            while (index < sentences.size()) {
                consoleUtil.readInput();
                consoleUtil.printf(
                        IntStream.range(0, 5).mapToObj(i -> "%1$s").collect(Collectors.joining()) + "%2$s",
                        "\n",
                        IntStream.range(0, 20).mapToObj(i -> "-").collect(Collectors.joining()) + System.lineSeparator());
                printSentences(index++, sentences);
            }
            consoleUtil.printf(SENTENCES_COUNT_FORMAT, sentences.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printSentences(int index, List<String> sentences) {
        for (int i = 0; i < index; i++) {
            consoleUtil.print(sentences.get(i));
        }
        consoleUtil.printf("[%1$s]", sentences.get(index));
        for (int i = index + 1; i < sentences.size(); i++) {
            consoleUtil.print(sentences.get(i));
        }
    }

    private static void init() throws IOException {
        sentenceFinder = new SentenceFinder();
        consoleUtil = new ConsoleUtil();
        loadText();
    }

    private static void loadText() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(MainClass.class.getResourceAsStream(FILE_NAME)))) {
            StringBuilder builder = new StringBuilder();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                builder.append(line);
            }
            text = builder.toString().trim();
        }
    }
}
