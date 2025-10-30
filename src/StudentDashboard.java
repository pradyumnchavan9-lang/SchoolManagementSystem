import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {
    public StudentDashboard(User u) {
        setTitle("Student Dashboard - " + u.getName());
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome, " + u.getName() + "!", SwingConstants.CENTER);
        JLabel info = new JLabel("You are logged in as a student.", SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(welcome);
        panel.add(info);

        add(panel, BorderLayout.CENTER);
    }
}
