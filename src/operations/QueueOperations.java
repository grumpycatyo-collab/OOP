package operations;

import interfaces.Queue;
import queues.ArrayUpQueue;
import queues.CircularQueue;
import queues.LinkedQueue;

import java.util.Scanner;

public class QueueOperations {
    public static Scanner scanner = new Scanner(System.in);
    public static void handleQueueOperations() {
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
