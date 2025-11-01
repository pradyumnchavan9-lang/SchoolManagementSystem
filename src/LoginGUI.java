import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    private java.util.List<User> users;

    public LoginGUI() {
        setTitle("Login - School Management System");
        setSize(420, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(ThemeManager.BACKGROUND_COLOR);

        // ---------- HEADER ----------
        JPanel header = new JPanel();
        header.setBackground(ThemeManager.PRIMARY_COLOR);
        JLabel title = new JLabel("School Management System", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(ThemeManager.TITLE_FONT);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(ThemeManager.BACKGROUND_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(ThemeManager.LABEL_FONT);
        usernameLabel.setForeground(ThemeManager.TEXT_COLOR);
        usernameField = new JTextField(15);
        usernameField.setBorder(BorderFactory.createLineBorder(ThemeManager.SECONDARY_COLOR, 1));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(ThemeManager.LABEL_FONT);
        passwordLabel.setForeground(ThemeManager.TEXT_COLOR);
        passwordField = new JPasswordField(15);
        passwordField.setBorder(BorderFactory.createLineBorder(ThemeManager.SECONDARY_COLOR, 1));

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // ---------- BUTTON PANEL ----------
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(ThemeManager.BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        loginButton = new JButton("Login");
        ThemeManager.styleButton(loginButton);
        buttonPanel.add(loginButton, BorderLayout.CENTER);

        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(ThemeManager.LABEL_FONT);
        buttonPanel.add(statusLabel, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);

        // ---------- LOAD USERS ----------
        users = new ArrayList<>();
        for (Student s : DataManager.loadStudents()) {
            users.add(s);
        }
        for (Teacher t : DataManager.loadTeachers()) {
            users.add(t);
        }

        // ---------- LOGIN ACTION ----------
        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

    for (User u : users) {
        if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
        JOptionPane.showMessageDialog(this, "Welcome " + u.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);

        if (u.getRole().equalsIgnoreCase("student") && u instanceof Student)
            new StudentDashboard((Student) u).setVisible(true);
        else if (u.getRole().equalsIgnoreCase("teacher") && u instanceof Teacher)
            new TeacherDashboard((Teacher) u).setVisible(true);

        this.dispose();
        return;
        }
    }

        statusLabel.setText("Invalid username or password");
        statusLabel.setForeground(Color.RED);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
