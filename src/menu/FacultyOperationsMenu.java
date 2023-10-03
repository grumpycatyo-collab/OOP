package menu;

import models.Faculty;
import models.Student;
import operations.FacultyOperations;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

import static operations.FacultyOperations.checkStudentBelongs;

public class FacultyOperationsMenu {
    public static void doFacultyOperationsMenu(Scanner scanner, ArrayList<Faculty> faculties) {
        String choice;
        String input;
        do {
            input = DisplayMenu.displayFacultyOperationsMenu(scanner);
            String[] parts = input.split("/");
            choice = parts[0];

            switch (choice) {
                case "ns":
                    String abbreviation = parts[1];
                    String firstName = parts[2];
                    String lastName = parts[3];
                    String email = parts[4];
                    int day = Integer.parseInt(parts[5]);
                    int month = Integer.parseInt(parts[6]);
                    int year = Integer.parseInt(parts[7]);
                    LocalDate enrollmentDate = LocalDate.of(year, month, day);
                    LocalDate dateOfBirth = LocalDate.of(year, month, day);
                    boolean graduated = false;
                    Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth,graduated);
                    boolean facultyFound = false;
                    for (Faculty faculty : faculties) {
                        if (faculty.abbreviation.equals(abbreviation)) {
                            FacultyOperations.createStudent(faculty,faculties,student);
                            facultyFound = true;
                            break;
                        }
                    }
                    if (!facultyFound) {
                        System.out.println("models.Faculty with abbreviation " + abbreviation + " not found.");
                    }
                    break;
                case "gs":
                    FacultyOperations.graduateStudent(faculties, parts[1]);
                    break;
                case "ds":
                    Faculty facultyDS = FacultyOperations.findFacultyByAbbreviation(faculties, parts[1]);
                    if (facultyDS != null) {
                        FacultyOperations.displayEnrolledStudents(facultyDS);
                    } else {
                        System.out.println("Faculty with abbreviation " + parts[1] + " not found.");
                    }
                    break;
                case "dg":
                    Faculty facultyDG = FacultyOperations.findFacultyByAbbreviation(faculties, parts[1]);
                    if (facultyDG != null) {
                        FacultyOperations.displayGraduatedStudents(facultyDG);
                    } else {
                        System.out.println("Faculty with abbreviation " + parts[1] + " not found.");
                    }
                    break;
                case "bf":
                    Faculty facultyBF = FacultyOperations.findFacultyByAbbreviation(faculties, parts[1]);
                    assert facultyBF != null;
                    checkStudentBelongs(facultyBF, parts[2]);
                    break;
                case "b":
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
