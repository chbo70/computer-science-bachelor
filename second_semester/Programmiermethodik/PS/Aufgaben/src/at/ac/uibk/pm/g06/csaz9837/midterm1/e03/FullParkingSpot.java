package at.ac.uibk.pm.g06.csaz9837.midterm1.e03;

public class FullParkingSpot extends Exception{
    private String message;

    public FullParkingSpot(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
