import file.FileManager;
import models.Faculty;
import menu.DisplayMenu;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static final ArrayList<Faculty> faculties = FileManager.loadData();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            choice = DisplayMenu.displayMainMenu(scanner);

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