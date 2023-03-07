package at.ac.uibk.pm.g06.csaz9837.s04.e03;

import java.util.Date;
import java.util.Objects;

public class User {
    private final String firstName;
    private final String lastName;
    private Birthday birthday;
    private final String password;

    public User(String firstName, String lastName, Birthday birthday, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.password = password;
        if (firstName == null || lastName == null || birthday == null || password == null) {
            throw new NullPointerException();
        }
    }

    public String getFirstName() {
        if (firstName.equals("") || !firstName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException();
        }
        return firstName;
    }

    public String getLastName() {
        if (lastName.equals("") || !lastName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException();
        }
        return lastName;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public String getPassword() {
        if (password.equals("")) {
            throw new IllegalArgumentException();
        }
        return password;
    }
}
