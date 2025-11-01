import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentDashboard extends JFrame {

    private Student student;
    private JLabel nameLabel, usernameLabel, marksLabel, attendanceLabel;
    private JButton logoutButton, refreshButton;

    public StudentDashboard(Student student) {
        this.student = student;

        // 🪟 Frame setup
        setTitle("Student Dashboard - " + student.getName());
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(ThemeManager.BACKGROUND_COLOR);

        // 🏷️ Title
        JLabel title = new JLabel("Welcome, " + student.getName() + "!", SwingConstants.CENTER);
        title.setFont(ThemeManager.TITLE_FONT);
        title.setForeground(ThemeManager.PRIMARY_COLOR);
        add(title, BorderLayout.NORTH);

        // 📋 Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        infoPanel.setBackground(ThemeManager.BACKGROUND_COLOR);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        nameLabel = new JLabel("Name: " + student.getName());
        usernameLabel = new JLabel("Username: " + student.getUsername());
        marksLabel = new JLabel("Marks: " + student.getMarks());
        attendanceLabel = new JLabel("Attendance: " + student.getAttendancePercentage() + "%");

        // Apply label styling
        JLabel[] labels = { nameLabel, usernameLabel, marksLabel, attendanceLabel };
        for (JLabel lbl : labels) {
            lbl.setFont(ThemeManager.LABEL_FONT);
            lbl.setForeground(ThemeManager.TEXT_COLOR);
            infoPanel.add(lbl);
        }

        add(infoPanel, BorderLayout.CENTER);

        // 🔘 Button panel (Refresh + Logout)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(ThemeManager.BACKGROUND_COLOR);

        refreshButton = new JButton("Refresh");
        ThemeManager.styleButton(refreshButton);
        refreshButton.addActionListener(e -> refreshData());

        logoutButton = new JButton("Logout");
        ThemeManager.styleButton(logoutButton);
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginGUI().setVisible(true);
        });

        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 🔄 Refresh student data dynamically
    private void refreshData() {
        nameLabel.setText("Name: " + student.getName());
        usernameLabel.setText("Username: " + student.getUsername());
        marksLabel.setText("Marks: " + student.getMarks());
        attendanceLabel.setText("Attendance: " + student.getAttendancePercentage() + "%");

        JOptionPane.showMessageDialog(this, "Student data refreshed!", "Refreshed", JOptionPane.INFORMATION_MESSAGE);
    }
}
