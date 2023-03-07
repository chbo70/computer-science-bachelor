package at.ac.uibk.pm.g06.csaz9837.midterm1.e03;

import java.util.ArrayList;

public class Car {
    private String serialnumber;
    private Condition carCondition;
    private int mileage;                //tells me how much the car has been droven in miles

    public Car(String serialnumber, Condition carCondition, int mileage) {
        this.serialnumber = serialnumber;
        this.carCondition = carCondition;
        this.mileage = mileage;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Condition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(Condition carCondition) {
        this.carCondition = carCondition;
    }

    public int getMileage() {
        if (carCondition == Condition.NEW){
            return 0;
        }
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return serialnumber + " (" + carCondition + ", " + getMileage() + "km)";
    }
}
