package at.ac.uibk.pm.g06.csaz9837.midterm1.e03;

import java.util.ArrayList;

public class Dealership {
    private int parkingSpots;
    private Car[] parkingLot;

    public Dealership(int parkingSpots) {
        this.parkingSpots = parkingSpots;
        this.parkingLot = new Car[parkingSpots];
    }

    public void parkCar(Car car1, int parkingSpot) throws FullParkingSpot {
        if (parkingLot[parkingSpot] != null) {
            throw new FullParkingSpot("This parkingspot is full!");
        }
        parkingLot[parkingSpot] = car1;
    }
    public void sellCar(int parkingSpot){
        if (parkingLot[parkingSpot] != null){
            parkingLot[parkingSpot] = null;
        }
    }
    public int size() {
        return parkingLot.length;
    }

    public void printParkingLot() {
        for (int i = 1; i < parkingLot.length; i++) {
            if (parkingLot[i] == null) {
                System.out.println(i + ":" + "<EMPTY>");
            } else {
                System.out.println(i + ":" + parkingLot[i]);
            }
        }
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "parkingSpots=" + parkingSpots +
                ", parkingLot=" + parkingLot +
                '}';
    }
}
