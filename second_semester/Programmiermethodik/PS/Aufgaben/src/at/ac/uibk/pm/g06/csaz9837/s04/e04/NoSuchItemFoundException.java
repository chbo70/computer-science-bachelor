package at.ac.uibk.pm.g06.csaz9837.s04.e04;

public class NoSuchItemFoundException extends Exception {
    private Item item;

    public NoSuchItemFoundException(Item item) {
        this.item = item;
    }
}
