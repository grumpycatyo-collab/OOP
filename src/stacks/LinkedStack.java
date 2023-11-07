package stacks;

import interfaces.Stack;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (top != null) {
            T data = top.data;
            top = top.next;
            return data;
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    public T element() {
        if (top != null) {
            return top.data;
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public boolean isFull() {
        return false; // Not applicable for linked list-based implementation
    }
}
