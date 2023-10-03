package operations;

import file.FileManager;
import models.Faculty;
import models.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FacultyOperations {

    public static Student getStudentByEmail(ArrayList<Faculty> faculties, String email) {

        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equals(email))
                    return student;
            }
        }

        return null;
    }

    public static void createStudent(Faculty faculty, ArrayList<Faculty> faculties, Student student) {
        faculty.students.add(student);
        FileManager.saveData(faculties);
        System.out.println("models.Student created and assigned successfully.");
    }

    public static void graduateStudent(ArrayList<Faculty> faculties, String email) {
        Student foundStudent = getStudentByEmail(faculties, email);
        if (foundStudent == null) {
            System.out.println("Student with email " + email + " is not present");
            return;
        }

        foundStudent.setGraduated(true);
        FileManager.saveData(faculties);
        System.out.println("Student " + foundStudent.getFirstName() + " " + foundStudent.getLastName() + " graduated.");
        FileManager.saveData(faculties);

    }

    public static void displayEnrolledStudents(Faculty faculty) {
        if (faculty.students.isEmpty()) {
            System.out.println("No students enrolled in " + faculty.name + " faculty.");
        } else {
            System.out.println("Enrolled students in " + faculty.name + " faculty:");
            for (Student student : faculty.students) {
                System.out.println(student.firstName + " " + student.lastName);
            }
        }
    }

    public static void displayGraduatedStudents(Faculty faculty) {
        System.out.println("Graduated students in " + faculty.getName() + ":");

        for (Student student : faculty.getStudents()) {
            if (student.isGraduated()) {
                System.out.println(student.getFirstName() + " " + student.getLastName() + " - " + student.getEmail());
            }
        }
    }

    public static boolean checkStudentBelongs(Faculty faculty, String email) {
        for (Student student : faculty.students) {
            if (student.email.equals(email)) {
                System.out.println("Yes, the student belongs to this faculty.");
                return true;
            }
        }
        System.out.println("No, the student does not belong to this faculty.");
        return false;
    }

    public static Faculty findFacultyByAbbreviation(List<Faculty> faculties, String abbreviation) {
        for (Faculty faculty : faculties) {
            if (faculty.abbreviation.equals(abbreviation)) {
                return faculty;
            }
        }
        return null;
    }
}
