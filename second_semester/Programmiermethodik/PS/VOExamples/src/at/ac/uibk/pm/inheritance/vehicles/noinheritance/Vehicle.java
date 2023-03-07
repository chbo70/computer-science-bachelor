package at.ac.uibk.pm.inheritance.vehicles.noinheritance;

/**
 * Class provides information about a vehicle.
 */
public class Vehicle {
    private String licensePlate;
    private String location;

    /**
     * Constructs a vehicle object with given information.
     *
     * @param licensePlate the license plate of the vehicle.
     * @param location     the location where the vehicle is parked.
     */
    public Vehicle(String licensePlate, String location) {
        this.licensePlate = licensePlate;
        this.location = location;
    }

    /**
     * Returns relevant information about the vehicle.
     *
     * @return String containing relevant information.
     */
    public String getInfo() {
        return String.format("%s at %s", licensePlate, location);
    }


    /**
     * Changes the license plate of the vehicle.
     *
     * @param licensePlate the license plate of the vehicle.
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Changes the location of the vehicle.
     *
     * @param location the location of the vehicle.
     */
    public void setLocation(String location) {
        this.location = location;
    }
}