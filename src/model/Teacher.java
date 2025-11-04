package model;

public class Teacher extends User {
    private String subject;
    public Teacher(String username, String password, String name,String subject) {
        super(username, password, name, "teacher");
        this.subject=subject;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject)
    {
        this.subject=subject;
    }
}
