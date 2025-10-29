import javax.swing.*;
import java.awt.*;

public class TeacherDashboard extends JFrame{

    public  TeacherDashboard(User teacher){

        setTitle("Teacher Dashboard - " + teacher.getName());
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + teacher.getName() + "!", SwingConstants.CENTER);
        JLabel infoLabel = new JLabel("You are logged in as a teacher.", SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(welcomeLabel);
        panel.add(infoLabel);

        add(panel, BorderLayout.CENTER);
    }
}
