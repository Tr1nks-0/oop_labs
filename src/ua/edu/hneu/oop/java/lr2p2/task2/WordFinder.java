package ua.edu.hneu.oop.java.lr2p2.task2;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFinder {

    private static final Pattern WORD_PATTERN = Pattern.compile("(?muU)(((^|\\W)[eyuioa]\\w*)|(\\w*[eyuioa]($|\\W)))");

    public Set<String> findAllVowelStartEndWords(String input) {
        Matcher matcher = WORD_PATTERN.matcher(input);
        Set<String> words = new HashSet<>();
        while (matcher.find()) {
            words.add(matcher.group(1).trim());
        }
        return words;
    }
}
