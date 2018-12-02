package ua.edu.hneu.oop.java.lr6.task1;

import ua.edu.hneu.oop.java.lr6.task1.domain.factory.AudiFactory;
import ua.edu.hneu.oop.java.lr6.task1.domain.factory.BMWFactory;
import ua.edu.hneu.oop.java.lr6.task1.domain.factory.CarFactory;

public class MainClass {
    public static void main(String[] args) {
        CarFactory bmwCarFactory = new BMWFactory();
        CarFactory audiCarFactory = new AudiFactory();
        Client client1 = new Client(bmwCarFactory);
        client1.run();
        Client client2 = new Client(audiCarFactory);
        client2.run();
    }
}
