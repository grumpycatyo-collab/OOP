package models;

import java.time.LocalDate;
import java.util.Date;

public class Student {
    public String firstName;
    public String lastName;
    public String email;
    LocalDate enrollmentDate;
    LocalDate dateOfBirth;
    public boolean Graduated;

    public Student(String firstName, String lastName, String email, LocalDate enrollmentDate, LocalDate dateOfBirth, boolean Graduated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.Graduated = Graduated;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public boolean isGraduated() {
        return Graduated;
    }

    public void setGraduated(boolean Graduated) {
        this.Graduated = Graduated;
    }
}
