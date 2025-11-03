package gui;

import model.Student;
import model.User;
import data.DataManager;
import theme.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentDashboard extends JFrame {

    private final Student student;
    private JTable marksTable;
    private JLabel attendanceLabel;

    public StudentDashboard(User user) {
        this.student = (Student) user;

        setTitle("Student Dashboard - " + student.getName());
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ThemeManager.SECONDARY_COLOR);
        setLayout(new BorderLayout(10, 10));

        // ---------- HEADER ----------
        JLabel title = new JLabel("Welcome, " + student.getName() + "!", SwingConstants.CENTER);
        title.setFont(ThemeManager.TITLE_FONT);
        title.setOpaque(true);
        title.setBackground(ThemeManager.PRIMARY_COLOR);
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(700, 50));
        add(title, BorderLayout.NORTH);

        // ---------- SUBJECT MARKS TABLE ----------
        LinkedHashMap<String, Integer> subjects = student.getSubjects();
        String[] columns = {"Subject", "Marks"};
        Object[][] data = new Object[subjects.size()][2];

        int i = 0;
        for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
            i++;
        }

        marksTable = new JTable(data, columns);
        marksTable.setFont(ThemeManager.LABEL_FONT);
        marksTable.setRowHeight(25);
        marksTable.setGridColor(ThemeManager.SECONDARY_COLOR);

        JScrollPane scrollPane = new JScrollPane(marksTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(ThemeManager.SECONDARY_COLOR, 1));
        add(scrollPane, BorderLayout.CENTER);

        // ---------- ATTENDANCE PANEL ----------
        JPanel attendancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        attendancePanel.setBackground(ThemeManager.SECONDARY_COLOR);
        attendanceLabel = new JLabel("Attendance: " + student.getAttendancePercentage() + "%");
        attendanceLabel.setFont(ThemeManager.LABEL_FONT);
        attendancePanel.add(attendanceLabel);
        add(attendancePanel, BorderLayout.SOUTH);

        // ---------- LOGOUT BUTTON ----------
        JButton logoutButton = new JButton("Logout");
        ThemeManager.styleButton(logoutButton);
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginGUI().setVisible(true);
        });
        attendancePanel.add(logoutButton);
    }
}
