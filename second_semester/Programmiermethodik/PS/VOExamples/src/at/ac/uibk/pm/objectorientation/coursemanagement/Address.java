package at.ac.uibk.pm.objectorientation.coursemanagement;

/**
 * Class provides address information.
 */
public class Address {

    private String street;
    private int streetNumber;
    private String town;
    private int zipCode;

    /**
     * Returns street number.
     *
     * @return street number.
     */
    public int getStreetNumber() {
        return this.streetNumber;
    }

    /**
     * Sets street number.
     *
     * @param streetNumber number to be set.
     */
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Returns the street.
     *
     * @return the street.
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Sets the street to given string.
     *
     * @param street street name to be set.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns town.
     *
     * @return town.
     */
    public String getTown() {
        return this.town;
    }

    /**
     * Sets town to given string.
     *
     * @param town town to be set.
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Returns zip code.
     *
     * @return zip code
     */
    public int getZipCode() {
        return this.zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode code to be set.
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Sets address.
     *
     * @param street       the street
     * @param streetNumber the street number
     * @param town         the town
     * @param zipCode      the zipcode of the given town
     */
    public void setAddress(String street, int streetNumber, String town, int zipCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.town = town;
        this.zipCode = zipCode;
    }

}