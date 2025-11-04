package gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import model.User;
import model.Student;
import data.DataManager;
import theme.ThemeManager;
import util.ReportCardGenerator;

public class TeacherDashboard extends JFrame{

    private JTable table;
    private DefaultTableModel model;
    private java.util.List<Student> students;
    private User teacher;

    public TeacherDashboard(User teacher)
    {
        this.teacher=teacher;
        setTitle("Teacher Dashboard - Student Report Management");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Theme
        getContentPane().setBackground(ThemeManager.SECONDARY_COLOR);
        setLayout(new BorderLayout(10,10));

        //Title
        JLabel title=new JLabel("Student Marks and Attendance",SwingConstants.CENTER);
        title.setFont(ThemeManager.TITLE_FONT);
        title.setOpaque(true);
        title.setBackground(ThemeManager.PRIMARY_COLOR);
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(900, 60));
        add(title, BorderLayout.NORTH);

        //Table Setup
        model=new DefaultTableModel(new Object[]{"Name","Username","Subject","Attendance"},0);
        table=new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane=new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        // --- BUTTON PANEL ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(ThemeManager.SECONDARY_COLOR);

        JButton editButton = new JButton("Edit Marks");
        JButton refreshButton = new JButton("Refresh");
        JButton saveButton = new JButton("Save All");
        JButton generateReportButton = new JButton("Generate Report");
        JButton logoutButton = new JButton("Logout");

        ThemeManager.styleButton(editButton);
        ThemeManager.styleButton(refreshButton);
        ThemeManager.styleButton(saveButton);
        ThemeManager.styleButton(generateReportButton);
        ThemeManager.styleButton(logoutButton);

        buttonPanel.add(editButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(generateReportButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // --- LOAD INITIAL DATA ---
        loadData();

        //Actions
        editButton.addActionListener(e->editStudentMarks());
        refreshButton.addActionListener(e->loadData());
        saveButton.addActionListener(e->{
            DataManager.saveStudents(students);
            JOptionPane.showMessageDialog(this, "Changes Saved SuccessFully!!!");
        });
        logoutButton.addActionListener(e->{
            new LoginGUI().setVisible(true);
            dispose();
        });
        generateReportButton.addActionListener(e->{
            int selectedRow=table.getSelectedRow();
            if(selectedRow==-1)
            {
                JOptionPane.showMessageDialog(this, "Select a student first");
                return;
            }
            Student selectedStudent=students.get(selectedRow);
            ReportCardGenerator.generateReport(selectedStudent);
        });
    }

    //Load All Students
    private void loadData(){
        model.setRowCount(0);
        students=DataManager.getStudents();

        for(Student s:students)
        {
            StringBuilder subjectDetails=new StringBuilder();

            for (Map.Entry<String, Integer> entry : s.getSubjects().entrySet()) {
            subjectDetails.append(entry.getKey()).append(": ").append(entry.getValue()).append("  ");
            }

            model.addRow(new Object[]{
                s.getName(),
                s.getUsername(),
                subjectDetails.toString(),
                s.getAttendancePercentage() + "%"
            });
        }
    }

    //Edit Selected Students Marks

    private void editStudentMarks(){
        int selectedRow=table.getSelectedRow();
        if(selectedRow==-1)
        {
            JOptionPane.showMessageDialog(this, "Select A Student!!!");
            return;
        }
        Student selectedStudent=students.get(selectedRow);
        LinkedHashMap<String,Integer> subjects=selectedStudent.getSubjects();

        JPanel editPanel=new JPanel(new GridLayout(subjects.size(),2,10,10));
        Map<String,JTextField> fields=new LinkedHashMap<>();

            for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
            editPanel.add(new JLabel(entry.getKey() + ":"));
            JTextField field = new JTextField(String.valueOf(entry.getValue()));
            fields.put(entry.getKey(), field);
            editPanel.add(field);
            
        }

        int option=JOptionPane.showConfirmDialog(this,
                                                 editPanel,
                                                 "Edit Marks for" + selectedStudent.getName(),
                                                 JOptionPane.OK_CANCEL_OPTION
                                                 );
        
        if(option==JOptionPane.OK_OPTION)
        {
            for(String subject:fields.keySet())
            {
                try {
                    int newMark=Integer.parseInt(fields.get(subject).getText());
                    subjects.put(subject,newMark);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid mark for" + subject);
                }
            }

            selectedStudent.setSubjects(subjects); //update marks
            loadData(); //refresh the table
        }

    }
}
