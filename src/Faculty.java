import java.util.ArrayList;
import java.util.List;

public class Faculty {
    String name;
    String abbreviation;
    List<Student> students;
    StudyField studyField;


    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void graduateStudent(Student student) {
        students.remove(student);
    }

    public void displayEnrolledStudents() {
        for (Student student : students) {
            System.out.println(student.firstName + " " + student.lastName);
        }
    }

    public void displayGraduates() {
        // Implement logic to display graduates
    }

    public boolean doesStudentBelongToFaculty(Student student) {
        return students.contains(student);
    }

    public boolean hasStudentWithIdentifier(String identifier) {
        for (Student student : students) {
            if (student.email.equals(identifier)) {
                return true;
            }
            // Add more identifier checks if needed (e.g., unique ID)
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public StudyField getStudyField() {
        return studyField;
    }
}
