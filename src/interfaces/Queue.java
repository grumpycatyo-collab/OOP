package interfaces;

public interface Queue<T> {
    void enqueue(T element);
    T dequeue();
    T element();
    boolean isEmpty();
    boolean isFull();
}