package at.ac.uibk.pm.g06.csaz9837.s02.e02;

public class Customer {
    //public int creditRating;
    private String name;
    private String surname;
    enum creditRating {low,medium,high};
    private creditRating customerRating;

    public Customer(String name, String surname, creditRating customerRating) {
        this.name = name;
        this.surname = surname;
        this.customerRating = customerRating;
    }

    public creditRating getCustomerRating() {
        return customerRating;
    }
}
