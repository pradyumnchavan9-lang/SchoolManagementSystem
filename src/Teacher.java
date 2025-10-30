public class Teacher extends User {
    private String subject;

    public Teacher(String username, String password, String name, String subject) {
        super(username, password, name, "Teacher");
        this.subject = subject;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public void updateStudentRecord(Student s, double newMarks, double newAttendance) {
        s.setMarks(newMarks);
        s.setAttendancePercentage(newAttendance);
        System.out.println("Updated " + s.getName() + "'s record successfully!");
    }

    public void displayTeacherInfo() {
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Subject: " + subject);
    }
}
