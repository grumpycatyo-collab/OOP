import interfaces.Queue;
import interfaces.Stack;
import queues.ArrayUpQueue;
import queues.CircularQueue;
import queues.LinkedQueue;
import stacks.ArrayUpStack;
import stacks.DoubleArrayStack;
import stacks.LinkedStack;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Stack Operations");
            System.out.println("2. Queue Operations");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleStackOperations();
                    break;
                case 2:
                    handleQueueOperations();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void handleStackOperations() {
        System.out.println("Select the type of Stack:");
        System.out.println("1. ArrayUpStack");
        System.out.println("2. DoubleArrayStack");
        System.out.println("3. LinkedStack");
        int stackChoice = scanner.nextInt();

        switch (stackChoice) {
            case 1:
                handleArrayUpStackOperations();
                break;
            case 2:
                handleDoubleArrayStackOperations();
                break;
            case 3:
                handleLinkedStackOperations();
                break;
            default:
                System.out.println("Invalid stack type.");
        }
    }

    private static void handleQueueOperations() {
        System.out.println("Select the type of Queue:");
        System.out.println("1. ArrayUpQueue");
        System.out.println("2. CircularQueue");
        System.out.println("3. LinkedQueue");
        int queueChoice = scanner.nextInt();

        switch (queueChoice) {
            case 1:
                handleArrayUpQueueOperations();
                break;
            case 2:
                handleCircularQueueOperations();
                break;
            case 3:
                handleLinkedQueueOperations();
                break;
            default:
                System.out.println("Invalid queue type.");
        }
    }

    private static void handleArrayUpStackOperations() {
        Stack<String> arrayUpStack = new ArrayUpStack<>(5);
        stackOperations(arrayUpStack);
    }

    private static void handleDoubleArrayStackOperations() {
        Stack<Double> doubleArrayStack = new DoubleArrayStack<>(5);
        stackOperations(doubleArrayStack);
    }

    private static void handleLinkedStackOperations() {
        Stack<Integer> linkedStack = new LinkedStack<>();
        stackOperations(linkedStack);
    }

    private static void handleArrayUpQueueOperations() {
        Queue<String> arrayUpQueue = new ArrayUpQueue<>(5);
        queueOperations(arrayUpQueue);
    }

    private static void handleCircularQueueOperations() {
        Queue<Boolean> circularQueue = new CircularQueue<>(5);
        queueOperations(circularQueue);
    }

    private static void handleLinkedQueueOperations() {
        Queue<Integer> linkedQueue = new LinkedQueue<>();
        queueOperations(linkedQueue);
    }

    // Generic method to handle stack operations
    private static <T> void stackOperations(Stack<T> stack) {
        boolean back = false;
        while (!back) {
            System.out.println("Stack Operations:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Back to main menu");
            int operation = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (operation) {
                case 1:
                    if(stack.isFull()){
                        System.out.println("Stack is full. You cannot push");
                        break;
                    }
                    else {
                        System.out.println("Enter an element to push:");
                        T element = (T) scanner.nextLine();

                        stack.push(element);
                        System.out.println("Element pushed successfully");
                        break;
                    }
                case 2:
                    if (!stack.isEmpty()) {
                        System.out.println("Popped element: " + stack.pop());
                    } else {
                        System.out.println("Stack is empty.");
                    }
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid operation.");
            }
        }
    }

    // Generic method to handle queue operations
    private static <T> void queueOperations(Queue<T> queue) {
        boolean back = false;
        while (!back) {
            System.out.println("Queue Operations:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Back to main menu");
            int operation = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (operation) {
                case 1:
                    if(queue.isFull()){
                        System.out.println("Queue is full. You cannot enqueue");
                        break;
                    }
                {
                    System.out.println("Enter an element to enqueue:");
                    T element = (T) scanner.nextLine();
                    queue.enqueue(element);
                    break;
                }
                case 2:
                    if (!queue.isEmpty()) {
                        System.out.println("Dequeued element: " + queue.dequeue());
                    } else {
                        System.out.println("Queue is empty.");
                    }
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid operation.");
            }
        }
    }
}