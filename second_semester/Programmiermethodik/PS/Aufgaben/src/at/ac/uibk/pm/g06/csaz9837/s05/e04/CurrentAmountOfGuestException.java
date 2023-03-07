package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class CurrentAmountOfGuestException extends Exception{
    @Override
    public String getMessage() {
        return "Current Amount of Guest is bigger than Max Amount of Guests!";
    }
}
