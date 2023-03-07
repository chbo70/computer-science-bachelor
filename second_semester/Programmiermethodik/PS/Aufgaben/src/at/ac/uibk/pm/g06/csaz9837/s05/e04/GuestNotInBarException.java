package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class GuestNotInBarException extends Exception{
    private Guest guest;

    public GuestNotInBarException(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String getMessage() {
        return guest + " is not in this Bar!";
    }
}
