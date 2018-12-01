package ua.edu.hneu.oop.java.lr5.task2;

import java.io.Serializable;
import java.util.Arrays;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int MARKS_SIZE = 5;
    public static final String HEADER = String.format("%-3s | %-16s |%-8s| %-5s | %17s |%-7s%n",
            "№", "Surname", "Initials", "Group", "Marks","average"
    );
    public static final String ROW_FORMAT = "%-3d | %-16s | %-6s | %-5d | %d | %d | %d | %d | %d | %5.2f%n";
    private String surname;
    private String initials;
    private int group;
    private int[] marks;

    public Student() {
        this.marks = new int[MARKS_SIZE];
    }

    public Student(String surname, String initials, int group) {
        this();
        this.surname = surname;
        this.initials = initials;
        this.group = group;
    }

    public Student(String surname, String initials, int group, int[] marks) {
        this.surname = surname;
        this.initials = initials;
        this.group = group;
        this.marks = marks;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public int getMark(int index) {
        checkMarkIndex(index);
        return marks[index];
    }

    public void setMark(int index, int mark) {
        checkMarkIndex(index);
        this.marks[index] = mark;
    }

    public String toTableString(int rowIndex) {
        return String.format(ROW_FORMAT, rowIndex, surname, initials, group, marks[0], marks[1], marks[2], marks[3], marks[4],getAverageMark());
    }

    public double getAverageMark() {
        return Arrays.stream(marks).average().orElse(0);
    }

    private void checkMarkIndex(int index) {
        if (index < 0 || index > MARKS_SIZE - 1) {
            throw new IllegalArgumentException("Index should be in range [ 0 ; " + (MARKS_SIZE - 1) + " ]");
        }
    }
}
