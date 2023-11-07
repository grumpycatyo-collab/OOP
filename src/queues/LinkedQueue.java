package queues;

import interfaces.Queue;

public class LinkedQueue<T> implements Queue<T> {
    private Node<T> front;
    private Node<T> rear;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
    }

    public T dequeue() {
        if (front != null) {
            T data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return data;
        } else {
            throw new IllegalStateException("Queue is empty");
        }
    }

    public T element() {
        if (front != null) {
            return front.data;
        } else {
            throw new IllegalStateException("Queue is empty");
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public boolean isFull() {
        return false; // Not applicable for linked list-based implementation
    }
}
