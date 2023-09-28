import java.util.ArrayList;
import java.util.List;

public class University {

    List<Faculty> faculties;

    public University() {
        faculties = new ArrayList<>();
    }

    // Faculty operations
    public void createAndAssignStudentToFaculty(Student student, Faculty faculty) {
        faculty.addStudent(student);
    }

    public void graduateStudent(Student student, Faculty faculty) {
        faculty.graduateStudent(student);
    }

    public void displayEnrolledStudents(Faculty faculty) {
        faculty.displayEnrolledStudents();
    }

    public void displayGraduates(Faculty faculty) {
        faculty.displayGraduates();
    }

    public boolean doesStudentBelongToFaculty(Student student, Faculty faculty) {
        return faculty.doesStudentBelongToFaculty(student);
    }

    // General operations
    public void createNewFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public Faculty findFacultyByStudentIdentifier(String identifier) {
        for (Faculty faculty : faculties) {
            if (faculty.hasStudentWithIdentifier(identifier)) {
                return faculty;
            }
        }
        return null; // Return null if not found
    }

    public void displayAllFaculties() {
        for (Faculty faculty : faculties) {
            System.out.println(faculty.getName());
        }
    }

    public List<Faculty> getFacultiesByField(StudyField field) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty : faculties) {
            if (faculty.getStudyField() == field) {
                result.add(faculty);
            }
        }
        return result;
    }
}
