import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException {
        University university = new University();

        System.out.println("Welcome to TUM's student management system!");

        Scanner scanner = new Scanner(System.in);

        System.out.print("What kind of operation you want to do? ");

        String operation = scanner.nextLine();



        System.out.println(operation);

        switch(operation){
            case ("g"):
                Scanner scanner_g = new Scanner(System.in);

                System.out.println("General operation:");

                String operation1 = scanner_g.nextLine();

                scanner_g.close();
                System.out.println(operation1);
                String[] parts = operation1.split("/");

                if (parts.length == 4 && parts[0].equals("nf")) {
                    String facultyName = parts[1];
                    String facultyAbbreviation = parts[2];
                    String field = parts[3];
                    Faculty faculty = new Faculty(facultyName, facultyAbbreviation, StudyField.valueOf(field.toUpperCase()));
                    university.createNewFaculty(faculty);
                    System.out.println(faculty);
                }else if (parts.length == 2 && parts[0].equals("ss")) {
                    String studentIdentifier = parts[1];
                    Faculty foundFaculty = university.findFacultyByStudentIdentifier(studentIdentifier);

                    if (foundFaculty != null) {
                        System.out.println("Faculty for " + studentIdentifier + ": " + foundFaculty.getName());
                    } else {
                        System.out.println("Faculty not found for " + studentIdentifier);
                    }
                } else if(parts.length == 1 && parts[0].equals("df")){
                    System.out.println("All Faculties:");
                    university.displayAllFaculties();
                    } else if(parts.length == 2 && parts[0].equals("df")) {
                    String field = parts[1];
                    for (Faculty faculty : university.getFacultiesByField(StudyField.valueOf(field.toUpperCase()))) {
                        System.out.println(faculty.getName());
                    }
                }
                else {
                    System.out.println("Invalid input format.");
                }
        }

//        Faculty mechFaculty = new Faculty("Mechanical Engineering", "MECH", StudyField.MECHANICAL_ENGINEERING);
//        Faculty softEngFaculty = new Faculty("Software Engineering", "SE", StudyField.SOFTWARE_ENGINEERING);

//        university.createNewFaculty(mechFaculty);
//        university.createNewFaculty(softEngFaculty);

        // Creating students
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob1 = sdf.parse("2000-01-15");
        Date dob2 = sdf.parse("1999-11-25");
        Date enrollmentDate = new Date(); // Current date

        Student student1 = new Student("John", "Doe", "john@example.com", enrollmentDate, dob1);
        Student student2 = new Student("Jane", "Doe", "jane@example.com", enrollmentDate, dob2);

//        // Assigning students to faculties
//        university.createAndAssignStudentToFaculty(student1, mechFaculty);
//        university.createAndAssignStudentToFaculty(student2, softEngFaculty);
//
//        // Displaying enrolled students
//        System.out.println("Enrolled students in Mechanical Engineering:");
//        university.displayEnrolledStudents(mechFaculty);
//
//        System.out.println("Enrolled students in Software Engineering:");
//        university.displayEnrolledStudents(softEngFaculty);
//
//        // Graduating a student
//        university.graduateStudent(student1, mechFaculty);
//
//        // Displaying graduates
//        System.out.println("Graduates in Mechanical Engineering:");
//        mechFaculty.displayGraduates();
//
//        // Checking if a student belongs to a faculty
//        boolean belongsToMech = university.doesStudentBelongToFaculty(student1, mechFaculty);
//        boolean belongsToSoftEng = university.doesStudentBelongToFaculty(student1, softEngFaculty);

//        System.out.println(student1.firstName + " belongs to Mechanical Engineering: " + belongsToMech);
//        System.out.println(student1.firstName + " belongs to Software Engineering: " + belongsToSoftEng);
//
//        // Finding faculty by student identifier
//        String studentIdentifier = "jane@example.com";
//        Faculty foundFaculty = university.findFacultyByStudentIdentifier(studentIdentifier);
//
//        if (foundFaculty != null) {
//            System.out.println("Faculty for " + studentIdentifier + ": " + foundFaculty.getName());
//        } else {
//            System.out.println("Faculty not found for " + studentIdentifier);
//        }

        // Displaying all faculties


        // Displaying faculties by field
        System.out.println("Faculties in Software Engineering:");
        for (Faculty faculty : university.getFacultiesByField(StudyField.SOFTWARE_ENGINEERING)) {
            System.out.println(faculty.getName());
        }
    }
}