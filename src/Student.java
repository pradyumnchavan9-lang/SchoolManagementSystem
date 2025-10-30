public class Student extends User {
    private double marks;
    private double attendancePercentage;

    public Student(String username, String password, String name, double marks, double attendancePercentage) {
        super(username, password, name, "Student");
        this.marks = marks;
        this.attendancePercentage = attendancePercentage;
    }

    public double getMarks() { return marks; }
    public double getAttendancePercentage() { return attendancePercentage; }

    public void setMarks(double marks) { this.marks = marks; }
    public void setAttendancePercentage(double attendancePercentage) { this.attendancePercentage = attendancePercentage; }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Marks: " + marks);
        System.out.println("Attendance: " + attendancePercentage + "%");
    }
}
