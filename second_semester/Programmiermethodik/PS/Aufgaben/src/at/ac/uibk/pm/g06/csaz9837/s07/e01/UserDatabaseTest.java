package at.ac.uibk.pm.g06.csaz9837.s07.e01;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserDatabaseTest {
    @Test
    void testSave(){
        InMemoryDatabase<String, Entity<String>> database = new InMemoryDatabase<>();
        User<String> user1 = new User<>("chbo70", "Chi", "Boon-Chung");
        assertEquals(user1, database.save(user1));
    }
    @Test
    void testSaveException(){
        InMemoryDatabase<String, Entity<String>> database = new InMemoryDatabase<>();
        User<String> user1 = new User<>("chbo70", "Chi", "Boon-Chung");
        database.save(user1);
        assertThrows(IllegalArgumentException.class, () -> database.save(user1));
    }
    @Test
    void testDelete(){
        InMemoryDatabase<String, Entity<String>> database = new InMemoryDatabase<>();
        User<String> user1 = new User<>("chbo70", "Chi", "Boon-Chung");
        database.save(user1);
        assertEquals(user1, database.delete(user1));
    }
    @Test
    void testDeleteException(){
        InMemoryDatabase<String, Entity<String>> database = new InMemoryDatabase<>();
        User<String> user1 = new User<>("chbo70", "Chi", "Boon-Chung");
        assertThrows(NoSuchElementException.class, () -> database.delete(user1));
    }
    @Test
    void testFindOne(){
        InMemoryDatabase<String, Entity<String>> database = new InMemoryDatabase<>();
        User<String> user1 = new User<>("chbo70", "Chi", "Boon-Chung");
        Optional<Entity<String>> opt = Optional.of(user1);
        database.save(user1);
        assertEquals(opt, database.findOne("chbo70"));
    }
    @Test
    void testFindAll(){
        InMemoryDatabase<String, Entity<String>> database = new InMemoryDatabase<>();
        User<String> user1 = new User<>("chbo70", "Chi", "Boon-Chung");
        User<String> user2 = new User<>("ahtuz69", "Cena", "John");
        List<Entity<String>> list = new ArrayList<>();
        Comparator<Entity<String>> comp = Comparator.comparing(Entity::getIdentifier);
        database.save(user1);
        database.save(user2);
        list.add(user1);
        list.add(user2);
        list.sort(comp);
        assertEquals(list, database.findAll(comp));
    }
}