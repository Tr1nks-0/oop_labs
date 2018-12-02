package ua.edu.hneu.oop.java.lr6.task3;

public class HumanImpl implements Human {
    private String name;

    public HumanImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
