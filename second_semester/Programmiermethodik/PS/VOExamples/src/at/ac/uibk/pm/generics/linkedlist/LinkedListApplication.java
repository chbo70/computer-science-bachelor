package at.ac.uibk.pm.generics.linkedlist;

/**
 * Class serves as test class for LinkedList implementation.
 */
public class LinkedListApplication {
    /**
     * The main method for testing the linked list application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        LinkedList<Integer> integerList = new LinkedList<>();
        integerList.add(3);
        integerList.add(2);
        integerList.add(1);

        LinkedList<Number> numberList = new LinkedList<>();
        numberList.add(2.3);
        numberList.add(1);
        System.out.println(numberList);

        numberList.append(integerList);
        System.out.println(numberList);

        numberList.clear();
        numberList.append(numberList);
        System.out.println(numberList);
    }
}
