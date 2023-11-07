package queues;

import interfaces.Queue;

public class CircularQueue<T> implements Queue<T> {
    private T[] elements;
    private int front;
    private int rear;
    private int size;
    private int count;

    public CircularQueue(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = capacity;
        this.count = 0;
    }

    public void enqueue(T element) {
        if (count < size) {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            elements[rear] = element;
            count++;
        } else {
            throw new IllegalStateException("Queue is full");
        }
    }

    public T dequeue() {
        if (count > 0) {
            T data = elements[front];
            front = (front + 1) % size;
            count--;
            if (count == 0) {
                front = rear = -1;
            }
            return data;
        } else {
            throw new IllegalStateException("Queue is empty");
        }
    }

    public T element() {
        if (count > 0) {
            return elements[front];
        } else {
            throw new IllegalStateException("Queue is empty");
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }
}
