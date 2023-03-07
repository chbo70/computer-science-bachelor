package at.ac.uibk.pm.inheritance.vehicles.noinheritance;

/**
 * Class provides means for storing information about trucks.
 */
public class Truck {
    private String licensePlate;
    private String location;
    private String cargo;

    /**
     * Constructs a truck object with given information.
     *
     * @param licensePlate the license plate of the vehicle.
     * @param location     the location where the vehicle is parked.
     * @param cargo        description of the cargo.
     */
    public Truck(String licensePlate, String location, String cargo) {
        this.licensePlate = licensePlate;
        this.location = location;
        this.cargo = cargo;
    }

    /**
     * Returns relevant information about the truck.
     *
     * @return String containing relevant information.
     */
    public String getInfo() {
        return String.format("%s at %s", licensePlate, location);
    }

    /**
     * Returns description of the truck.
     *
     * @return cargo of truck.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Changes information about the cargo of the truck.
     *
     * @param cargo description of the cargo.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
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