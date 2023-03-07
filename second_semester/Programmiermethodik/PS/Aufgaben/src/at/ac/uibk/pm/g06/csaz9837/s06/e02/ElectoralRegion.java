package at.ac.uibk.pm.g06.csaz9837.s06.e02;

public class ElectoralRegion {
    private String name;
    private int maximumSeats;

    public ElectoralRegion(String name, int maximumSeats) {
        this.name = name;
        this.maximumSeats = maximumSeats;
    }

    public int getMaximumSeats() {
        return maximumSeats;
    }

    public void setMaximumSeats(int maximumSeats) {
        this.maximumSeats = maximumSeats;
    }

    @Override
    public String toString() {
        return " " + name;
    }
}
