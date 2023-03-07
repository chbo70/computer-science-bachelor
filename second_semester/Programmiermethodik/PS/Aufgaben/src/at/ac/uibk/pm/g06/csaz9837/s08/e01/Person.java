package at.ac.uibk.pm.g06.csaz9837.s08.e01;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record Person(String firstName, String lastName, LocalDate birthdate) {
    @Override
    public String toString() {
        return "FirstName/LastName/Birthday: " + firstName + "/ " + lastName + "/ " + birthdate;
    }

    static class PersonManager {
        private List<Person> personList;

        public PersonManager(List<Person> personList1) {
            this.personList = new ArrayList<>(personList1);
        }

        public List<Person> sortByFirstName() {
            return personList.stream().sorted(Comparator.comparing(Person::firstName)).collect(Collectors.toList());
        }

        public List<Person> sortByLastName() {
            return personList.stream().sorted(Comparator.comparing(Person::lastName)).collect(Collectors.toList());
        }

        public List<Person> sortByBirthDate() {
            return personList.stream().sorted(Comparator.comparing(Person::birthdate)).collect(Collectors.toList());
        }

        public void printList(List<Person> personList) {
            for (int i = 0; i < personList.size(); i++) {
                System.out.println(personList.get(i));
            }
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("Jeff", "Manson", LocalDate.of(1992, Month.JUNE, 21));
        Person person2 = new Person("Danielson", "Rufi", LocalDate.of(2002, Month.MAY, 1));
        Person person3 = new Person("Martin", "Loaker", LocalDate.of(2010, Month.MARCH, 30));
        Person person4 = new Person("Bob", "Bottom", LocalDate.of(1989, Month.OCTOBER, 15));
        Person person5 = new Person("Christian", "Dior", LocalDate.of(1999, Month.DECEMBER, 17));
        Person person6 = new Person("Johny", "Five", LocalDate.of(1999, Month.JANUARY, 23));
        Person person7 = new Person("Austin", "Lampert", LocalDate.of(2015, Month.JULY, 28));
        Person person8 = new Person("Trymacs", "Moin", LocalDate.of(1990, Month.JUNE, 4));
        List<Person> listOfPersons = new ArrayList<>();
        listOfPersons.add(person1);
        listOfPersons.add(person2);
        listOfPersons.add(person3);
        listOfPersons.add(person4);
        listOfPersons.add(person5);
        listOfPersons.add(person6);
        listOfPersons.add(person7);
        listOfPersons.add(person8);
        PersonManager personManager = new PersonManager(listOfPersons);
        System.out.println("Before sorting: ");
        personManager.printList(listOfPersons);
        System.out.println("\nAfter firstName sorting: ");
        personManager.printList(personManager.sortByFirstName());
        System.out.println("\nAfter lastName sorting: ");
        personManager.printList(personManager.sortByLastName());
        System.out.println("\nAfter birthdate sorting: ");
        personManager.printList(personManager.sortByBirthDate());
    }
}
