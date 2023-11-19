import interfaces.Queue;
import interfaces.Stack;
import queues.ArrayUpQueue;
import queues.CircularQueue;
import queues.LinkedQueue;
import stacks.ArrayUpStack;
import stacks.DoubleArrayStack;
import stacks.LinkedStack;

import java.util.Scanner;

import static operations.QueueOperations.handleQueueOperations;
import static operations.StackOperations.handleStackOperations;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

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





}