package ua.edu.hneu.oop.java.lr5.task1;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BackspasableTextBuffer {
    private Stack<Character> text = new Stack<>();

    public void append(String str) {

        str.chars().forEach(c -> append((char) c));
    }

    public void backspace() {
        backspace(1);
    }

    public void backspace(int count) {
        append(IntStream.range(0, count).mapToObj(i -> "#").collect(Collectors.joining()));
    }

    public void append(char c) {
        if ('#' == c) {
            text.pop();
        } else {
            text.push(c);
        }
    }

    public String getText() {
        return text.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
