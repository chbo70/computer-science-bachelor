package at.ac.uibk.pm.g06.csaz9837.midterm1.e03;

import java.util.ArrayList;

public class Exercise3Application {
    public static void main(String[] args) throws FullParkingSpot {
        Dealership dealership = new Dealership(10);
        Car car1 = new Car("VALLAH12", Condition.NEW, 0);
        dealership.parkCar(car1, 2);
        dealership.parkCar(car1, 3);
        dealership.printParkingLot();
        System.out.println("-----------------------------------------");
        dealership.sellCar(2);
        dealership.printParkingLot();
    }
}
