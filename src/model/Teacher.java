package model;

public class Teacher extends User {
    public Teacher(String username, String password, String name) {
        super(username, password, name, "teacher");
    }
}
