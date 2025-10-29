import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame{
    
    public StudentDashboard(User student)
    {
        setTitle("Student Dashboard - " + student.getName());
        setSize(400,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel=new JLabel("Welcome," + student.getName() +"!",SwingConstants.CENTER);
        JLabel infoLabel=new JLabel("You are logged in as a student.",SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(welcomeLabel);
        panel.add(infoLabel);

        add(panel,BorderLayout.CENTER);
    }
}
