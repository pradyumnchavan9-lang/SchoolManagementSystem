import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    private List<User> users;

    public LoginGUI() {
        setTitle("Login System");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        loginButton = new JButton("Login");
        statusLabel = new JLabel(" ", SwingConstants.CENTER);

        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        // Load Data
        users = new ArrayList<>();
        for (Student s : DataManager.loadStudents()) {
            users.add(s);
        }

        // Hardcoded teacher
        users.add(new User("teacher1", "teach123", "Mr. Rudra", "Teacher"));

        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Welcome " + u.getName());

                if (u.getRole().equalsIgnoreCase("student"))
                    new StudentDashboard(u).setVisible(true);
                else if (u.getRole().equalsIgnoreCase("teacher"))
                    new TeacherDashboard(u).setVisible(true);

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
