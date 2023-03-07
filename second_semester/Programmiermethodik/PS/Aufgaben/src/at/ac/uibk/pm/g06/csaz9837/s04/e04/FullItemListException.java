package at.ac.uibk.pm.g06.csaz9837.s04.e04;

public class FullItemListException extends Exception {
    private Item item;

    public FullItemListException(Item item) {
        this.item = item;
    }

}
