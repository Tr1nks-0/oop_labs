package ua.edu.hneu.oop.java.lr2.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSplitter {
    private Pattern PATTERN = Pattern.compile("(?muU)(\\w{2,})(.*)(?=\\1)");

    public List<String> findRepeatableBases(String input) {
        List<String> bases = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(input);
        while (matcher.find()) {
            bases.add(matcher.group(1));
        }
        return bases;
    }

    public String splitWords(String input) {
        List<String> bases = findRepeatableBases(input);
        String out = input;
        int i = 0;
        for (String base : bases) {
            out = out.replaceAll(base, "(" + ++i + ")[" + base + "]");
        }
        return out;
    }
}
