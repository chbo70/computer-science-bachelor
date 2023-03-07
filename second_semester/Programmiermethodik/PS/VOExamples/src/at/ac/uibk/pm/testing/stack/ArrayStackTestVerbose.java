package at.ac.uibk.pm.testing.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayStackTestVerbose {

    @Test
    @DisplayName("an empty stack should be empty")
    public void isEmpty() {
        // GIVEN: an empty array stack
        final ArrayStack stack = new ArrayStack();

        // WHEN: isEmpty is called
        final boolean isEmpty = stack.isEmpty();

        // THEN: It should return true
        assertTrue(isEmpty);
    }

    @Test
    @DisplayName("a stack containing one element should not be empty")
    public void isNotEmpty() {
        // GIVEN: an array stack with one element
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");

        // WHEN: isEmpty is called
        final boolean isEmpty = stack.isEmpty();

        // THEN: it should return false
        assertFalse(isEmpty);
    }

    @Test
    @DisplayName("an empty stack should have size 0")
    public void sizeEmpty() {
        // GIVEN: an empty array stack
        final ArrayStack stack = new ArrayStack();

        // WHEN: size is called
        final int size = stack.size();

        // THEN: it should return 0
        assertEquals(0, size);
    }

    @Test
    @DisplayName("a stack with two elements should have size 2")
    public void sizeNotEmpty() {
        // GIVEN: an array stack with two elements
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");
        stack.push("beta");

        // WHEN: size is called
        final int size = stack.size();

        // THEN: the stack should have size 2
        assertEquals(2, size);
    }

    @Test
    @DisplayName("push three elements")
    public void pushThreeElements() {
        // GIVEN: an emtpy array stack
        final ArrayStack stack = new ArrayStack();

        // WHEN: the strings alpha, beta, gamma are pushed
        stack.push("alpha");
        stack.push("beta");
        stack.push("gamma");

        // THEN: the array stack should contain three elements
        assertAll(
                () -> assertEquals(3, stack.size()),
                () -> assertEquals("gamma", stack.peek())
        );
    }

    @Test
    @DisplayName("push an element on a full stack")
    public void pushStackFull() {
        // GIVEN: an array stack with a maximum size of five containing five elements
        final ArrayStack stack = new ArrayStack(5);
        stack.push("alpha");
        stack.push("beta");
        stack.push("gamma");
        stack.push("delta");
        stack.push("epsilon");

        // WHEN: push is called
        Executable when = () -> stack.push("zeta");

        // THEN: it should result in FullStackException
        assertThrows(FullStackException.class, when);
    }

    @Test
    @DisplayName("pop on empty stack")
    public void popEmpty() {
        // GIVEN: an empty array stack
        final ArrayStack stack = new ArrayStack();

        // WHEN: pop is called
        Executable when = stack::pop;

        // THEN: it should result in EmptyStackException
        assertThrows(EmptyStackException.class, when);
    }

    @Test
    @DisplayName("pop on stack with two elements")
    public void popNotEmpty() {
        // GIVEN: an array stack with the two elements alpha, beta
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");
        stack.push("beta");

        // WHEN: an element is popped
        final String element = stack.pop();

        // THEN: it should be equal to beta and the size of the stack should be 1
        assertAll(
                () -> assertEquals("beta", element),
                () -> assertEquals(1, stack.size())
        );
    }

    @Test
    @DisplayName("push then pop on empty stack")
    public void pushPopEmpty() {
        // GIVEN: an empty array stack
        final ArrayStack stack = new ArrayStack();

        // WHEN: an element is pushed and popped
        stack.push("alpha");
        stack.pop();

        // THEN: the stack should be empty
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("peek on empty stack")
    public void peekEmpty() {
        // GIVEN: an empty array stack
        final ArrayStack stack = new ArrayStack();

        // WHEN: peek is called
        Executable when = stack::peek;

        // THEN: it should result in EmptyStackException
        assertThrows(EmptyStackException.class, when);
    }

    @Test
    @DisplayName("peek on stack with two elements")
    public void peekNotEmpty() {
        // GIVEN: an array stack with the two elements alpha, beta
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");
        stack.push("beta");

        // WHEN: an element is popped
        final String element = stack.peek();

        // THEN: it should be equal to beta and the size of the stack should be 2
        assertAll(
                () -> assertEquals("beta", element),
                () -> assertEquals(2, stack.size())
        );
    }
}