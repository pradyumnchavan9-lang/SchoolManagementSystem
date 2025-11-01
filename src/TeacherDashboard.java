import javax.swing.*;
import java.awt.*;
import java.util.List;


public class TeacherDashboard extends JFrame {
    private User teacher;
    private JTable studentTable;
    private JButton updateMarksButton, updateAttendanceButton, refreshButton;
    private List<Student> students;

    public TeacherDashboard(User teacher) {
        this.teacher = teacher;
        setTitle("Teacher Dashboard - " + teacher.getName());
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(ThemeManager.BACKGROUND_COLOR);

        // ---------- HEADER ----------
        JLabel title = new JLabel("Welcome, " + teacher.getName() + "!", SwingConstants.CENTER);
        title.setFont(ThemeManager.TITLE_FONT);
        title.setOpaque(true);
        title.setBackground(ThemeManager.PRIMARY_COLOR);
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(700, 50));
        add(title, BorderLayout.NORTH);

        // ---------- LOAD STUDENTS ----------
        students = DataManager.loadStudents();

        // ---------- TABLE SETUP ----------
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
        studentTable.setFont(ThemeManager.LABEL_FONT);
        studentTable.setRowHeight(25);
        studentTable.setGridColor(ThemeManager.SECONDARY_COLOR);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(ThemeManager.SECONDARY_COLOR, 1));
        add(scrollPane, BorderLayout.CENTER);

        // ---------- BUTTONS ----------
        updateMarksButton = new JButton("Update Marks");
        updateAttendanceButton = new JButton("Update Attendance");
        refreshButton = new JButton("Refresh");

        ThemeManager.styleButton(updateMarksButton);
        ThemeManager.styleButton(updateAttendanceButton);
        ThemeManager.styleButton(refreshButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(ThemeManager.BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(updateMarksButton);
        buttonPanel.add(updateAttendanceButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // ---------- ACTION LISTENERS ----------

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
