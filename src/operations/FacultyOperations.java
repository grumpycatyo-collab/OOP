package operations;

import models.Faculty;
import models.Student;

import java.util.Iterator;
import java.util.List;

public class FacultyOperations {

    public static void createStudent(Faculty faculty, Student student) {
        faculty.students.add(student);
        System.out.println("models.Student created and assigned successfully.");
    }

    public static void graduateStudent(List<Faculty> faculties, String email) {
        for (Faculty faculty : faculties) {
            Iterator<Student> iterator = faculty.students.iterator();
            while (iterator.hasNext()) {
                Student student = iterator.next();
                if (student.email.equals(email)) {
                    iterator.remove();
                    faculty.graduates.add(student);
                    System.out.println("Student graduated from " + faculty.name + " faculty.");
                    return;
                }
            }
        }
        System.out.println("Student with email " + email + " not found.");
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

        System.out.println("Graduated students in " + faculty.name + " faculty:");
        for (Student student : faculty.graduates) {
            System.out.println(student.firstName + " " + student.lastName);

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
