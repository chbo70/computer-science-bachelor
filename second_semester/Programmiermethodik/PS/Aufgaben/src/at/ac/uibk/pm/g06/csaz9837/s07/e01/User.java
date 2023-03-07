package at.ac.uibk.pm.g06.csaz9837.s07.e01;

public class User<U> extends Entity<String> {
    private final String name;
    private final String surname;

    public User(String username, String name, String surname) {
        super(username);
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "name: " + name + ", surname: " + surname;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
