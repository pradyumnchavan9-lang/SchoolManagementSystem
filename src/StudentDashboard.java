import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentDashboard extends JFrame {

    private JLabel nameLabel,usernameLabel,marksLabel,attendanceLabel;
    private JButton logoutButton;

    public StudentDashboard(User user) {
        setTitle("Student Dashboard - " + user.getName());
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        //Cast to Student if Possible 
        Student student=(user instanceof Student)? (Student) user :null;

        //Header
        JLabel title=new JLabel("Welcome, "+ user.getName() + "!",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,18));
        add(title,BorderLayout.NORTH);

        //Info Panel
        JPanel infoPanel=new JPanel(new GridLayout(4,1,10,10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));

        nameLabel=new JLabel("Name: "+user.getName());
        usernameLabel=new JLabel("Username: "+user.getUsername());

        if(student!=null)
        {
            marksLabel=new JLabel("Marks: "+student.getMarks());
            attendanceLabel=new JLabel("Attendance: "+ student.getAttendancePercentage() + "%");
        }
        else{
            marksLabel = new JLabel("Marks: (not available)");
            attendanceLabel = new JLabel("Attendance: (not available)");
        }

        infoPanel.add(nameLabel);
        infoPanel.add(usernameLabel);
        infoPanel.add(marksLabel);
        infoPanel.add(attendanceLabel);
        add(infoPanel, BorderLayout.CENTER);

          // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginGUI().setVisible(true);
        });
        add(logoutButton, BorderLayout.SOUTH);
        
    }
}
