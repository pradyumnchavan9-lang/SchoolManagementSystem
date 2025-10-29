import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginGUI extends JFrame{
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private final JButton loginButton;
    private final JLabel statusLabel;

    // This acts as our temporary database
    private ArrayList<User> users=new ArrayList<>();

public LoginGUI(){

    setTitle("Login System");
    setSize(400,250);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Create UI Components
    JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    usernameField=new JTextField();
    passwordField=new JPasswordField();
    inputPanel.add(new JLabel("Username:"));
    inputPanel.add(usernameField);
    inputPanel.add(new JLabel("Password:"));
    inputPanel.add(passwordField);

    loginButton=new JButton("Login");
    statusLabel=new JLabel(" ", SwingConstants.CENTER);

    // ---------- Add Components ----------
    add(inputPanel, BorderLayout.CENTER);
    add(loginButton, BorderLayout.SOUTH);
    add(statusLabel, BorderLayout.NORTH);

    // ---------- Initialize Dummy Users ----------
    users.add(new User("student1", "pass123", "Alice", "student"));
    users.add(new User("teacher1", "teach123", "Mr. Smith", "teacher"));

    //-----------Login Action-------------
    loginButton.addActionListener(e->handleLogin());

    }

    private void handleLogin(){
        String username=usernameField.getText();
        String password = new String(passwordField.getPassword());

        boolean found=false;

        for(User u:users)
        {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
            {
                found=true;
                if(u.getRole().equalsIgnoreCase("student"))
                {
                    JOptionPane.showMessageDialog(this, "Welcome Student " + u.getName());
                    // Open Student DashBoard
                    new StudentDashboard(u).setVisible(true);
                }
                else if(u.getRole().equalsIgnoreCase("teacher")){
                    JOptionPane.showMessageDialog(this, "Welcome Teacher " + u.getName());
                    // Open Teacher Dashboard
                    new TeacherDashboard(u).setVisible(true);
                }
                break;

            }
        }
        if(!found)
        {
            statusLabel.setText("Invalid Username or password");
            statusLabel.setForeground(Color.RED);
        } else {
            statusLabel.setText(" ");
        }
    }
      public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}

