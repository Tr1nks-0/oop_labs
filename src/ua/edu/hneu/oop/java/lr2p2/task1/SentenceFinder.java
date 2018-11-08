package ua.edu.hneu.oop.java.lr2p2.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceFinder {
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("(?muU)(?<= )*([\\w, :-]*[.!?])(?= )*");

    public List<String> splitOnSentences(String input) {
        Matcher matcher = SENTENCE_PATTERN.matcher(input);
        List<String> sentences = new ArrayList<>();
        while (matcher.find()) {
            sentences.add(matcher.group(1));
        }
        return sentences;
    }
}
