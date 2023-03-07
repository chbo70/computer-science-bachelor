package at.ac.uibk.pm.g06.csaz9837.s05.e04;

public class NoNegativeDrinksException extends Exception{
    @Override
    public String toString() {
        return "Not possible to order negative Amount of Drinks";
    }
}
