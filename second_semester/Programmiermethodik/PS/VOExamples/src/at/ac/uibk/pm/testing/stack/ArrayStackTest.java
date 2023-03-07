package at.ac.uibk.pm.testing.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayStackTest {

    @Test
    @DisplayName("an empty stack should be empty")
    public void isEmpty() {
        final ArrayStack stack = new ArrayStack();

        final boolean isEmpty = stack.isEmpty();

        assertTrue(isEmpty);
    }

    @Test
    @DisplayName("a stack containing one element should not be empty")
    public void isNotEmpty() {
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");

        final boolean isEmpty = stack.isEmpty();

        assertFalse(isEmpty);
    }

    @Test
    @DisplayName("an empty stack should have size 0")
    public void sizeEmpty() {
        final ArrayStack stack = new ArrayStack();

        final int size = stack.size();

        assertEquals(0, size);
    }

    @Test
    @DisplayName("a stack with two elements should have size 2")
    public void sizeNotEmpty() {
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");
        stack.push("beta");

        final int size = stack.size();

        assertEquals(2, size);
    }

    @Test
    @DisplayName("push three elements")
    public void pushThreeElements() {
        final ArrayStack stack = new ArrayStack();

        stack.push("alpha");
        stack.push("beta");
        stack.push("gamma");

        assertAll(
                () -> assertEquals(3, stack.size()),
                () -> assertEquals("gamma", stack.peek())
        );
    }

    @Test
    @DisplayName("push an element on a full stack")
    public void pushStackFull() {
        final ArrayStack stack = new ArrayStack(5);
        stack.push("alpha");
        stack.push("beta");
        stack.push("gamma");
        stack.push("delta");
        stack.push("epsilon");

        Executable when = () -> stack.push("zeta");

        assertThrows(FullStackException.class, when);
    }

    @Test
    @DisplayName("pop on empty stack")
    public void popEmpty() {
        final ArrayStack stack = new ArrayStack();

        Executable when = stack::pop;

        assertThrows(EmptyStackException.class, when);
    }

    @Test
    @DisplayName("pop on stack with two elements")
    public void popNotEmpty() {
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");
        stack.push("beta");

        final String element = stack.pop();

        assertAll(
                () -> assertEquals("beta", element),
                () -> assertEquals(1, stack.size())
        );
    }

    @Test
    @DisplayName("push then pop on empty stack")
    public void pushPopEmpty() {
        final ArrayStack stack = new ArrayStack();

        stack.push("alpha");
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("peek on empty stack")
    public void peekEmpty() {
        final ArrayStack stack = new ArrayStack();

        Executable when = stack::peek;

        assertThrows(EmptyStackException.class, when);
    }

    @Test
    @DisplayName("peek on stack with two elements")
    public void peekNotEmpty() {
        final ArrayStack stack = new ArrayStack();
        stack.push("alpha");
        stack.push("beta");

        final String element = stack.peek();

        assertAll(
                () -> assertEquals("beta", element),
                () -> assertEquals(2, stack.size())
        );
    }
}