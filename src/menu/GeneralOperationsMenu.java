package menu;

import models.Faculty;
import models.StudyField;
import operations.GeneralOperations;
import operations.MenuOperations;

import java.util.List;
import java.util.Scanner;

public class GeneralOperationsMenu {

    public static void doGeneralOperationsMenu(Scanner scanner, List<Faculty> faculties) {
        String choice;
        String input;
        do {
            input = MenuOperations.displayGeneralOperationsMenu(scanner);
            String[] parts = input.split("/");
            choice = parts[0];

            switch (choice) {
                case "nf":
                    GeneralOperations.createFaculty(faculties, parts[1], parts[2], StudyField.valueOf(parts[3]));
                    break;
                case "ss":
                    GeneralOperations.searchStudent(faculties, parts[1]);
                    break;
                case "df":
                    if (parts.length == 1) {
                        GeneralOperations.displayFaculties(faculties);
                    } else {
                        GeneralOperations.displayFacultiesByField(faculties, StudyField.valueOf(parts[1]));
                    }
                    break;
                case "q":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }

        } while (!choice.equals("b") && !choice.equals("q"));
    }
}
