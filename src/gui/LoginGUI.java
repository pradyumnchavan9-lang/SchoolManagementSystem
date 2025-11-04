package gui;

import model.User;
import model.Student;
import model.Teacher;
import data.DataManager;
import theme.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {
        setTitle("School Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ThemeManager.SECONDARY_COLOR);
        setLayout(new BorderLayout(10, 10));

        // ---------- HEADER ----------
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(ThemeManager.TITLE_FONT);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(ThemeManager.PRIMARY_COLOR);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(400, 50));
        add(titleLabel, BorderLayout.NORTH);

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(ThemeManager.SECONDARY_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        ThemeManager.styleButton(loginButton);
        formPanel.add(new JLabel(""));
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);

        // ---------- LOGIN ACTION ----------
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            User user = DataManager.validateUser(username, password);
            
            if (user instanceof Student) {
            new StudentDashboard((Student) user).setVisible(true);
                    dispose();
                } else if (user instanceof Teacher) {
                    new TeacherDashboard((Teacher) user).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password");
                }

            if (user == null) {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Welcome " + user.getName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose();

            switch (user.getRole().toLowerCase()) {
            case "teacher" -> new TeacherDashboard(user).setVisible(true);
            case "student" -> new StudentDashboard(user).setVisible(true);
}

        });
    }
}
