package model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String name;
    private String role; // student / teacher / admin (if extended later)

    // ---------- Constructors ----------
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = username; // default name if not provided
    }

    public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    // ---------- Getters ----------
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getRole() { return role; }

    // ---------- Setters ----------
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }

    // ---------- Utility ----------
    @Override
    public String toString() {
        return username + "," + password + "," + role;
    }
}
