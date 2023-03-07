package at.ac.uibk.pm.g06.csaz9837.s08.e02;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

    private List<Item> items;

    public Inventory(List<Item> items) {
        this.items = new ArrayList<>(items);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    public List<Item> getItems(FoodType foodType, BigDecimal priceUpperBound) {
        return items.stream()
                .filter(item -> item.getFoodType() == foodType)
                .filter(item -> item.getPrice().compareTo(priceUpperBound) < 0).collect(Collectors.toList());
    }

    /*
    public List<Item> getItems(FoodType foodType, BigDecimal priceUpperBound) {
        List<Item> itemsPerType = new ArrayList<>();
        for (Item item : items) {
            if ((item.getFoodType() == foodType) && (item.getPrice().compareTo(priceUpperBound) < 0)) {
                itemsPerType.add(item);
            }
        }
        return itemsPerType;
    }
    */

    public List<Item> getItems(FoodType foodType) {
        return items.stream().filter(item -> item.getFoodType() == foodType).collect(Collectors.toList());
    }

    /*
    public List<Item> getItems(FoodType foodType) {
        List<Item> itemsPerType = new ArrayList<>();
        for (Item item : items) {
            if (item.getFoodType() == foodType) {
                itemsPerType.add(item);
            }
        }
        return itemsPerType;
    }
    */
    public List<Item> getAllItemsAlphabetically() {
        return getAllItems().stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
    }

    public BigDecimal getTotalPrice() {
        List<List<String>> listOfLists = null;


        return items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, (bigDecimal, divisor) -> bigDecimal.divide(divisor));
    }

    /*
    public BigDecimal getTotalPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Item item : items) {
            sum = sum.add(item.getPrice());
        }
        return sum;
    }
    */
    public BigDecimal getTotalPrice(FoodType foodType) {
        return items.stream().filter(item -> item.getFoodType() == foodType).map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    /*
    public BigDecimal getTotalPrice(FoodType foodType) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Item item : items) {
            if (item.getFoodType() == foodType) {
                sum = sum.add(item.getPrice());
            }
        }
        return sum;
    }
    */

    public BigDecimal getAveragePrice(FoodType foodType) {
        BigDecimal sum1 = items.stream().filter(item -> item.getFoodType() == foodType).map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum1.divide(BigDecimal.valueOf(items.stream().filter(item -> item.getFoodType() == foodType).map(Item::getPrice).count()), 2, RoundingMode.HALF_EVEN);
    }
    /*
    public BigDecimal getAveragePrice(FoodType foodtype){
    BigDecimal sum = BigDecimal.ZERO;
        int amount = 0;
        for (Item item : items) {
            if (item.getFoodType() == foodType) {
                sum = sum.add(item.getPrice());
                amount++;
            }
        }
        System.out.println(sum);
        return sum.divide(BigDecimal.valueOf(amount), 2, RoundingMode.HALF_EVEN);
    }
    */

    public void increasePrices(){
        items.stream().filter(item -> item.getFoodType() == FoodType.FRUIT).forEach(x -> x.setPrice(x.getPrice().multiply(BigDecimal.valueOf(1.1)).round(new MathContext(3))));
        items.stream().filter(item1 -> item1.getFoodType() == FoodType.MEAT).forEach(item1 -> item1.setPrice(item1.getPrice().multiply(BigDecimal.valueOf(1.2)).round(new MathContext(3))));
        items.stream().filter(item -> item.getFoodType() != FoodType.MEAT).filter(item -> item.getFoodType() != FoodType.FRUIT)
                .forEach(x -> x.setPrice(x.getPrice().multiply(BigDecimal.valueOf(1.125))));
        //.round(new MathContext(3))
    }
    public void removeExpiredProducts(LocalDate today){
        items = items.stream().filter(item -> (item.getExpirationDate().compareTo(today) > 0)).collect(Collectors.toList());
    }
    public void sortItemsByPrice(){
        items.sort(Comparator.comparing(Item::getPrice));
    }

    /*
    public void sortItemsByPrice() {
        int n = items.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (items.get(j - 1).getPrice().compareTo(items.get(j).getPrice()) > 0) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j - 1));
                    items.set(j - 1, temp);
                }
            }
        }
    }
    */
    public void sortItemsByFoodType(){
        items.sort(Comparator.comparing(Item::getFoodType));
    }
    /*
    public void sortItemsByFoodType() {
        int n = items.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (items.get(j - 1).getFoodType().ordinal() > items.get(j).getFoodType().ordinal()) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j - 1));
                    items.set(j - 1, temp);
                }
            }
        }
    }
    */

    @Override
    public String toString() {
        return items.toString();
    }


}
