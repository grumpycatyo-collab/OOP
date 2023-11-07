
import interfaces.Queue;
import interfaces.Stack;
import queues.ArrayUpQueue;
import queues.CircularQueue;
import queues.LinkedQueue;
import stacks.ArrayUpStack;
import stacks.DoubleArrayStack;
import stacks.LinkedStack;


// ## Main Class

public class Main {
    public static void main(String[] args) {
        // ### Stack examples

        // **ArrayUpStack**
        Stack<String> arrayUpStack = new ArrayUpStack<>(5);
        arrayUpStack.push("A");
        arrayUpStack.push("B");
        System.out.println("ArrayUpStack pop: " + arrayUpStack.pop()); // Output: B

        // **LinkedStack**
        Stack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push(1);
        linkedStack.push(2);
        System.out.println("LinkedStack pop: " + linkedStack.pop()); // Output: 2

        // **DoubleArrayStack**
        Stack<Double> doubleArrayStack = new DoubleArrayStack<>(6);
        doubleArrayStack.push(3.14);
        doubleArrayStack.push(2.71);
        System.out.println("DoubleArrayStack pop: " + doubleArrayStack.pop()); // Output: 2.71

        // ### Queue examples

        // **ArrayUpQueue**
        Queue<String> arrayUpQueue = new ArrayUpQueue<>(5);
        arrayUpQueue.enqueue("X");
        arrayUpQueue.enqueue("Y");
        System.out.println("ArrayUpQueue dequeue: " + arrayUpQueue.dequeue()); // Output: X

        // **LinkedQueue**
        Queue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(10);
        linkedQueue.enqueue(20);
        System.out.println("LinkedQueue dequeue: " + linkedQueue.dequeue()); // Output: 10

        // **CircularQueue**
        Queue<Boolean> circularQueue = new CircularQueue<>(3);
        circularQueue.enqueue(true);
        circularQueue.enqueue(false);
        System.out.println("CircularQueue dequeue: " + circularQueue.dequeue()); // Output: true
    }
}
