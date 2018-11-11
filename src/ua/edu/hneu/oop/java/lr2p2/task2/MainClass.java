package ua.edu.hneu.oop.java.lr2p2.task2;

import ua.edu.hneu.oop.java.util.ConsoleUtil;
import ua.edu.hneu.oop.java.util.FileUtil;

import java.io.IOException;
import java.util.Set;

public class MainClass {
    private static final String FILE_NAME = "/sentences.txt";
    private static final String INVITATION = "All words start or end with vowel:";
    private static WordFinder wordFinder;
    private static ConsoleUtil consoleUtil;
    private static String text;


    public static void main(String[] args) {
        try {
            init();
            consoleUtil.println(INVITATION);
            Set<String> sentences = wordFinder.findAllVowelStartEndWords(text);
            sentences.forEach(consoleUtil::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void init() throws IOException {
        wordFinder = new WordFinder();
        consoleUtil = ConsoleUtil.getInstance();
        FileUtil fileUtil = new FileUtil();
        text = fileUtil.loadFileText(FILE_NAME);
    }
}
