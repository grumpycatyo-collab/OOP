package interfaces;

public interface Stack<T> {
    void push(T element);
    T pop();
    T element();
    boolean isEmpty();
    boolean isFull();
}