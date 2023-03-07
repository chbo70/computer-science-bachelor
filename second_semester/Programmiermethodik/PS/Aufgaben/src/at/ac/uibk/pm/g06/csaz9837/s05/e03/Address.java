package at.ac.uibk.pm.g06.csaz9837.s05.e03;

public class Address {

    private String street;
    private String houseNumber;
    private int zipCode;
    private String city;
    private String country;

    public Address(String street, String housenum, int zipCode, String city, String country) {
        this.street = street;
        this.houseNumber = housenum;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return " " + this.street + " " + this.houseNumber + "\n \t \t  " + this.zipCode + "\n \t \t  " + this.city + "\n \t \t  " + this.country;
    }

}
