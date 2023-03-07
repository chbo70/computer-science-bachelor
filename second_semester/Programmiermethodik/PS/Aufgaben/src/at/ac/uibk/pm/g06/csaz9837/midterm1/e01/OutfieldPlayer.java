package at.ac.uibk.pm.g06.csaz9837.midterm1.e01;

public class OutfieldPlayer {
    private String name;
    private Position position;

    public OutfieldPlayer(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "OutfieldPlayer { " +
                "name = '" + name + '\'' +
                ", position = " + position +
                '}';
    }
}
