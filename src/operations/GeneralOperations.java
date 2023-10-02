package operations;

import models.Faculty;
import models.Student;
import models.StudyField;

import java.util.List;

public class GeneralOperations {

    public static void createFaculty(List<Faculty> faculties, String name, String abbreviation, StudyField studyField) {
        Faculty faculty = new Faculty(name, abbreviation, studyField);
        faculties.add(faculty);
        System.out.println("models.Faculty created successfully.");
    }

    public static void searchStudent(List<Faculty> faculties, String email) {
        for (Faculty faculty : faculties) {
            for (Student student : faculty.students) {
                if (student.email.equals(email)) {
                    System.out.println("models.Student belongs to " + faculty.name + " faculty.");
                    return;
                }
            }
        }
        System.out.println("models.Student with email " + email + " not found.");
    }

    public static void displayFaculties(List<Faculty> faculties) {
        for (Faculty faculty : faculties) {
            System.out.println(faculty.name);
        }
    }

    public static void displayFacultiesByField(List<Faculty> faculties, StudyField field) {
        for (Faculty faculty : faculties) {
            if (faculty.studyField == field) {
                System.out.println(faculty.name);
            }
        }
    }
}
