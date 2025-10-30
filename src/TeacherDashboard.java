import javax.swing.*;
import java.awt.*;

public class TeacherDashboard extends JFrame {
    public TeacherDashboard(User u) {
        setTitle("Teacher Dashboard - " + u.getName());
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome, " + u.getName() + "!", SwingConstants.CENTER);
        JLabel info = new JLabel("You are logged in as a teacher.", SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(welcome);
        panel.add(info);

        add(panel, BorderLayout.CENTER);
    }
}
