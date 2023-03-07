package at.ac.uibk.pm.g06.csaz9837.s04.e04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemManagerTest {
    private List<Item> itemManager = new ArrayList<>();

    @Test
    @DisplayName("MAX_SIZE is smaller than zero")
    void invalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new ItemManager(-1, itemManager).size());
    }

    @Test
    @DisplayName("Items are correctly added")
    void correctlyAddedItems() throws FullItemListException {
        ItemManager itemManagerList = new ItemManager(5, itemManager);
        Item item = new Item("Mouse", 10.50);
        itemManagerList.addItem(item);
        itemManagerList.addItem(item);
        assertEquals(2, itemManagerList.size());
    }

    @Test
    @DisplayName("ItemsManager is full, new Items can't be added")
    void itemManagerFullTest() throws FullItemListException {
        ItemManager itemManagerList = new ItemManager(2, itemManager);
        Item item = new Item("Shoes", 49.99);
        //System.out.println(itemManagerList.size());
        itemManagerList.addItem(item);
        itemManagerList.addItem(item);
        System.out.println(itemManagerList.size());
        assertThrows(FullItemListException.class,
                () -> {
                    itemManagerList.addItem(item);
                });
    }

    @Test
    @DisplayName("Items are removed correctly")
    void correctlyRemovedItems() throws FullItemListException, NoSuchItemFoundException {
        ItemManager itemManagerList = new ItemManager(2, itemManager);
        Item item1 = new Item("Shoes", 49.99);
        Item item2 = new Item("Glasses", 409.99);
        itemManagerList.addItem(item1);
        itemManagerList.addItem(item2);
        itemManagerList.removeItem(item1);
        assertEquals(1, itemManagerList.size());
        assertThrows(NoSuchItemFoundException.class, () -> itemManagerList.removeItem(item1));
    }

    @Test
    @DisplayName("Correctly calculated Total Price")
    void isTotalPriceCorrect() throws FullItemListException {
        ItemManager itemManagerList = new ItemManager(2, itemManager);
        Item item1 = new Item("Shoes", 49.99);
        Item item2 = new Item("Glasses", 409.99);
        itemManagerList.addItem(item1);
        itemManagerList.addItem(item2);
        assertEquals(49.99 + 409.99, itemManagerList.totalPrice());
    }
}