package com.tqs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class StackTests {

    Stack stack;

    @BeforeEach
    void setUp() {
        stack = new Stack();
    }

    @Test
    @DisplayName("Empty stack should be returned.")
    void isEmpty() {
        assertTrue(stack.isEmpty(), "Stack should be empty!");
    }

    @Test
    @DisplayName("Size 0 should be returned.")
    void sizeZero() {
        int size = stack.size();

        assertEquals(0, size, "Stack should be size 0!");

    }

    @Test
    @DisplayName("N pushs stack not empty.")
    void afterNPushs() {
        stack.push("element");
        stack.push("element1");
        stack.push("element2");

        int size = stack.size();

        assertAll("Size != 0",
                () -> assertTrue(!stack.isEmpty()),
                () -> assertEquals(3, size));

    }

    @Test
    @DisplayName("Push and pop x.")
    void pushThenPop() {
        stack.push("element");
        stack.push("element2");
        stack.push("element3");

        String popped = stack.pop();

        assertEquals("element3", popped);

    }

    @Test
    @DisplayName("Peek and size remains the same!")
    void peekThenEqualSize() {
        stack.push("element");
        stack.push("element2");
        stack.push("element3");

        String peeked = stack.peek();

        int size = stack.size();

        assertAll("Size has to be the same",
                () -> assertEquals("element3", peeked),
                () -> assertEquals(3, size));
    }

    @Test
    @DisplayName("Push n and pop n.")
    void afterNPops() {
        stack.push("element");
        stack.push("element2");
        stack.push("element3");

        int size = stack.size();

        for (int i = 0; i < size; i++) {
            stack.pop();
        }

        assertAll("Size has to be 0",
                () -> assertTrue(stack.isEmpty()),
                () -> assertEquals(0, stack.size()));
    }

    @Test
    @DisplayName("Pop from empty throws exception.")
    void popFromEmptyStack() {
        assertThrows(NoSuchElementException.class,
                () -> stack.pop());

    }

    @Test
    @DisplayName("Peek from empty throws exception.")
    void peekFromEmptyStack() {
        assertThrows(NoSuchElementException.class,
                () -> stack.peek());

    }

    @Test
    @DisplayName("Push into full stack.")
    void pushFullStack() {
        Stack stackwithbound = new Stack(2);

        stackwithbound.push("element");
        stackwithbound.push("element2");

        assertThrows(IllegalStateException.class, () -> {
            stackwithbound.push("element3");
        });
    }

}
