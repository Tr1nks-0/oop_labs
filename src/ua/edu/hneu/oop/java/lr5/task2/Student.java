package ua.edu.hneu.oop.java.lr5.task2;

public class Student {
    public static final int MARKS_SIZE = 5;
    private String surname;
    private int group;
    private int[] marks;

    public Student() {
        this.marks = new int[MARKS_SIZE];
    }

    public Student(String surname, int group) {
        this();
        this.surname = surname;
        this.group = group;
    }

    public Student(String surname, int group, int[] marks) {
        this.surname = surname;
        this.group = group;
        this.marks = marks;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    private void checkMarkIndex(int index) {
        if (index < 0 || index > MARKS_SIZE - 1) {
            throw new IllegalArgumentException("Index should be in range [ 0 ; MARKS_SIZE-1 ]");
        }
    }
}
