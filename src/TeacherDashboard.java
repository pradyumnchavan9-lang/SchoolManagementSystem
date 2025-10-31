import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TeacherDashboard extends JFrame {
    private User teacher;
    private JTable studentTable;
    private JButton updateMarksButton, updateAttendanceButton, refreshButton;
    private List<Student> students;

    public TeacherDashboard(User teacher) {
        this.teacher = teacher;
        setTitle("Teacher Dashboard - " + teacher.getName());
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------- Load Students ----------
        students = DataManager.loadStudents();

        // ---------- Table Setup ----------
        String[] columns = {"Username", "Name", "Marks", "Attendance (%)"};
        Object[][] data = new Object[students.size()][4];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            data[i][0] = s.getUsername();
            data[i][1] = s.getName();
            data[i][2] = s.getMarks();
            data[i][3] = s.getAttendancePercentage();
        }

        studentTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // ---------- Buttons ----------
        updateMarksButton = new JButton("Update Marks");
        updateAttendanceButton = new JButton("Update Attendance");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateMarksButton);
        buttonPanel.add(updateAttendanceButton);
        buttonPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ---------- Action Listeners ----------

        // Update Marks
        updateMarksButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student first.");
                return;
            }

            String username = studentTable.getValueAt(selectedRow, 0).toString();
            String marksStr = JOptionPane.showInputDialog(this, "Enter new marks:");

            if (marksStr == null || marksStr.trim().isEmpty()) return;

            try {
                double marks = Double.parseDouble(marksStr);
                for (Student s : students) {
                    if (s.getUsername().equals(username)) {
                        s.setMarks(marks);
                        DataManager.saveStudents(students);
                        JOptionPane.showMessageDialog(this, "Marks updated successfully!");
                        refreshTable();
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid marks format.");
            }
        });

        // Update Attendance
        updateAttendanceButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student first.");
                return;
            }

            String username = studentTable.getValueAt(selectedRow, 0).toString();
            String attendanceStr = JOptionPane.showInputDialog(this, "Enter new attendance (%):");

            if (attendanceStr == null || attendanceStr.trim().isEmpty()) return;

            try {
                double attendance = Double.parseDouble(attendanceStr);
                for (Student s : students) {
                    if (s.getUsername().equals(username)) {
                        s.setAttendancePercentage(attendance);
                        DataManager.saveStudents(students);
                        JOptionPane.showMessageDialog(this, "Attendance updated successfully!");
                        refreshTable();
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid attendance format.");
            }
        });

        // Refresh Table
        refreshButton.addActionListener(e -> refreshTable());
    }

    private void refreshTable() {
        students = DataManager.loadStudents();
        String[] columns = {"Username", "Name", "Marks", "Attendance (%)"};
        Object[][] data = new Object[students.size()][4];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            data[i][0] = s.getUsername();
            data[i][1] = s.getName();
            data[i][2] = s.getMarks();
            data[i][3] = s.getAttendancePercentage();
        }

        studentTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }
}
