package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class GuestAlreadyInBarException extends Exception {
    private Guest guest;

    public GuestAlreadyInBarException(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String getMessage() {
        return guest + " is already in the Bar!";
    }
}
