package at.ac.uibk.pm.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.joining;

/**
 * Class demonstrates serialization and deserialization of objects.
 */
public class ListSerializerApplication {

    private static String getRandomSting(int length) {
        return ThreadLocalRandom.current()
                .ints('a', 'z' + 1)
                .limit(length)
                .mapToObj(Character::toString)
                .collect(joining());
    }

    /**
     * Serializes list of employees (persons) and deserializes these as well.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        Random randomNumber = ThreadLocalRandom.current();
        List<Person> employees = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            employees.add(new Person(getRandomSting(10), getRandomSting(15),
                    randomNumber.nextInt(100), new Address(getRandomSting(10))));
        }
        System.out.println(employees);
        // serialize
        try (FileOutputStream out = new FileOutputStream("employees.ser");
             ObjectOutputStream os = new ObjectOutputStream(out)) {
            os.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // deserialize
        try (FileInputStream in = new FileInputStream("employees.ser");
             ObjectInputStream is = new ObjectInputStream(in)) {
            employees = (List<Person>) is.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        for (Person person : employees) {
            System.out.println(person);
        }
    }
}
