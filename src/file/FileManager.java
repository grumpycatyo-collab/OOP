package file;

import models.Faculty;
import models.Student;
import models.StudyField;

import java.io.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "university.txt";

    public static void saveData(ArrayList<Faculty> faculties) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Faculty faculty : faculties) {
                writer.write(String.format("Faculty: %s (%s), Study Field: %s%n", faculty.getName(), faculty.getAbbreviation(), faculty.getStudyField()));
                List<Student> students = faculty.getStudents();
                for (Student student : students) {
                    String studentInfo = String.format("Student: %s %s%nEmail: %s%nEnrollment Date: %s%nDate of Birth: %s%n",
                            student.getFirstName(), student.getLastName(), student.getEmail(), student.getEnrollmentDate(), student.getDateOfBirth());
                    writer.write(studentInfo);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }


}