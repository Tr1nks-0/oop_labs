package ua.edu.hneu.oop.java.lr6.task3;

public class MainClass {
    public static void main(String[] args) {
        Human jack = new HumanImpl("Jack");

        Human jackProxy = jack.getUnmodifiable();

        System.out.println(jack.getName());
        jack.setName("John");
        System.out.println(jack.getName());

        System.out.println("------ PROXY: ----------");

        System.out.println(jackProxy.getName());
        jackProxy.setName("Mary");
        System.out.println(jackProxy.getName());

    }
}
