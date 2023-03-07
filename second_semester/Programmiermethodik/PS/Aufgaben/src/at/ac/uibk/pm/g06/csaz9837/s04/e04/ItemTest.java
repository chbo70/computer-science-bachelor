package at.ac.uibk.pm.g06.csaz9837.s04.e04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    @DisplayName("name test")
    void nameTest() {
        Item item = new Item("pencil", 2.99);
        assertEquals("pencil", item.getName());
    }

    @Test
    @DisplayName("price test")
    void priceTest() {
        Item item = new Item("pencil", 2.99);
        assertEquals(2.99, item.getPrice());
    }

    @Test
    @DisplayName("ConstructorException")
    void invalidConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, 2.90));
    }

}