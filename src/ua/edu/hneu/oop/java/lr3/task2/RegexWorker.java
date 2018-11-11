package ua.edu.hneu.oop.java.lr3.task2;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexWorker {
    private Pattern pattern;
    private String text;

    public boolean contains() {
        return pattern.matcher(text).find();
    }

    public void printAllMatches() {
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            ConsoleUtil.getInstance().println(matcher.group(1));
        }
    }

    public void removeAllMatches() {
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll("");
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
