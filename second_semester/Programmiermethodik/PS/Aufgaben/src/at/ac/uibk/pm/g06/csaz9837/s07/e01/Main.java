package at.ac.uibk.pm.g06.csaz9837.s07.e01;

public class Main {
    public static void main(String[] args) {
        InMemoryDatabase<String, Entity<String>> database = new InMemoryDatabase<>();
        User<String> user1 = new User<>("chbo70", "Chi", "Boon-Chung");
        User<String> user2 = new User<>("sbcoi89", "Musk", "Elon");
        User<String> user3 = new User<>("uisi60", "Müller", "Mark");
        database.save(user1);
        database.save(user2);
        database.save(user3);
        System.out.println(database.findOne("sbcoi89"));
        System.out.println(database);
        System.out.println("-----------------------------------");
        InMemoryDatabase<Long, Entity<Long>> database1 = new InMemoryDatabase<>();
        Item item1 = new Item(0L,"Ball", 3.90);
        Item item2 = new Item(1L,"Ice Cream", 1.90);
        Item item3 = new Item(2L,"Life", 9999);
        database1.save(item1);
        database1.save(item2);
        database1.save(item3);
        System.out.println(database1.findOne(2L));
        System.out.println(database1);
    }
}
