package queues;

import interfaces.Queue;

public class ArrayUpQueue<T> implements Queue<T> {
    private T[] elements;
    private int front;
    private int rear;

    public ArrayUpQueue(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.front = -1;
        this.rear = -1;
    }

    public void enqueue(T element) {
        if (rear < elements.length - 1) {
            elements[++rear] = element;
            if (front == -1) {
                front = 0;
            }
        } else {
            throw new IllegalStateException("Queue is full");
        }
    }

    public T dequeue() {
        if (front <= rear && front != -1) {
            T data = elements[front++];
            if (front > rear) {
                front = rear = -1;
            }
            return data;
        } else {
            throw new IllegalStateException("Queue is empty");
        }
    }

    public T element() {
        if (front <= rear && front != -1) {
            return elements[front];
        } else {
            throw new IllegalStateException("Queue is empty");
        }
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return rear == elements.length - 1;
    }
}
