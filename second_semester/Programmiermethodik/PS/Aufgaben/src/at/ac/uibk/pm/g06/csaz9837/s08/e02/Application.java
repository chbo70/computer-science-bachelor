package at.ac.uibk.pm.g06.csaz9837.s08.e02;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;

public class Application {

    public static void main(String[] args){
        if (args.length < 1) {
            System.err.println("usage: Application <path to CSV file>");
            return;
        }
        Path path = Path.of(args[0]);
        try {
            Inventory shoppingCart = new Inventory(CSVToArray.readCsv(path));
            //System.out.println("Initial ShoppingCart: ");
            //System.out.println(shoppingCart);
            //System.out.println("\nSorted Alphabetically: ");
            //System.out.println(shoppingCart.getAllItemsAlphabetically());
            System.out.println("\nShoppingCart sorted by price: ");
            shoppingCart.sortItemsByPrice();
            System.out.println(shoppingCart);
            System.out.println("\nShoppingCart sorted by foodtype");
            shoppingCart.sortItemsByFoodType();
            System.out.println(shoppingCart);
            System.out.println("\nShoppingCart multiply FRUIT by 10%:");
            shoppingCart.increasePrices();
            System.out.println(shoppingCart);
            System.out.println("Removing expired Products: ");
            shoppingCart.removeExpiredProducts(LocalDate.of(2022, Month.MAY, 26));
            System.out.println(shoppingCart);
            //System.out.println("\nShoppingCart MEAT with UpperBoundPrice of 5: ");
            //System.out.println(shoppingCart.getItems(FoodType.MEAT, BigDecimal.valueOf(5)));
            //System.out.println(shoppingCart.getTotalPrice());
            //System.out.println(shoppingCart.getAveragePrice(FoodType.MEAT));
            //System.out.println(shoppingCart.getItems(FoodType.MEAT));
        } catch (IOException e) {
            System.err.printf("Error: Unable to successfully read from file %s!".formatted(path));
        }

    }
}
