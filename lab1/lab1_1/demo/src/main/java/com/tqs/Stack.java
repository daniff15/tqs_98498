package com.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack {

    private LinkedList<String> stack;
    private int bound = -1;

    public Stack() {
        this.stack = new LinkedList<>();
    }

    public Stack(int bound) {
        this.bound = bound;
        stack = new LinkedList<>();
    }

    public void push(String element) {
        if (bound > 0) {
            if (stack.size() == bound)
                throw new IllegalStateException();
        }
        stack.addFirst(element);
    }

    public String pop() {
        if (this.stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.pop();
    }

    public String peek() {
        if (this.stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.getFirst();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }

}