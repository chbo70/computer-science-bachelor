package at.ac.uibk.pm.inheritance.vehicles.optimization3;

/**
 * Class provides means for storing information about trucks.
 */

public class Truck extends Vehicle {
    private String cargo;

    /**
     * Constructs a truck object with given information.
     *
     * @param licensePlate the license plate of the vehicle.
     * @param location     the location where the vehicle is parked.
     * @param cargo        description of the cargo.
     */
    public Truck(String licensePlate, String location, String cargo) {
        super(licensePlate, location);
        this.cargo = cargo;
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
     * Returns relevant information about the truck, overrides getInfo()
     * -method of super-class.
     *
     * @return String containing relevant information.
     */
    @Override
    public String getInfo() {
        return String.format("%s (cargo: %s)", super.getInfo(), cargo);
    }
}