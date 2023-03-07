package at.ac.uibk.pm.g06.csaz9837.s09.e01;

import java.util.List;

public class Restaurants {
    private int ID;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String type;

    public Restaurants(int ID, String name, String address, String city, String phone, String type) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.type = type;
    }
    public Restaurants( String name, String address, String city, String phone, String type) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }


    public String getType() {
        return type;
    }


}
