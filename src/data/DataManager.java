package data;

import model.*;
import java.io.*;
import java.util.*;

public class DataManager {

    private static final String STUDENT_FILE = "C:\\Users\\prady\\OneDrive\\Desktop\\School_Management\\SchoolProject\\students.txt";
    private static List<Student> students = new ArrayList<>();

    // --- Load All Students from File ---
    public static List<Student> getStudents() {
        if (students.isEmpty()) {
            loadStudents();
        }
        return students;
    }

public static void loadStudents() {
    students.clear();
    File file = new File(STUDENT_FILE);
    if (!file.exists()) {
        System.out.println("No students.txt found, starting with empty list.");
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Format: username,password,name,percentage,subject1:mark1;subject2:mark2;...
            String[] parts = line.split(",");
            if (parts.length < 5) continue;

            String username = parts[0];
            String password = parts[1];
            String name = parts[2];
            double percentage = Double.parseDouble(parts[3]);

            LinkedHashMap<String, Integer> subjects = new LinkedHashMap<>();
            String[] subjectParts = parts[4].split(";");
            for (String subj : subjectParts) {
                String[] kv = subj.split(":");
                if (kv.length == 2) {
                    subjects.put(kv[0], Integer.parseInt(kv[1]));
                }
            }

            students.add(new Student(username, password, name, subjects, percentage));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    // --- Save Students Back to File ---
    public static void saveStudents(List<Student> updatedStudents) {
        students = updatedStudents; // update global list
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students) {
                StringBuilder line = new StringBuilder();
                line.append(s.getUsername()).append(",")
                    .append(s.getPassword()).append(",")
                    .append(s.getName()).append(",");

                for (Map.Entry<String, Integer> entry : s.getSubjects().entrySet()) {
                    line.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
                }

                line.append(s.getAttendancePercentage());
                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Validate User (for Login) ---
    public static User validateUser(String username, String password) {
        // First, check students
        for (Student s : getStudents()) {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        // Future: add teachers as well
        return null;
    }
}
