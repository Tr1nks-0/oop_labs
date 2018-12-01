package ua.edu.hneu.oop.java.lr5.task2;

import ua.edu.hneu.oop.java.util.ConsoleUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private static final String INVITATION = "Enter a command:%n>>>";
    private static final String HELP_DESCRIPTION = "Print this help.";
    private static final String DESCRIPTION_FORMAT = "%-10s  ---  %s";
    private static final String PATTERN_FORMAT = "(?i)^%s";
    private static final String[] HELP_COMMAND_KEYS = {"h", "help"};
    private static final String COMMAND_NOT_FOUND = "Entered command not found. For help enter help.";
    private List<String> menuDescriptions;
    private Map<String, VoidMethod> menu;
    private ConsoleUtil consoleUtil;

    public Menu(ConsoleUtil consoleUtil) {
        this.consoleUtil = consoleUtil;
        menuDescriptions = new ArrayList<>();
        menu = new HashMap<>();
        addMenuRow(HELP_DESCRIPTION, this::printHelp, HELP_COMMAND_KEYS);
    }

    public void addMenuRow(String description, VoidMethod action, String... keys) {
        appendDescription(description, keys);
        menu.put(toPattern(keys), action);
    }

    public void process() throws IOException, ClassNotFoundException {
        consoleUtil.printf(INVITATION);
        processInput(consoleUtil.readInput());
    }

    public void printHelp() {
        menuDescriptions.forEach(s -> consoleUtil.println(s));
        consoleUtil.printf("%n");
    }

    private void processInput(String command) throws IOException, ClassNotFoundException {
        menu.entrySet()
                .stream()
                .filter(p -> command.matches(p.getKey()))
                .map(Map.Entry::getValue)
                .findAny()
                .orElse(this::defaultCommand).call();
    }

    private void defaultCommand() {
        consoleUtil.println(COMMAND_NOT_FOUND);
    }

    private void appendDescription(String description, String... key) {
        menuDescriptions.add(String.format(DESCRIPTION_FORMAT, keysJoining(key), description));
    }

    private String toPattern(String... key) {
        return String.format(PATTERN_FORMAT, keysJoining(key));
    }

    private String keysJoining(String... key) {
        return String.join("|", key);
    }
}
