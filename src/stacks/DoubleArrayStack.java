package stacks;

import interfaces.Stack;

public class DoubleArrayStack<T> implements Stack<T> {
    private T[] elements;
    private int top1;
    private int top2;
    private int size;

    public DoubleArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.top1 = -1;
        this.top2 = capacity;
        this.size = capacity;
    }

    public void push(T element) {
        if (top1 < top2 - 1) {
            elements[++top1] = element;
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    public T pop() {
        if (top1 >= 0) {
            return elements[top1--];
        } else if (top2 < size) {
            return elements[top2++];
        } else {
            throw new IllegalStateException("Both stacks are empty");
        }
    }

    public T element() {
        if (top1 >= 0) {
            return elements[top1];
        } else if (top2 < size) {
            return elements[top2];
        } else {
            throw new IllegalStateException("Both stacks are empty");
        }
    }

    public boolean isEmpty() {
        return top1 == -1 && top2 == size;
    }

    public boolean isFull() {
        return top1 == top2 - 1;
    }
}
