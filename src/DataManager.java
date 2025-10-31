import java.io.*;
import java.util.*;

public class DataManager {
    private static final String STUDENT_FILE = "C:\\Users\\prady\\OneDrive\\Desktop\\School_Management\\SchoolProject\\students.txt";

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        File file = new File(STUDENT_FILE);
        if (!file.exists()) {
            System.out.println("No existing student data found. Starting fresh...");
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Expect 5 fields: username,password,name,marks,attendance
                if (parts.length != 5) continue;

                String username = parts[0].trim();
                String password = parts[1].trim();
                String name = parts[2].trim();

                try {
                    double marks = Double.parseDouble(parts[3].trim());
                    double attendance = Double.parseDouble(parts[4].trim());
                    students.add(new Student(username, password, name, marks, attendance));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid student record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student data: " + e.getMessage());
        }

        return students;
    }

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students) {
                bw.write(s.getUsername() + "," +
                         s.getPassword() + "," +
                         s.getName() + "," +
                         s.getMarks() + "," +
                         s.getAttendancePercentage());
                bw.newLine();
            }
            System.out.println("✅ Student data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    //--------Load Teachers---------
    public static List<Teacher> loadTeachers(){
        List<Teacher> teachers=new ArrayList<>();
        File file=new File("C:\\Users\\prady\\OneDrive\\Desktop\\School_Management\\SchoolProject\\teachers.txt");
        if(!file.exists())
        {
            System.out.println("No existing teacher data found. Starting fresh...");
            return teachers;
        }
        try(BufferedReader br= new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine())!=null)
            {
                String[] parts=line.split(",");
                if(parts.length!=4)
                {
                    continue;
                }
                String username=parts[0];
                String password=parts[1];
                String name=parts[2];
                String subject=parts[3];

                teachers.add(new Teacher(username,password,name,subject));
            }
        }catch(IOException e)
        {
            System.out.println("Error reading teacher data: " + e.getMessage());
        }
        return teachers;
    }

    //------Save Teachers-----
    public static void saveTeachers(List<Teacher> teachers) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\prady\\OneDrive\\Desktop\\School_Management\\SchoolProject\\teachers.txt"))) {
        for (Teacher t : teachers) {
            bw.write(t.getUsername() + "," +
                     t.getPassword() + "," +
                     t.getName() + "," +
                     t.getSubject());
            bw.newLine();
        }
        System.out.println("✅ Teacher data saved successfully!");
    } catch (IOException e) {
        System.out.println("Error saving teacher data: " + e.getMessage());
        }
    }
}
