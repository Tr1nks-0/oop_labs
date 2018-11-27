package ua.edu.hneu.oop.java.lr5.task1;

import ua.edu.hneu.oop.java.util.ConsoleUtil;
import ua.edu.hneu.oop.java.util.FileUtil;

import java.io.IOException;

public class MainClass {
    private static final String FILE_NAME = "/text.txt";
    private static final String PRINT_FORMAT = "Initial text :\n\n\"%s\"\n\nProcessed text:\n\n\"%s\"\n";
    private static final String IO_EXCEPTION_FORMAT = "Something went wrong with obtaining value. Original error is:\"%s\".%n%n";

    private static ConsoleUtil consoleUtil;
    private static String text;
    private static BackspasableTextBuffer textBuffer;

    public static void main(String[] args) {
        try {
            init();
            textBuffer.append(text);
            consoleUtil.printf(PRINT_FORMAT,text,textBuffer.getText());
        } catch (IOException e) {
            consoleUtil.printf(IO_EXCEPTION_FORMAT, e.getMessage());
        }
    }

    private static void init() throws IOException {
        consoleUtil = ConsoleUtil.getInstance();
        FileUtil fileUtil = new FileUtil();
        text = fileUtil.loadFileText(FILE_NAME);
        textBuffer=new BackspasableTextBuffer();
    }
}
