package ua.edu.hneu.oop.java.lr6.task1;

import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractCar;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractEngine;
import ua.edu.hneu.oop.java.lr6.task1.domain.factory.CarFactory;

public class Client {
    private AbstractCar abstractCar;
    private AbstractEngine abstractEngine;

    public Client(CarFactory car_factory) {
        abstractCar = car_factory.createCar();
        abstractEngine = car_factory.createEngine();
    }

    public void run() {
        abstractCar.maxSpeed(abstractEngine);
    }
}
