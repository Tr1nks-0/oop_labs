package ua.edu.hneu.oop.java.lr6.task1.domain.entity;

public class BMWCar extends AbstractCar {
    @Override
    public void maxSpeed(AbstractEngine engine) {
        System.out.println("Макcимальная скорость" + engine.getMaxSpeed());
    }
}
