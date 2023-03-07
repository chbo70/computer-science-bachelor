package at.ac.uibk.pm.g06.csaz9837.s04.e04;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private int MAX_SIZE = 10;
    private List<Item> itemManager = new ArrayList<>();

    public ItemManager(int MAX_SIZE, List<Item> itemManager) {
        this.MAX_SIZE = MAX_SIZE;
        this.itemManager = itemManager;
        if (MAX_SIZE < 0) {
            throw new IllegalArgumentException();
        }
    }

    public int size() {
        return itemManager.size();
    }

    public void addItem(Item item) throws FullItemListException {
        itemManager.add(item);
        if (itemManager.size() > MAX_SIZE) {
            throw new FullItemListException(item);
        }
    }

    public void removeItem(Item item) throws NoSuchItemFoundException {
        if (!itemManager.contains(item)) {
            throw new NoSuchItemFoundException(item);
        }
        itemManager.remove(item);
    }

    public double totalPrice() {
        double sum = 0;
        for (int i = 0; i < itemManager.size(); ++i) {
            sum += itemManager.get(i).getPrice();
        }
        return sum;
    }

    public List<Item> getItemManager() {
        return itemManager;
    }

    public void print() {
        for (int i = 0; i < itemManager.size(); ++i) {
            System.out.println(itemManager.get(i));
        }
    }
}
