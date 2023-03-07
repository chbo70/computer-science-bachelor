package at.ac.uibk.pm.inheritance.stackcontainer;

/**
 * Class provides means for testing polymorphism based on containers and stacks.
 */
public class StackContainerApplication {

    /**
     * Main method of StackContainerApplication.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        // use as stack
        Stack methodStack = new ArrayStack();
        methodStack.push("foo");
        methodStack.push("bar");
        while (methodStack.size() > 0) {
            System.out.println(methodStack.pop());
        }

        // use as container
        Container methodContainer = new ArrayStack();
        methodContainer.addElement("foo");
        methodContainer.addElement("bar");

        Iterator methodIterator = methodContainer.iterator();
        while (methodIterator.hasNext()) {
            System.out.println(methodIterator.next());
        }
        System.out.println(methodContainer.size());

        // now use LinkedList as Container
        LinkedList list = new LinkedList();
        methodContainer = list;
        methodContainer.addElement("foo");
        methodContainer.addElement("bar");
        methodIterator = methodContainer.iterator();
        while (methodIterator.hasNext()) {
            System.out.println(methodIterator.next());
        }
        System.out.println(methodContainer.size());

        // the removable iterator only available from list
        RemovableIterator listIterator = list.iterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        listIterator.remove();
        System.out.println(list.size());

        listIterator = list.iterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        listIterator.remove();
        System.out.println(list.size());

    }
}
