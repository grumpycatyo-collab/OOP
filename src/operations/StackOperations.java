package operations;

import interfaces.Stack;
import stacks.ArrayUpStack;
import stacks.DoubleArrayStack;
import stacks.LinkedStack;

import java.util.Scanner;

public class StackOperations {
    public static Scanner scanner = new Scanner(System.in);
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


    public static void handleStackOperations() {
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
}
