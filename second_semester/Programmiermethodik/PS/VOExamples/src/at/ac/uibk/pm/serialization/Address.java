package at.ac.uibk.pm.serialization;

import java.io.Serializable;

/**
 * Class provides (not necessarily complete) information about an address,
 * demonstrates shallow copies.
 */
public class Address implements Cloneable, Serializable {
    static final long serialVersionUID = 42L;

    private String town;

    /**
     * Constructs the address object with town information.
     *
     * @param town town information.
     */
    public Address(String town) {
        this.town = town;
    }

    /**
     * returns a String representation of the given object.
     *
     * @return string representation of town.
     */
    @Override
    public String toString() {
        return this.town;
    }

    /**
     * Setter for town information.
     *
     * @param town the town to be set.
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Clones the given address and returns a shallow copy of this address.
     *
     * @return the cloned Address object
     */
    @Override
    public Address clone() throws CloneNotSupportedException {
        // shallow copy suffices
        return (Address) super.clone();
    }
}