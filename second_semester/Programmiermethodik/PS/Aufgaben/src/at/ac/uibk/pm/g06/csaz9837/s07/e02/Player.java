package at.ac.uibk.pm.g06.csaz9837.s07.e02;

public class Player<T> {
    //Player Class  = Players, Teams, Cars ...
    private T name;

    public Player(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\n\tPlayer: " +
                "name=" + name +
                '}';
    }
}
