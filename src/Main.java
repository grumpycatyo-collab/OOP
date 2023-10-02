import models.Faculty;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final List<Faculty> faculties = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            choice = operations.MenuOperations.displayMainMenu(scanner);

            switch (choice) {
                case 'g':
                    menu.GeneralOperationsMenu.doGeneralOperationsMenu(scanner, faculties);
                    break;
                case 'f':
                    menu.FacultyOperationsMenu.doFacultyOperationsMenu(scanner, faculties);
                    break;
                case 's':
                    System.out.println("Sorry, not implemented!");
                    break;
                case 'q':
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }

        } while (choice != 'q');

        scanner.close();
    }
}