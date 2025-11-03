package model;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Student extends User implements Serializable {

    private LinkedHashMap<String, Integer> subjects;  // Subject → Marks
    private double attendancePercentage;

    public Student(String username, String password, String name,
                   LinkedHashMap<String, Integer> subjects, double attendancePercentage) {
        super(username, password, name, "student");
        this.subjects = subjects;
        this.attendancePercentage = attendancePercentage;
    }

    // --- Getter and Setter for Subjects ---
    public LinkedHashMap<String, Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(LinkedHashMap<String, Integer> subjects) {
        this.subjects = subjects;
    }

    // --- Helper to get Marks for One Subject ---
    public int getMarks(String subject) {
        return subjects.getOrDefault(subject, 0);
    }

    // --- Attendance Getters/Setters ---
    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }
}
