package file;

import models.Faculty;
import models.Student;
import models.StudyField;
import operations.FacultyOperations;

import java.io.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "db.txt";

    public static void saveData(ArrayList<Faculty> faculties) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Faculty faculty : faculties) {
                writer.write(String.format("Faculty: %s (%s), Study Field: %s%n", faculty.getName(), faculty.getAbbreviation(), faculty.getStudyField()));
                List<Student> students = faculty.getStudents();

                for (Student student : students) {
                    String studentInfo = String.format("Student: %s %s%nEmail: %s%nEnrollment Date: %s%nDate of Birth: %s%nGraduated: %s%n",
                            student.getFirstName(), student.getLastName(), student.getEmail(), student.getEnrollmentDate(), student.getDateOfBirth(), student.isGraduated() ? "Yes" : "No");
                    writer.write(studentInfo);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }


    public static ArrayList<Faculty> loadData() {
        ArrayList<Faculty> faculties = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Faculty currentFaculty = null;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Faculty: ")) {
                    String facultyInfo = line.substring("Faculty: ".length());
                    String[] facultyData = facultyInfo.split(" \\(");
                    String name = facultyData[0];
                    String abbreviation = facultyData[1].split("\\), Study Field: ")[0];
                    String studyField = facultyData[1].split("\\), Study Field: ")[1];
                    currentFaculty = new Faculty(name, abbreviation, StudyField.valueOf(studyField));
                    faculties.add(currentFaculty);

                } else if (line.startsWith("Student: ")) {
                    String[] studentInfo = line.substring(9).split(" ");
                    String firstName = studentInfo[0];
                    String lastName = studentInfo[1];
                    String email = reader.readLine().substring(7);
                    LocalDate enrollmentDate = LocalDate.parse(reader.readLine().substring("Enrollment Date: ".length()));
                    LocalDate dateOfBirth = LocalDate.parse(reader.readLine().substring("Date of Birth: ".length()));
                    boolean graduated = false;
                    graduated = reader.readLine().substring("Graduated: ".length()).equals("Yes");
                    Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, graduated);
                    if (currentFaculty != null) {
                        FacultyOperations.createStudent(currentFaculty, faculties, student);
                    }
                    reader.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return faculties;
    }


}