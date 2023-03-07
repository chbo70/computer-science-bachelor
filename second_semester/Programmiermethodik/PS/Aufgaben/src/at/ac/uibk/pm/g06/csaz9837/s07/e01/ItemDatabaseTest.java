package at.ac.uibk.pm.g06.csaz9837.s07.e01;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemDatabaseTest {
    @Test
    void testSave(){
        InMemoryDatabase<Long, Entity<Long>> database = new InMemoryDatabase<>();
        Item item1 = new Item(1L, "Ball", 3.99);
        assertEquals(item1,database.save(item1));
    }
    @Test
    void testSaveException(){
        InMemoryDatabase<Long, Entity<Long>> database = new InMemoryDatabase<>();
        Item item1 = new Item(1L, "Ball", 3.99);
        database.save(item1);
        assertThrows(IllegalArgumentException.class, () -> database.save(item1));
    }
    @Test
    void testDelete(){
        InMemoryDatabase<Long, Entity<Long>> database = new InMemoryDatabase<>();
        Item item1 = new Item(1L, "Ball", 3.99);
        database.save(item1);
        assertEquals(item1, database.delete(item1));
    }
    @Test
    void testDeleteException(){
        InMemoryDatabase<Long, Entity<Long>> database = new InMemoryDatabase<>();
        Item item1 = new Item(1L, "Ball", 3.99);
        assertThrows(NoSuchElementException.class, () -> database.delete(item1));
    }
    @Test
    void testFindOne(){
        InMemoryDatabase<Long, Entity<Long>> database = new InMemoryDatabase<>();
        Item item1 = new Item(1L, "Ball", 3.99);
        Optional<Entity<Long>> opt = Optional.of(item1);
        database.save(item1);
        assertEquals(opt, database.findOne(1L));
    }
    @Test
    void testFindAll(){
        InMemoryDatabase<Long, Entity<Long>> database = new InMemoryDatabase<>();
        Item item2 = new Item(2L, "Ice", 1.20);
        Item item1 = new Item(1L, "Ball", 3.99);
        List<Entity<Long>> list = new ArrayList<>();
        Comparator<Entity<Long>> comp = Comparator.comparing(Entity::getIdentifier);
        database.save(item2);
        database.save(item1);

        list.add(item1);
        list.add(item2);
        list.sort(comp);
        System.out.println(database.findAll(comp));
        assertEquals(list, database.findAll(comp));
    }
}