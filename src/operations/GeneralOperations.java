package operations;

import file.FileManager;
import logger.DefaultLogger;
import models.Faculty;
import models.Student;
import models.StudyField;

import java.util.ArrayList;
import java.util.List;

public class GeneralOperations {
    private static final DefaultLogger logger = new DefaultLogger();

    public static void createFaculty(ArrayList<Faculty> faculties, String name, String abbreviation, StudyField studyField) {
        Faculty faculty = new Faculty(name, abbreviation, studyField);
        faculties.add(faculty);
        FileManager.saveData(faculties);
        logger.logInfo("models.Faculty created successfully");
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
        logger.logError("Student with such email not found");
    }

    public static void displayFaculties(List<Faculty> faculties) {
        if (faculties.isEmpty()) {
            logger.logError("No faculties found");
        }
        for (Faculty faculty : faculties) {
            System.out.println(faculty.name);
        }
    }

    public static void displayFacultiesByField(List<Faculty> faculties, StudyField field) {
        if (faculties.isEmpty()) {
            logger.logError("No faculties found");
        }
        for (Faculty faculty : faculties) {
            if (faculty.studyField == field) {
                System.out.println(faculty.name);
            }
        }
    }
}
