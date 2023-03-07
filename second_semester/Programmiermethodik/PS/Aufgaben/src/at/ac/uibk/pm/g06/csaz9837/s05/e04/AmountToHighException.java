package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class AmountToHighException extends IndexOutOfBoundsException{

    @Override
    public String getMessage() {
        return "Sorry, we don't have that amount of Drinks";
    }
}
