package com.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<T> {

    private LinkedList<T> stack;
    private int bound = -1;

    public Stack() {
        this.stack = new LinkedList<T>();
    }

    public Stack(int bound) {
        this.bound = bound;
        stack = new LinkedList<T>();
    }

    public void push(T element) {
        if (bound > 0) {
            if (stack.size() == bound)
                throw new IllegalStateException();
        }
        stack.addFirst(element);
    }

    public T pop() {
        if (this.stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.pop();
    }

    public T peek() {
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