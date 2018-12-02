package ua.edu.hneu.oop.java.lr6.task1.domain.factory;

import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractCar;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AbstractEngine;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AudiCar;
import ua.edu.hneu.oop.java.lr6.task1.domain.entity.AudiEngine;

public class AudiFactory extends CarFactory {
    @Override
    public AbstractCar createCar() {
        return new AudiCar();
    }

    @Override
    public AbstractEngine createEngine() {
        return new AudiEngine();
    }
}
