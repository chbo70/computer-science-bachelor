package at.ac.uibk.pm.generics.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class StackApplication {
    public static void main(String[] args) {
        Stack<Number> arrayStack = new ArrayStack<>();
        arrayStack.pushAll(Arrays.asList(0.0, 1.0, 2.2));
        for (int i = 3; i < 10; i++) {
            arrayStack.push(i);
        }

        Collection<Number> poppedElements = new ArrayList<>();
        arrayStack.popAll(poppedElements);
        System.out.println(poppedElements);
        System.out.println(arrayStack.isEmpty());
    }
}
