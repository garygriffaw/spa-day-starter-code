package org.launchcode.spaday.models;

import java.time.LocalDate;

public class User {

    private int id;
    private static int nextId = 1;

    private String username;
    private String email;
    private String password;
    private LocalDate date;

    public User(String username, String email, String password, LocalDate date) {
        this.id = nextId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
        nextId++;
    }

// Getters and Setters

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
