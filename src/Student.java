import java.util.Date;

public class Student {
    String firstName;
    String lastName;
    String email;
    Date enrollmentDate;
    Date dateOfBirth;

    public Student(String firstName, String lastName, String email, Date enrollmentDate, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
    }
}
