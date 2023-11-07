package stacks;

import interfaces.Stack;

public class ArrayUpStack<T> implements Stack<T> {
    private T[] elements;
    private int top;

    public ArrayUpStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.top = -1;
    }

    public void push(T element) {
        if (top < elements.length - 1) {
            elements[++top] = element;
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    public T pop() {
        if (top >= 0) {
            return elements[top--];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    public T element() {
        if (top >= 0) {
            return elements[top];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == elements.length - 1;
    }
}
