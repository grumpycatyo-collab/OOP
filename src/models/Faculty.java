package models;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    public String name;
    public String abbreviation;
    public List<Student> students;
    public StudyField studyField;
    public List<Student> graduates;


    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
        this.graduates = new ArrayList<>();
    }

}
