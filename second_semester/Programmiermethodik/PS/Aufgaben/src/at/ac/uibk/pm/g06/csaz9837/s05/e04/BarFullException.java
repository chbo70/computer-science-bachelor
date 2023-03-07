package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class BarFullException extends IndexOutOfBoundsException{
    @Override
    public String getMessage() {
        return "The Bar is Full, go somewhere else!";
    }
}
