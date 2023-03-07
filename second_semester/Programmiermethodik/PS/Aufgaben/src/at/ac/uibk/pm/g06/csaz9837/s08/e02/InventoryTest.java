package at.ac.uibk.pm.g06.csaz9837.s08.e02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void testGetAllItemsAlphabetically(){
        List<Item> testList = new ArrayList<>();
        testList.add(new Item("Apple", FoodType.FRUIT, BigDecimal.valueOf(1.50), LocalDate.of(2022, Month.APRIL, 22)));
        testList.add(new Item("Banana", FoodType.FRUIT, BigDecimal.valueOf(2.50), LocalDate.of(2022, Month.JUNE, 2)));
        testList.add(new Item("Cheetos", FoodType.MUNCHIES, BigDecimal.valueOf(0.50), LocalDate.of(2022, Month.DECEMBER, 11)));

        List<Item> testList2 = new ArrayList<>();
        testList2.add(new Item("Cheetos", FoodType.MUNCHIES, BigDecimal.valueOf(0.50), LocalDate.of(2022, Month.DECEMBER, 11)));
        testList2.add(new Item("Apple", FoodType.FRUIT, BigDecimal.valueOf(1.50), LocalDate.of(2022, Month.APRIL, 22)));
        testList2.add(new Item("Banana", FoodType.FRUIT, BigDecimal.valueOf(2.50), LocalDate.of(2022, Month.JUNE, 2)));
        Inventory shoppingCart2 = new Inventory(testList2);
        assertEquals(testList.get(0), shoppingCart2.getAllItemsAlphabetically().get(0));
        assertEquals(testList.get(1), shoppingCart2.getAllItemsAlphabetically().get(1));
        assertEquals(testList.get(2), shoppingCart2.getAllItemsAlphabetically().get(2));
    }

    @Test
    void testIncreasePrices() {
        List<Item> testList = new ArrayList<>();
        testList.add(new Item("Apple", FoodType.FRUIT, BigDecimal.valueOf(1.50), LocalDate.of(2022, Month.APRIL, 22)));
        testList.add(new Item("Beef", FoodType.MEAT, BigDecimal.valueOf(2.50), LocalDate.of(2022, Month.JUNE, 2)));
        testList.add(new Item("Cheetos", FoodType.MUNCHIES, BigDecimal.valueOf(0.50), LocalDate.of(2022, Month.DECEMBER, 11)));
        Inventory shoppingCart = new Inventory(testList);
        shoppingCart.increasePrices();
        assertEquals(BigDecimal.valueOf(1.65), shoppingCart.getAllItems().get(0).getPrice());
        assertEquals(BigDecimal.valueOf(0.5625), shoppingCart.getAllItems().get(2).getPrice());
        assertEquals(new BigDecimal("3.00"), shoppingCart.getAllItems().get(1).getPrice());
    }

    @Test
    void testRemoveExpiredProducts(){
        List<Item> testList = new ArrayList<>();
        testList.add(new Item("Apple", FoodType.FRUIT, BigDecimal.valueOf(1.50), LocalDate.of(2022, Month.APRIL, 22)));
        testList.add(new Item("Beef", FoodType.MEAT, BigDecimal.valueOf(2.50), LocalDate.of(2022, Month.JUNE, 2)));
        testList.add(new Item("Cheetos", FoodType.MUNCHIES, BigDecimal.valueOf(0.50), LocalDate.of(2022, Month.DECEMBER, 11)));
        Inventory shoppingCart = new Inventory(testList);
        testList.remove(0);
        shoppingCart.removeExpiredProducts(LocalDate.of(2022, Month.MAY, 26));
        assertEquals(testList, shoppingCart.getAllItems());
    }
}